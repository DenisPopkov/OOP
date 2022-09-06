import TicTacToe.board
import TicTacToe.boardSize
import TicTacToe.userStep

fun String.replaceUnusualSymbols() =
    replace(", ", "").replace("[", "").replace("]", "")

fun String.isSame() = length == boardSize && reversed() == this

fun String.correctStep() = if (userStep % 2 == 0 && this != "X") "0" else this

fun Array<Int>.isIncorrectPoint() = sum() !in 0..4 || board[component1()][component2()].toString().isNotBlank()