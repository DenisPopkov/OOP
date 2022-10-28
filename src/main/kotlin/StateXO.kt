class StateXO(board: Board, val turn: String = "X") : AbstractState(board) {

    private fun getTotalStepText() = "Сейчас $turn ход:\n"

    override val gameResult: String? = when {
        !board.cells.isFill() -> null
        checkWin().isWhitespace() -> getTotalStepText()
        else -> null
    }

    override fun copy(): AbstractState {
        val newBoard = board.cells.copy()
        return nextState(Input.Step(0, 0))
    }

    override fun nextState(step: Input.Step): AbstractState = copy()

    override fun toString() = when (gameResult == null) {
        !board.cells.isFill().thenIfNotTrue { board.cells.size.printWinner() } -> "Ничья"
        else -> ""
    }

    private fun checkWin(): Char {
        var line = " "
        winLines.forEach { winningLine ->
            winningLine.forEach {
                line += board.cells[it[0]][it[1]]
            }.also {
                line = line.trim()
                if (line.isSame()) return line.first() else line = " "
            }
        }

        return line.first()
    }
}