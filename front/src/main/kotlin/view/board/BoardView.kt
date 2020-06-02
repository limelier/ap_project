package view.board

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.TilePane
import javafx.scene.layout.VBox
import java.util.*

private val loc = ResourceBundle.getBundle("locale.Main")

/**
 * View for the current board, title and description.
 */
class BoardView : VBox() {
    /**
     * 8 by 8 matrix holding the tiles in the view.
     */
    val tiles = Array(8) { row ->
        Array(8) { col ->
            Tile(row, col)
        }
    }

    val title = SimpleStringProperty()
    val description = SimpleStringProperty()

    init {
        padding = Insets(20.0, 20.0, 20.0, 20.0)
        spacing = 20.0

        val boardRoot = TilePane().apply {
            children.addAll(tiles.flatten())
            prefWidth = 512.0
            prefHeight = 512.0
            minWidth = 512.0
            minHeight = 512.0
        }

        children.addAll(initTextRoot(), boardRoot)
    }

    /**
     * Initialize the [javafx.scene.layout.VBox] containing the [title] and [description].
     */
    private fun initTextRoot(): VBox {
        val textRoot = VBox().apply {
            spacing = 5.0
            alignment = Pos.CENTER
        }
        val titleField = TextField().apply {
            promptText = loc.getString("titlePrompt")
            textProperty().bindBidirectional(title)
        }
        val titleLabel = Label(loc.getString("titleLabel"))
        val titleBox = HBox(titleLabel, titleField).apply {
            spacing = 5.0
            alignment = Pos.CENTER_LEFT
        }
        titleField.prefWidthProperty().bind(
            titleBox.widthProperty()
                .subtract(titleLabel.widthProperty())
                .subtract(5.0)
        )
        val descArea = TextArea().apply {
            promptText = loc.getString("descPrompt")
            textProperty().bindBidirectional(description)
            prefRowCount = 3
        }
        textRoot.children.addAll(titleBox, descArea)
        return textRoot
    }
}