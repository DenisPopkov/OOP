package state

import game.Board
import game.Input
import game.MultiGame
import util.Point
import util.isSame
import util.winLines

class StateXO(board: Board = Board(), private val turn: Char = 'X') : AbstractState(board) {

    private val game = MultiGame(abstractState = StateXO())

    override val gameResult: String? = when {
        board.checkWin().isWhitespace() -> ""
        else -> null
    }

    override fun copy(): AbstractState = StateXO()

    override fun nextState(step: Input.Step): AbstractState {
        val newBoard = board.setAndCopy(point = Point(step.x, step.y), turn)
        return StateXO(board = newBoard)
    }

    private fun Board.checkWin(): Char {
        var line = " "
        winLines.forEach { winningLine ->
            winningLine.forEach {
                line += cells[it[0]][it[1]]
            }.also {
                line = line.trim()
                if (line.isSame()) return line.first() else line = " "
            }
        }

        return line.first()
    }

    fun step(point: Point) {
        StateXO(turn = getTurn()).nextState(Input.Step(x = point.x, y = point.y))
    }

    private fun getTurn() = if (game.indexState % 2 == 0) 'X' else '0'
}