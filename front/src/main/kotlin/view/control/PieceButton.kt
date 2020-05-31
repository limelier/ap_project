package view.control

import data.Color
import data.Piece
import javafx.scene.image.ImageView


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