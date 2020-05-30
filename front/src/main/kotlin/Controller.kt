class Controller {
    private val state = State()

    fun changeColor(color: Color) {
        state.selectedColor = color
    }

    fun changeRank(rank: Rank?) {
        state.selectedRank = rank
    }

    fun changeTile(tile: Tile) {
        val piece = state.selectedRank?.let { Piece(state.selectedColor, it) }
        tile.setPiece(piece)
    }
}