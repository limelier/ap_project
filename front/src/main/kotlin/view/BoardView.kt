package view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.TilePane
import javafx.scene.layout.VBox

class BoardView : VBox() {
    val tiles = Array(8) { row ->
        Array(8) { col ->
            Tile(row, col)
        }
    }

    val name = SimpleStringProperty()
    val description = SimpleStringProperty()

    init {
        padding = Insets(40.0, 20.0, 20.0, 20.0)
        spacing = 20.0

        val textRoot = HBox()
        textRoot.spacing = 20.0
        textRoot.alignment = Pos.CENTER
        val titleBox = TextField()
        titleBox.promptText = "untitled"
        titleBox.textProperty().bindBidirectional(name)
        val descBox = TextField()
        descBox.textProperty().bindBidirectional(description)
        textRoot.children.addAll(Label("Title"), titleBox, Label("Description"), descBox)

        val boardRoot = TilePane()
        boardRoot.children.addAll(tiles.flatten())
        boardRoot.prefWidth = 512.0
        boardRoot.prefHeight = 512.0
        boardRoot.minWidth = 512.0
        boardRoot.minHeight = 512.0


        children.addAll(textRoot, boardRoot)
    }
}