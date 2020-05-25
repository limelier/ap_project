package com.limelier.approj.api.controller

import com.limelier.approj.api.model.BoardEntity
import com.limelier.approj.api.model.Board
import com.limelier.approj.api.model.Piece
import com.limelier.approj.api.repository.BoardRepository
import com.limelier.approj.api.repository.PieceRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BoardController(
        private val boardRepository : BoardRepository,
        private val pieceRepository: PieceRepository
) {
    @GetMapping("/boards")
    fun getAllBoards(): List<Board> {
        val boardEntities: List<BoardEntity> = boardRepository.findAll()
        return boardEntities.map {
            val pieces = pieceRepository.findByBoardId(it)
            Board(it, pieces)
        }
    }
}