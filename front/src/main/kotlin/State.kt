class State {
    var selectedColor = Color.WHITE
    var selectedRank: Rank? = null

    var name: String = ""
    var description: String = ""

    var pieces: Array<Array<Piece?>> = Array(8) { arrayOfNulls(8) }
}