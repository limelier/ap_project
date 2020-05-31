package controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import data.Board
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.TextInputDialog
import model.BoardModel
import okhttp3.OkHttpClient
import okhttp3.Request
import view.control.ImportExportPane
import java.io.IOException

class ImportExportController(private val boardModel: BoardModel, importExportPane: ImportExportPane) {
    private val httpClient = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    init {
        val importButton = importExportPane.importButton
        val exportButton = importExportPane.exportButton

        importButton.onAction = EventHandler { handleImportRequest() }
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
                if (it.code == 200 && it.body != null) {
                    importFromJson(it.body!!.string())
                } else {
                    importFailureAlert()
                }
            }
        } catch (ex: IOException) {
            println(ex)
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