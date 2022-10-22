import TicTacToe.outputConsole
import TicTacToe.winLines
import java.io.PrintStream

fun String.isSame() = (this == "XXX") || (this == "000")

fun Pair<Int, Int>.isNotCommand() = first == -1

fun Array<Array<Char>>.get(point: Pair<Int, Int>): Char = this[point.first][point.second]
fun Array<Array<Char>>.get(point: Array<Int>): Char = this[point[0]][point[1]]
fun Array<Array<Char>>.set(point: Pair<Int, Int>, char: Char) {
    this[point.first][point.second] = char
}

fun Array<Array<Char>>.printBoard(out: PrintStream = outputConsole) {
    val boardString = joinToString(
        separator = "\n",
        prefix = "\n",
    ) { row ->
        row.joinToString(separator = " ", prefix = "|", postfix = "|")
    }

    out.println(boardString)
}

fun Array<Array<Char>>.checkWin(): Char {
    var line = " "
    var counter = 0
    while (counter < winLines.size) {
        winLines.forEach { winningLine ->
            winningLine.forEach {
                line += this[it[0]][it[1]]
            }
            line = line.replace(" ", "")
            if (line.isSame()) {
                return line.first()
            } else {
                line = " "
            }
        }
        counter++
    }
    return line.first()
}

fun Array<Array<Char>>.isFill() = flatten().contains(' ')
fun Array<Array<Char>>.isRightMove(point: Pair<Int, Int>) = this[point.first][point.second].toString().isBlank()

fun String.pointFromString(): Pair<Int, Int>? {
   return Pair(
        trim().split(" ")[0].toIntOrNull() ?: return null,
        split(" ")[1].toIntOrNull() ?: return null
    )
}