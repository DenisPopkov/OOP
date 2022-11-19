package state

import game.Board
import game.Input
import util.WrongStepException

class EStateBalda(
    board: Board = Board(),
    private val turn: Int = 1,
    private val words1: List<String> = ArrayList(),
    private val words2: List<String> = ArrayList()
) : EAbstractState(board) {

    override val gameResult: String?
        get() = null

    private fun getWord() = if (turn == 1) words1 else words2
    override fun checkStep(step: Input.Step) {
        if (step.param.isEmpty()) throw WrongStepException().throwException(step.point)
    }

    override fun copy(): EAbstractState = EStateBalda()
    override fun nextState(step: Input.Step): EAbstractState {
        val word = getWord()
        word.forEach { println(it) }
        val newBoard = board.setStringAndCopy(word = word.toTypedArray(), step.point)
        return EStateBalda(board = newBoard)
    }
}