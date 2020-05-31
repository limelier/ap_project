package controller

import data.Piece
import model.BoardModel
import model.SelectionModel
import view.board.BoardView

class BoardController(
    model: BoardModel,
    view: BoardView,
    private val selectionModel: SelectionModel
) {
    init {
        val flatPieces = model.pieces.flatten()
        val flatTiles = view.tiles.flatten()

        flatPieces.zip(flatTiles).forEach { (piece, tile) ->
            piece.addListener { _, _, newPiece -> tile.setPiece(newPiece) }
            tile.setOnMouseClicked {
                val rank = selectionModel.rankProperty.get()
                piece.set(
                    if (rank == null) null
                    else Piece(selectionModel.colorProperty.get(), selectionModel.rankProperty.get())
                )
            }
        }

        view.name.bindBidirectional(model.name)
        view.description.bindBidirectional(model.description)
    }
}