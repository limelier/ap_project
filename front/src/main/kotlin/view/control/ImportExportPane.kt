package view.control

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox

class ImportExportPane : VBox() {
    init {
        val importButton = Button("Import a board")
        importButton.prefWidthProperty().bind(widthProperty())
        val exportButton = Button("Export board")
        exportButton.prefWidthProperty().bind(widthProperty())

        children.addAll(importButton, exportButton)
        spacing = 5.0
        alignment = Pos.CENTER
    }
}