package game

import state.AbstractState
import state.StateBalda
import state.StateXO
import util.*

class MultiGame(private val abstractState: AbstractState) {

    private var indexState = 0

    private val states = ArrayList<AbstractState>()

    var gameOver = false

    init {
        states.add(abstractState)
    }

    override fun toString() = states.last().board.cells.printBoard()

    private fun updateGameResult(newState: AbstractState) {
        val mergedArray = newState.board.cells.merge(states.last().board.cells)
        val mergedState = StateXO(board = Board(mergedArray))
        states.add(mergedState)
        gameOver = mergedState.gameResult == null
        if (gameOver) {
            if (!mergedArray.isFill()) println("Ничья") else println(mergedArray.size.getWinner())
        }
        indexState++
    }

    fun step(point: Point) {
        when (abstractState) {
            is StateXO -> StateXO(turn = getTurn()).nextState(Input.Step(x = point.x, y = point.y)).apply { updateGameResult(this) }
            is StateBalda -> StateBalda().nextState(Input.Step(x = point.x, y = point.y)).apply { updateGameResult(this) }
        }
    }

    fun takeBack(shift: Int) {
        indexState = shift
        states.addAll(states.slice(0..shift))
    }

    private fun getTurn() = if (indexState % 2 == 0) 'X' else '0'
}