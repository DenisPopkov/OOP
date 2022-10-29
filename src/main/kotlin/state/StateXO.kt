package state

import game.Board
import game.Input
import util.*

class StateXO(board: Board, private val turn: String = "X") : AbstractState(board) {

    override val gameResult: String? = when {
        !board.cells.isFill() -> null
        checkWin().isWhitespace() -> ""
        else -> null
    }

    override fun copy(): AbstractState {
        val newBoard = board.cells.copy()
        return nextState(Input.Step(0, 0))
    }

    override fun nextState(step: Input.Step): AbstractState {
        val newState = copy()
    }

    fun step(point: Point): StateXO? = when {
        board.cells.isRightMove(point.toPair()) -> StateXO(Board(board.cells), turn)
        else -> null
    }

    override fun toString() = when (gameResult == null) {
        !board.cells.isFill().thenIfNotTrue { board.cells.size.printWinner() } -> "Ничья"
        else -> ""
    }

    fun checkWin(): Char {
        var line = " "
        winLines.forEach { winningLine ->
            winningLine.forEach {
                line += board.cells[it[0]][it[1]]
            }.also {
                line = line.trim()
                if (line.isSame()) return line.first() else line = " "
            }
        }

        return line.first()
    }
}