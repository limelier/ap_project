package controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import data.Board
import data.BoardEntity
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.TextInputDialog
import model.BoardModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import view.control.ImportExportPane
import java.io.IOException

class ImportExportController(private val boardModel: BoardModel, importExportPane: ImportExportPane) {
    private val httpClient = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    init {
        val importButton = importExportPane.importButton
        val exportButton = importExportPane.exportButton

        importButton.onAction = EventHandler { handleImportRequest() }
        exportButton.onAction = EventHandler { handleExportRequest() }
    }

    private fun handleExportRequest() {
        val pieces = boardModel.pieces.map { row ->
            row.map { it.get() }.toTypedArray()
        }.toTypedArray()
        val boardEntity = BoardEntity(
            name = boardModel.name.get(),
            description = boardModel.description.get()
        )
        val board = Board(boardEntity, pieces)
        val json = mapper.writeValueAsString(board)

        val request = Request.Builder()
            .url("http://localhost:8080/api/boards/")
            .post(json.toRequestBody("application/json; charset=utf-8".toMediaType()))
            .build()

        val response = httpClient.newCall(request).execute()

        try {
            response.use {
                if (it.isSuccessful && it.body != null) {
                    codeAlert(it.body!!.string())
                } else {
                    genericErrorAlert(it)
                }
            }
        } catch (ex: IOException) {
            connectionFailureAlert()
        }
    }

    private fun genericErrorAlert(it: Any) {
        Alert(Alert.AlertType.ERROR).apply {
            title = "Unknown error"
            headerText = "Unexpected error occurred"
            contentText = it.toString()

            showAndWait()
        }
    }

    private fun codeAlert(code: String) {
        Alert(Alert.AlertType.INFORMATION).apply {
            title = "Export successful"
            headerText = code
            contentText = "Use the code above to import the board somewhere else."

            showAndWait()
        }
    }

    private fun handleImportRequest() {
        val dialog = TextInputDialog().apply {
            title = "Import board"
            headerText = null
            contentText = "Code:"

        }
        val result = dialog.showAndWait()
        result.ifPresent { executeImport(it.toInt()) }
    }

    private fun executeImport(code: Int) {
        val request = Request.Builder()
            .url("http://localhost:8080/api/boards/${code}")
            .build()

        try {
            val response = httpClient.newCall(request).execute()

            response.use {
                if (it.isSuccessful && it.body != null) {
                    importFromJson(it.body!!.string())
                } else {
                    importFailureAlert()
                }
            }
        } catch (ex: IOException) {
            connectionFailureAlert()
        }
    }

    private fun connectionFailureAlert() {
        Alert(Alert.AlertType.ERROR).apply {
            title = "Connection failure"
            headerText = "Connection failure!"
            contentText = "Make sure your internet connection works."

            showAndWait()
        }
    }

    private fun importFailureAlert() {
        Alert(Alert.AlertType.ERROR).apply {
            title = "Import failure"
            headerText = "Import failed!"
            contentText = "The code you entered was invalid, please try again."

            showAndWait()
        }
    }

    private fun importFromJson(json: String) {
        val board: Board = mapper.readValue(json)
        boardModel.name.set(board.boardEntity.name)
        boardModel.description.set(board.boardEntity.description)

        val modelPieces = boardModel.pieces.flatten()
        val importedPieces = board.pieces.flatten()
        modelPieces.zip(importedPieces).forEach { (modelPiece, piece) ->
            modelPiece.set(piece)
        }
    }
}