class StateBalda(
    board: Board,
    val turn: Int = 1,
    val words1: List<String> = ArrayList(),
    val words2: List<String> = ArrayList()
) : AbstractState(board) {

    override val gameResult: String?
        get() = null

    override fun copy(): AbstractState {
        TODO("Not yet implemented")
    }

    override fun nextState(step: Step): AbstractState {
        TODO("Not yet implemented")
    }
}