import TicTacToe.board
import TicTacToe.boardSize
import TicTacToe.currentIndex
import TicTacToe.outputConsole
import TicTacToe.winLines
import java.io.PrintStream

fun String.isSame() = length == boardSize && reversed() == this

fun String.correctStep() = if (currentIndex % 2 == 0 && this != "X") "0" else this

fun Pair<Int, Int>.isIncorrectPoint() = (toList().sum() !in 0..4 || board[first][second].toString().isNotBlank()) && !isNotCommand()

fun Pair<Int, Int>.isNotCommand() = first == -1

fun Array<Array<Char>>.get(point: Pair<Int, Int>): Char = this[point.first][point.second]
fun Array<Array<Char>>.get(point: Array<Int>): Char = this[point.component1()][point.component2()]
fun Array<Array<Char>>.set(point: Pair<Int, Int>, char: Char) {
    this[point.first][point.second] = char
}

fun Array<Array<Char>>.printBoard() {
    val boardString = joinToString(
        separator = "\n",
        prefix = "\n",
    ) { row ->
        row.joinToString(" ", prefix = "|", postfix = "|")
    }

    println(boardString)
}

fun Array<Array<Char>>.checkWin(): Char {
    var line = ""
    run array@{
        winLines.forEach { array ->
            array.forEach {
                line += this[it.component1()][it.component2()]
            }

            line = line.replace(" ", "")

            when {
                line.isSame() -> return@array
                else -> line = " "
            }
        }
    }

    return line.first()
}

fun Array<Array<Char>>.isFill() = flatten().contains(' ')
fun Array<Array<Char>>.isRightMove(point: Pair<Int, Int>) = this[point.component1()][point.component2()].toString().isBlank()

fun String.pointFromString(): Pair<Int, Int>? {
   return Pair(
        trim().split(" ").component1().toIntOrNull() ?: return null,
        split(" ").component2().toIntOrNull() ?: return null
    )
}