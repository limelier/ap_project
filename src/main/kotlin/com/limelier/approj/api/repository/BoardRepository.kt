package com.limelier.approj.api.repository

import com.limelier.approj.api.model.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<BoardEntity, Int>