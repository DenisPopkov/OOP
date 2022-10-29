package state

import game.Board
import game.Input

class StateBalda(
    board: Board,
    val turn: Int = 1,
    val words1: List<String> = ArrayList(),
    val words2: List<String> = ArrayList()
) : AbstractState(board) {

    //constructor(): this() {
    //
    //}

    override val gameResult: String?
        get() = null

    override fun checkStep(step: Input.Step): Boolean {
        return if (step.param.isNotEmpty()) super.checkStep(step) else false
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun copy(): AbstractState {
        //val firstWords = words1.copy()
        //val secondWords = words2.copy()
        //if (turn % 2 == 1) {
        //    firstWords[turn] = turn
        //}
        return nextState(Input.Step(0, 0, param = emptyList()))
    }

    override fun nextState(step: Input.Step): AbstractState = copy()
}