package view.control

import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextInputControl
import javafx.scene.control.TextInputDialog
import javafx.scene.layout.VBox

class ImportExportPane : VBox() {
    val importCode = SimpleStringProperty()

    init {
        val importButton = Button("Import a board")
        val exportButton = Button("Export board")

        importButton.prefWidthProperty().bind(widthProperty())
        importButton.onAction = EventHandler {
            val dialog = TextInputDialog().apply {
                title = "Import board"
                headerText = null
                contentText = "Code:"
            }
            val result = dialog.showAndWait()
            result.ifPresent { importCode.set(it) }
        }

        exportButton.prefWidthProperty().bind(widthProperty())
        exportButton.onAction = EventHandler {
            Alert(Alert.AlertType.INFORMATION).apply {
                title = "Export board"
                headerText = "Board exported! Use the code below to import it again."
                contentText = "######"
                showAndWait()
            }
        }

        children.addAll(importButton, exportButton)
        spacing = 5.0
        alignment = Pos.CENTER
    }
}