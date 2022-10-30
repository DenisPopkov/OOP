package state

import game.Board
import game.Input
import util.*

class StateXO(board: Board = Board(), private val turn: Char = 'X') : AbstractState(board) {

    override val gameResult: String? = when {
        !board.cells.isFill() -> null
        checkWin().isWhitespace() -> ""
        else -> null
    }

    override fun copy(): AbstractState = StateXO()

    override fun nextState(step: Input.Step): AbstractState {
        val newBoard = board.setAndCopy(point = Point(step.x, step.y), turn)
        return StateXO(board = newBoard)
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