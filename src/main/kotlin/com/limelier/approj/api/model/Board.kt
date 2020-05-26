package com.limelier.approj.api.model

import javax.persistence.*

@Entity(name = "board")
data class BoardEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        val name: String = "",
        val description: String = ""
)

class Board(
        val boardEntity: BoardEntity,
        val pieces: Array<Array<Piece?>> = Array(8) { Array<Piece?>(8) { null } }
) {
    constructor(boardEntity: BoardEntity, pieceEntities: List<PieceEntity>) : this(boardEntity) {
        for (pieceEntity in pieceEntities) {
            val pos = pieceEntity.pos
            val row = pos / 8
            val col = pos % 8
            pieces[row][col] = Piece(pieceEntity)
        }
    }
}