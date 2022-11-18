package state

import game.Board
import game.Input

class StateBalda(
    board: Board = Board(),
    private val turn: Int = 1,
    private val words1: List<String> = ArrayList(),
    private val words2: List<String> = ArrayList()
) : AbstractState(board) {

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
}