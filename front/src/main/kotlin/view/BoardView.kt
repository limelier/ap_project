package view

import javafx.scene.layout.TilePane

class BoardView : TilePane() {
    val tiles = Array(8) { row ->
        Array(8) { col ->
            Tile(row, col)
        }
    }

    init {
        children.addAll(tiles.flatten())
        width = 512.0
        height = 512.0
    }
}