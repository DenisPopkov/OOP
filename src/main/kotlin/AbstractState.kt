abstract class AbstractState(
    val board: Board
) {

    abstract val gameResult: String?

    abstract fun copy(): AbstractState

    fun step(step: Input.Step): AbstractState? = checkStep(step).invokeOrNull { nextState(step) }

    open fun checkStep(step: Input.Step): Boolean {
        val isFill = board.cells.isFill()
        val isGameOver = gameResult == null
        val isIncorrectStep = step.point.isIncorrectStep()

        return isFill && isGameOver && isIncorrectStep
    }

    abstract fun nextState(step: Input.Step): AbstractState
}