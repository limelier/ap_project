package com.limelier.approj.api.controller

import com.limelier.approj.api.model.Board
import com.limelier.approj.api.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boards")
class BoardController(
        private val boardService: BoardService
) {
    @GetMapping
    fun getAll(): List<Board> = boardService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Board> {
        val board = boardService.get(id)
        return if (board != null) {
            ResponseEntity.ok(board)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun post(@Validated @RequestBody board: Board): Int = boardService.post(board)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        return if (boardService.delete(id)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}