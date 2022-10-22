import TicTacToe.board
import TicTacToe.boardSize
import TicTacToe.currentIndex
import TicTacToe.outputConsole
import TicTacToe.winLines
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

fun String.isSame() = this == "XXX" || this == "000"

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

fun requestUserPoint(reader: BufferedReader): String {
    when {
        currentIndex % 2 == 0 -> println("First player, your 0")
        else -> println("Second player, your X")
    }.let { print("Enter your point: ") }

    return reader.readLine()
}

fun Pair<Int, Int>.checkIncorrect() = (first < 0 || second < 0) || (first > 2 || second > 2)

fun game(inputStream: InputStream = System.`in`, output: PrintStream = outputConsole) {
    board = Array(boardSize) { Array(boardSize) { ' ' } }
    currentIndex = 0
    var userInput: String
    val reader = BufferedReader(inputStream.reader())
    do {
        if (board.checkWin().toString().isNotBlank()) {
            if (currentIndex % 2 != 0) {
                output.print("First player won")
                print("\n")
                break
            } else {
                output.print("Second player won")
                print("\n")
                break
            }
        } else if (!board.isFill()) {
            output.print("Draw!")
            print("\n")
            break
        }

        var point: Pair<Int, Int>?
        do {
            point = requestUserPoint(reader).pointFromString()
        } while (point != null && !(point.isNotCommand() || board.isRightMove(point)))

        userInput = if (currentIndex % 2 != 0) "X" else "0"

        point?.let { board[it.first][it.second] = userInput.first() }
        board.printBoard(output)
        currentIndex++
    } while (point?.toList()?.sum() in 0..4 || board[0][1].toString().isNotBlank() || board.isFill())
}