package controller

import model.BoardModel
import view.board.BoardView

class BoardController(model: BoardModel, view: BoardView) {
    init {
        val flatPieces = model.pieces.flatten()
        val flatTiles = view.tiles.flatten()

        flatPieces.zip(flatTiles).forEach {
            it.first.addListener { _, _, piece -> it.second.setPiece(piece)}
        }

        view.name.bindBidirectional(model.name)
        view.description.bindBidirectional(model.description)
    }
}