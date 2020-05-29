enum class Color {
    WHITE,
    BLACK
}

enum class Rank {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
}

data class Piece(
    val color: Color,
    val rank: Rank
)
