import TicTacToe.boardSize
import TicTacToe.outputConsole
import TicTacToe.winLines
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

fun Array<Array<Char>>.printBoard(out: PrintStream = outputConsole) {
    val boardString = joinToString(
        separator = "\n",
        prefix = "\n",
    ) { row ->
        row.joinToString(separator = " ", prefix = "|", postfix = "|")
    }

    out.println(boardString)
}

fun String.pointFromString(): Pair<Int, Int>? {
    return Pair(
        trim().split(" ")[0].toIntOrNull() ?: return null,
        split(" ")[1].toIntOrNull() ?: return null
    )
}

fun Array<Array<Char>>.checkWin(): Char {
    var line = " "
    winLines.forEach { winningLine ->
        winningLine.forEach {
            line += this[it[0]][it[1]]
        }.also {
            line = line.trim()
            if (line.isSame()) return line.first() else line = " "
        }
    }

    return line.first()
}

fun requestUserPoint(reader: BufferedReader): String {
    print("Enter your point: ")
    return reader.readLine()
}

fun game(inputStream: InputStream = System.`in`, output: PrintStream = outputConsole) {
    val board = Array(boardSize) { Array(boardSize) { ' ' } }
    var currentIndex = 0
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