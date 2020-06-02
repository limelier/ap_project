package controller

import data.Piece
import javafx.event.EventHandler
import model.BoardModel
import model.SelectionModel
import view.board.BoardView

/**
 * Tie the board [model] and [view] together.
 *
 * Updates the [view] pieces are changed in the [model], and changes the model when a tile on the view is clicked.
 * Uses the [selectionModel] to determine which piece to change a tile's state to. Also bidirectionally binds a board's
 * name and description.
 */
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
            tile.onMouseClicked = EventHandler {
                val rank = selectionModel.rankProperty.get()
                piece.set(
                    if (rank == null) null
                    else Piece(selectionModel.colorProperty.get(), selectionModel.rankProperty.get())
                )
            }
        }

        view.name.bindBidirectional(model.title)
        view.description.bindBidirectional(model.description)
    }
}