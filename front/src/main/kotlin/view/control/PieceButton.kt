package view.control

import data.Color
import data.Piece
import javafx.scene.image.ImageView

/**
 * Button for selecting the rank of the placed piece.
 *
 * Has a graphic depicting the given [piece], which will match the rank it allows the selection of, and the currently
 * selected piece color.
 */
class PieceButton(private val piece: Piece) : SelectButton() {
    fun changeColor(color: Color) {
        graphic = ImageView(piece.copy(color = color).image)
    }

    fun getRank() = piece.rank

    init {
        prefWidth = 64.0
        prefHeight = 64.0
        graphic = ImageView(piece.image)
    }
}