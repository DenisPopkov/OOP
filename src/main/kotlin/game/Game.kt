package game

import state.StateBalda
import state.StateXO
import util.*

class Game {

    private var indexState = 0
    val statesXO = ArrayList<StateXO>()
    val statesBalda = ArrayList<StateBalda>()
    var gameOver = false

    init {
        statesXO.add(StateXO(board = Board()))
        statesBalda.add(StateBalda(board = Board()))
    }

    override fun toString() = statesXO.last().board.cells.printBoard()

    private fun updateGameResult(newState: StateXO) {
        gameOver = newState.gameResult == null
        gameOver.then { println(newState.toString()) }
        indexState++
    }

    private fun copy(point: Point): Array<Array<Char>> {
        val newBoard = statesXO.last().board.cells.copy()
        newBoard[point.x][point.y] = indexState.getTurn()
        return newBoard
    }

    fun step(point: Point) {
        val newBoard = StateXO(Board(copy(point)))
        statesXO.add(newBoard)
        updateGameResult(newBoard)
    }

    fun takeBack(shift: Int) {
        indexState = shift
        statesXO.addAll(statesXO.slice(0..shift))
    }
}