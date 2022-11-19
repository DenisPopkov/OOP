package state

import game.Board
import game.Input
import util.WrongStepException
import util.isFill
import util.isIncorrectStep

abstract class EAbstractState(
    val board: Board
) {

    abstract val gameResult: String?

    abstract fun copy(): EAbstractState

    fun step(step: Input.Step): EAbstractState = nextState(step)

    open fun checkStep(step: Input.Step) {
        val isFill = board.cells.isFill()
        val isGameOver = gameResult == null
        val isIncorrectStep = step.point.isIncorrectStep()
        val stepPredicate = isFill && isGameOver && isIncorrectStep

        if (!stepPredicate) throw WrongStepException().throwException(step.point)
    }

    abstract fun nextState(step: Input.Step): EAbstractState
}