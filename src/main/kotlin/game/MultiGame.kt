package game

import state.AbstractState
import state.StateXO
import util.*

class MultiGame(private val abstractState: AbstractState = StateXO()) {

    var indexState = 0

    val states = ArrayList<AbstractState>()

    var gameOver = false

    init {
        states.add(abstractState)
    }

    override fun toString() = states.last().board.cells.printBoard()

    fun updateGameResult(newState: AbstractState) {
        val mergedArray = newState.board.cells.merge(states.last().board.cells)
        val mergedState = StateXO(board = Board(mergedArray))
        states.add(mergedState)
        gameOver = mergedState.gameResult == null
        if (gameOver) {
            if (!mergedArray.isFill()) println("Ничья") else println(mergedArray.size.getWinner())
        }
        indexState++
    }

    fun takeBack(shift: Int) {
        indexState = shift
        states.addAll(states.slice(0..shift))
        states.forEach { it.board.cells.printBoard() }
    }
}