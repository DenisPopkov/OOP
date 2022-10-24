class Game(
    state: State = State()
) {

    private var indexState = 0
    val states = ArrayList<State>()
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

    fun takeBack(shift: Int) = (shift in 0..states.size).apply {
        then {
            indexState = shift
            with (states) {
                dropLastWhile { it != states[shift] }.also { addAll(this) }
            }
        }
    }
}