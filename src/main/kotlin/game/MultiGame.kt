package game

import state.AbstractState
import state.StateBalda
import state.StateXO
import util.*

class MultiGame(private val abstractState: AbstractState) {

    private var indexState = 0

    val states = ArrayList<AbstractState>()

    //val boardSize = abstractState.board.cells.size
    var gameOver = false

    init {
        states.add(abstractState)
    }

    override fun toString() = states.last().board.cells.printBoard()

    private fun updateGameResult(newState: AbstractState) {
        states.add(StateXO(board = Board(newState.board.cells.copy())))
        gameOver = newState.gameResult == null
        gameOver.then { println(newState.toString()) }
        indexState++
    }

    fun step(point: Point) {
        when (abstractState) {
            is StateXO -> StateXO().nextState(Input.Step(x = point.x, y = point.y)).apply { updateGameResult(this) }
            is StateBalda -> StateBalda().nextState(Input.Step(x = point.x, y = point.y)).apply { updateGameResult(this) }
        }
    }

    fun takeBack(shift: Int) {
        indexState = shift
        states.addAll(states.slice(0..shift))
    }
}