class Board(val cells: Array<Array<Char>> = Array(size) { Array(size) { ' ' } }) {

    private val isFill: Boolean = cells.isFill()

    companion object {
        var size = 3
        fun stringToArray(string: String) = string.toCharArray().toTypedArray()
    }

    override fun toString() = cells.joinToString(
        separator = "\n",
        prefix = "\n",
    ) { row ->
        row.joinToString(separator = " ", prefix = "|", postfix = "|")
    }

    operator fun get(point: Point) = cells[point.x][point.y]

    operator fun get(point: Array<Int>) = cells[point[0]][point[1]]

    fun getOrNull(point: Point): Char? =
        if (!cells.isRightMove(Pair(point.x, point.y)) && !isFill) cells[point.x][point.y] else null

    fun setAndCopy(point: Point, c: Char): Board {
        cells.set(point.toPair(), c)
        val newBoard = cells.copy()
        return Board(cells = newBoard)
    }

    constructor(stringBoard: String) : this() {
        stringBoard.lines().forEachIndexed { index, item ->
            cells[index] = stringToArray(item)
        }
    }

    constructor(board: Board) : this(board.cells) {
        board.cells.copy()
    }
}