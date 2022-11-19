package game

import state.EAbstractState
import state.EStateBalda
import state.EStateXO
import util.*

class EMultiGame(private val abstractState: EAbstractState = EStateXO()) {

    private var indexState = 0

    val states: StateList<EAbstractState> = StateList(init = abstractState)

    var gameOver = false

    init {
        states.add(abstractState)
    }

    override fun toString() = states.state.board.cells.printBoard()

    private fun updateGameResult(newState: EAbstractState) {
        val mergedArray = newState.board.cells.merge(states.state.board.cells)
        val mergedState = EStateXO(board = Board(mergedArray))
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
            is EStateXO -> EStateXO(turn = getTurn()).nextState(Input.Step(x = point.x, y = point.y))
            else -> {
                println("Введите слово")
                val userWord = listOf(readln())
                if (getBaldaPlayerIndex() == 1) {
                    EStateBalda(turn = getBaldaPlayerIndex(), words1 = userWord).nextState(
                        Input.Step(
                            x = point.x,
                            y = point.y,
                            param = userWord
                        )
                    )
                } else {
                    EStateBalda(turn = getBaldaPlayerIndex(), words2 = userWord).nextState(
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
        states.move(shift)
        states.state.board.cells.printBoard()
    }
}