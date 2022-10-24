class State(
    val board: Board = Board(),
    val turn: Char = 'X',
) {

    val gameResult: String? = when {
        !board.cells.isFill() -> null
        checkWin() == ' ' -> getTotalStepText()
        else -> null
    }

    override fun toString() = if (gameResult == null) {
        if (!board.cells.isFill()) "Ничья" else board.cells.size.printWinner()
    } else { "" }

    private fun getTotalStepText() = "Сейчас $turn ход:\n"

    fun step(point: Point): State? = when {
        board.cells.isRightMove(point.toPair()) -> State(Board(board.cells), turn)
        else -> null
    }

    fun checkWin(): Char {
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