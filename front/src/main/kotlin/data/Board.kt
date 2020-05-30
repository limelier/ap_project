package data

data class Board(
    var name: String = "",
    var description: String = "",
    var pieces: Array<Array<Piece?>> = Array(8) { Array<Piece?>(8) { null } }
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (name != other.name) return false
        if (description != other.description) return false
        if (!pieces.contentDeepEquals(other.pieces)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + pieces.contentDeepHashCode()
        return result
    }
}