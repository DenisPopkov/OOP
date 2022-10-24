class Game(
    state: State = State()
) {

    val states = ArrayList<State>()
    var indexState = 0
    var gameOver = false

    init {
        states.add(state)
    }

    override fun toString() = states.last().board.cells.printBoard()

    fun step(point: Point) = states.last().board.cells.isRightMove(point.toPair()).also {
        val totalTurn = indexState.getTurn()
        val newBoard = states.last().board.cells.copy()
        newBoard[point.x][point.y] = totalTurn
        val newState = State(board = Board(newBoard), turn = totalTurn)
        states.add(newState)
        newState.step(point)
        gameOver = newState.gameResult == null
        if (gameOver) println(newState.toString())
        indexState++
    }

    fun takeBack(shift: Int): Boolean {
        val prevStatesSize = states.size
        val shiftPredicate = shift in 0..prevStatesSize
        if (shiftPredicate) {
            indexState = shift
            val state = states.dropLastWhile { it != states[shift] }
            states.addAll(state)
        }

        return shiftPredicate
    }
}