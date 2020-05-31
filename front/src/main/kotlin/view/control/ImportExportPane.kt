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
    val importButton = Button("Import a board")
    val exportButton = Button("Export board")

    init {
        importButton.prefWidthProperty().bind(widthProperty())
        exportButton.prefWidthProperty().bind(widthProperty())

        children.addAll(importButton, exportButton)
        spacing = 5.0
        alignment = Pos.CENTER
    }
}