package game

import state.AbstractState
import state.StateBalda
import state.StateXO
import util.*

class MultiGame(private val abstractState: AbstractState = StateXO()) {

    private var indexState = 0

    val states = ArrayList<AbstractState>()

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

    private fun getTurn() = if (indexState % 2 == 0) 'X' else '0'

    private fun getBaldaPlayerIndex() = if (indexState % 2 != 0) 1 else 2

    fun step(point: Point) {
        when (abstractState) {
            is StateXO -> StateXO(turn = getTurn()).nextState(Input.Step(x = point.x, y = point.y))
            else -> {
                println("Введите слово")
                val userWord = listOf(readln())
                if (getBaldaPlayerIndex() == 1) {
                    StateBalda(turn = getBaldaPlayerIndex(), words1 = userWord).nextState(
                        Input.Step(
                            x = point.x,
                            y = point.y,
                            param = userWord
                        )
                    )
                } else {
                    StateBalda(turn = getBaldaPlayerIndex(), words2 = userWord).nextState(
                        Input.Step(
                            x = point.x,
                            y = point.y,
                            param = userWord
                        )
                    )
                }
            }
        }.let { updateGameResult(it) }
    }

    fun takeBack(shift: Int) {
        indexState = shift
        states.addAll(states.slice(0..shift))
        states.forEach { it.board.cells.printBoard() }
    }
}