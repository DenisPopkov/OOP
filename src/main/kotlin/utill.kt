import TicTacToe.board
import TicTacToe.boardSize
import TicTacToe.currentIndex
import TicTacToe.outputConsole
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

fun isFill(board: Array<Array<Char>>) = board.flatten().contains(' ')

fun isRightMove(board: Array<Array<Char>>, point: Array<Int>) =
    board[point[0]][point[1]].toString().isBlank()


fun requestUserPoint(reader: BufferedReader): String {
    if (currentIndex % 2 == 0)
        println("First player, your 0")
    else
        println("Second player, your X")
    print("Enter your point: ")
    return reader.readLine()
}

fun game(inputStream: InputStream = System.`in`, output: PrintStream = outputConsole) {
    board = Array(boardSize) { Array(boardSize) { ' ' } }
    currentIndex = 0
    val reader = BufferedReader(inputStream.reader())
    var userInput: String
    do {
        if (board.checkWin().toString().isNotBlank()) {
            if (currentIndex % 2 != 0) {
                output.print("First player won")
                print("\n")
                return
            } else {
                output.print("Second player won")
                print("\n")
                return
            }
        }
        var point = requestUserPoint(reader).pointFromString()
        while ((point!!.first < 0 || point.second < 0) || (point.first > 2 || point.second > 2)) {
            output.print("Incorrect Step, true again")
            point = requestUserPoint(reader).pointFromString()
        }
        userInput = if (currentIndex % 2 != 0) "X" else "0"
        when {
            !isFill(board) -> {
                output.print("Draw!")
                print("\n")
                return
            }

            !board.isRightMove(point) -> {
                output.print("Incorrect step!")
                print("\n")
                return
            }
        }

        board[point.first][point.second] = userInput.first()
        board.printBoard(output)
        currentIndex++
    } while (point?.toList()?.sum() in 0..4 || board[0][1].toString().isNotBlank() || isFill(board))
}