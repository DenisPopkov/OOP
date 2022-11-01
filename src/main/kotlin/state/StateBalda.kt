package state

import game.Board
import game.Input
import game.MultiGame
import util.Point

class StateBalda(
    board: Board = Board(),
    private val turn: Int = 1,
    private val words1: List<String> = ArrayList(),
    private val words2: List<String> = ArrayList()
) : AbstractState(board) {

    private val game = MultiGame(abstractState = StateBalda())
    override val gameResult: String?
        get() = null

    private fun getWord() = if (turn == 1) words1 else words2
    override fun checkStep(step: Input.Step): Boolean {
        return if (step.param.isNotEmpty()) super.checkStep(step) else false
    }

    override fun copy(): AbstractState = StateBalda()
    override fun nextState(step: Input.Step): AbstractState {
        val word = getWord()
        word.forEach { println(it) }
        val newBoard = board.setStringAndCopy(word = word.toTypedArray(), step.point)
        return StateBalda(board = newBoard)
    }

    fun step(point: Point) {
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
        game.updateGameResult(StateBalda())
    }

    private fun getBaldaPlayerIndex() = if (game.indexState % 2 != 0) 1 else 2
}