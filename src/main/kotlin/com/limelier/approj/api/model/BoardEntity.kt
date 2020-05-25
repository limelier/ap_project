package com.limelier.approj.api.model

import javax.persistence.*

@Entity(name = "board")
data class BoardEntity (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        val name: String = "",
        val description: String = ""
)

data class Board (
        val boardEntity: BoardEntity,
        val pieces: List<Piece>
)