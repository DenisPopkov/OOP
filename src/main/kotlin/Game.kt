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

    private fun updateGameResult(newState: State) {
        gameOver = newState.gameResult == null
        gameOver.then { println(newState.toString()) }
        indexState++
    }

    private fun copy(point: Point): Array<Array<Char>> {
        val newBoard = states.last().board.cells.copy()
        newBoard[point.x][point.y] = indexState.getTurn()
        return newBoard
    }

    fun step(point: Point) = states.last().board.cells.isRightMove(point.toPair()).also {
        with(State(Board(copy(point)))) {
            states.add(this)
            step(point)
            updateGameResult(this)
        }
    }

    fun takeBack(shift: Int) = (shift in 0..states.size).apply {
        then {
            indexState = shift
            with(states) {
                dropLastWhile { it != states[shift] }.also { addAll(this) }
            }
        }
    }
}