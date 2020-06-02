package data

data class BoardEntity(
    /**
     * ID of a board in the database. Unused, but required for successful mapping.
     */
    val id: Int = 0,
    var name: String = "",
    var description: String = ""
)

/**
 * Class corresponding to the one sent and received by the API.
 */
data class Board(
    var boardEntity: BoardEntity,
    var pieces: Array<Array<Piece?>> = Array(8) { Array<Piece?>(8) { null } }
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (boardEntity != other.boardEntity) return false
        if (!pieces.contentDeepEquals(other.pieces)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = boardEntity.hashCode()
        result = 31 * result + pieces.contentDeepHashCode()
        return result
    }
}