package controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import data.Board
import data.BoardEntity
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.TextInputDialog
import javafx.scene.layout.Region
import model.BoardModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import view.control.ImportExportPane
import java.io.IOException
import java.util.*

class ImportExportController(private val boardModel: BoardModel, importExportPane: ImportExportPane) {
    private val httpClient = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    /**
     * Resource bundle containing localized strings.
     */
    private val loc = ResourceBundle.getBundle("locale.Modals")

    init {
        val importButton = importExportPane.importButton
        val exportButton = importExportPane.exportButton

        importButton.onAction = EventHandler { handleImportRequest() }
        exportButton.onAction = EventHandler { handleExportRequest() }
    }

    /**
     * Build a [data.Board] from the [boardModel] and POST it to the API.
     *
     * Call [codeAlert] with the code once exporting is complete.
     * Call [connectionFailureAlert] if connection to the API fails.
     */
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

        try {
            val response = httpClient.newCall(request).execute()

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

    /**
     * Display an alert for an unknown error, with [it] as the contentText.
     *
     * Should generally not appear.
     */
    private fun genericErrorAlert(it: Any) {
        Alert(Alert.AlertType.ERROR).apply {
            title = loc.getString("genericError.title")
            headerText = loc.getString("genericError.header")
            contentText = it.toString()
            dialogPane.minHeight = Region.USE_PREF_SIZE

            showAndWait()
        }
    }

    /**
     * Display an alert with the [code] that the board was saved at.
     */
    private fun codeAlert(code: String) {
        Alert(Alert.AlertType.INFORMATION).apply {
            title = loc.getString("codeAlert.title")
            headerText = code
            contentText = loc.getString("codeAlert.content")

            showAndWait()
        }
    }

    /**
     * Show a dialog asking for a code for the board to import.
     *
     * Calls [executeImport] with the code once obtained.
     */
    private fun handleImportRequest() {
        val dialog = TextInputDialog().apply {
            title = loc.getString("importDialog.title")
            headerText = null
            contentText = loc.getString("importDialog.content")

        }
        val result = dialog.showAndWait()
        result.ifPresent { executeImport(it) }
    }

    /**
     * Send a GET request to the API to retrieve the board with the given [code].
     *
     * Calls [importFromJson] with the response body if successful, or [importFailureAlert] on a failure such as being
     * unable to find the board.
     * Calls [connectionFailureAlert] if connection to the API fails.
     */
    private fun executeImport(code: String) {
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

    /**
     * Show an alert describing a connection failure.
     */
    private fun connectionFailureAlert() {
        Alert(Alert.AlertType.ERROR).apply {
            title = loc.getString("connectionAlert.title")
            headerText = loc.getString("connectionAlert.header")
            contentText = loc.getString("connectionAlert.content")
            dialogPane.minHeight = Region.USE_PREF_SIZE

            showAndWait()
        }
    }

    /**
     * Show an alert describing an import failure.
     *
     * An example of an import failure is trying to import a non-existent board.
     */
    private fun importFailureAlert() {
        Alert(Alert.AlertType.ERROR).apply {
            title = loc.getString("importFailureAlert.title")
            headerText = loc.getString("importFailureAlert.header")
            contentText = loc.getString("importFailureAlert.content")

            showAndWait()
        }
    }

    /**
     * Deserialize a [json] string into a [data.Board], importing it into the [boardModel].
     */
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