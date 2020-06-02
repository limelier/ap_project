package model

import data.Piece
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

/**
 * Model for the board currently displayed in the program.
 */
class BoardModel {
    val titleProperty = SimpleStringProperty("")
    val descriptionProperty = SimpleStringProperty("")

    /**
     * 8 by 8 matrix holding the piece in each tile of the board, or a `null` if the tile is empty.
     */
    val pieces = Array(8) { Array(8) { SimpleObjectProperty<Piece?>() } }
}