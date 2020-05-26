package com.limelier.approj.api.service

import com.limelier.approj.api.model.Board
import com.limelier.approj.api.model.BoardEntity
import com.limelier.approj.api.model.PieceEntity
import com.limelier.approj.api.repository.BoardRepository
import com.limelier.approj.api.repository.PieceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardService @Autowired constructor(
        private val boardRepository: BoardRepository,
        private val pieceRepository: PieceRepository
) {
    private fun packBoard(boardEntity: BoardEntity): Board {
        val pieces = pieceRepository.findByBoardId(boardEntity)
        return Board(boardEntity, pieces)
    }

    private fun unpackBoardPieces(board: Board, boardEntity: BoardEntity): List<PieceEntity> {
        val pieceGrid = board.pieces
        val pieceEntities = mutableListOf<PieceEntity>()
        for ((i, row) in pieceGrid.withIndex()) {
            for ((j, piece) in row.withIndex()) {
                if (piece == null) {
                    continue
                } else {
                    val pos = (i * 8 + j).toShort()
                    pieceEntities.add(PieceEntity(boardEntity, pos, piece.color, piece.rank))
                }
            }
        }
        return pieceEntities
    }

    fun getAll(): List<Board> {
        val boardEntities = boardRepository.findAll()
        return boardEntities.map { packBoard(it) }
    }

    fun get(id: Int): Board? {
        val boardEntity = boardRepository.findByIdOrNull(id) ?: return null
        return packBoard(boardEntity)
    }

    fun post(board: Board): Int {
        val boardEntity = boardRepository.save(board.boardEntity)
        val pieceEntities = unpackBoardPieces(board, boardEntity)
        pieceRepository.saveAll(pieceEntities)
        return boardEntity.id
    }

    fun delete(id: Int): Boolean {
        val boardEntity = boardRepository.findByIdOrNull(id)
        return if (boardEntity == null) {
            false
        } else {
            boardRepository.delete(boardEntity)
            true
        }
    }
}