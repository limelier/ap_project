package view

import data.Piece
import javafx.scene.image.ImageView


class PieceButton(piece: Piece) : SelectButton() {
    init {
        prefWidth = 64.0
        prefHeight = 64.0
        graphic = ImageView(piece.image)
    }
}