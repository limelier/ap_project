package com.limelier.approj.api.model

import org.hibernate.annotations.TypeDef
import javax.persistence.*
import java.io.Serializable

data class PieceId(
        val boardId: Int = 0,
        val pos: Short = 0
) : Serializable

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

@Entity(name = "piece")
@IdClass(PieceId::class)
data class PieceEntity(
        @Id @ManyToOne @JoinColumn(name = "board_id")
        val boardId: BoardEntity? = null,
        @Id
        val pos: Short = 0,
        @Enumerated(EnumType.STRING)
        val color: Color? = null,
        @Enumerated(EnumType.STRING)
        val rank: Rank? = null
)

data class Piece(
        val color: Color,
        val rank: Rank
) {
    constructor(pieceEntity: PieceEntity) :
            this(pieceEntity.color!!, pieceEntity.rank!!)
}