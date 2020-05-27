package com.limelier.approj.api.repository

import com.limelier.approj.api.model.BoardEntity
import com.limelier.approj.api.model.PieceEntity
import com.limelier.approj.api.model.PieceId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PieceRepository : JpaRepository<PieceEntity, PieceId> {
    fun findByBoardId(boardId: BoardEntity): List<PieceEntity>
}