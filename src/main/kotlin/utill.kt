import Game.copy
import Game.game
import TicTacToe.board
import TicTacToe.currentIndex
import TicTacToe.outputConsole
import TicTacToe.winLines
import java.io.PrintStream

fun isFill(board: Array<Array<Char>>) = board.flatten().contains(' ')

fun checkWin(board: Array<Array<Char>>): Char {
    var line = " "
    var counter = 0
    while (counter < winLines.size) {
        winLines.forEach { winningLine ->
            winningLine.forEach {
                line += board[it.component1()][it.component2()]
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

fun isRightMove(board: Array<Array<Char>>, point: Array<Int>) =
    board[point.component1()][point.component2()].toString().isBlank()


fun requestUserPoint(): String {
    if (currentIndex % 2 == 0) println("First player, your X") else println("Second player, your 0")
    print("Enter your point: ")
    return readLine().toString()
}

fun requestCurrentIndex(): String {
    print("Enter your step: ")
    return readLine().toString().uppercase()
}

fun game(output: PrintStream = outputConsole): Unit {
    while (isFill(board)) {
        do {
            if (checkWin(board).toString().isNotBlank()) {
                if (currentIndex % 2 != 0) println("First player won") else println("Second player won")
                return
            }
            val point = requestUserPoint().pointFromString() ?: Pair(0, 0)
            when {
                !isFill(board) -> {
                    println("Draw!")
                    return
                }

                point.isIncorrectPoint() -> {
                    println("Incorrect step")
                    return
                }

                point.isNotCommand() -> {
                    currentIndex = point.second
                    board = game[point.second]
                    game[point.second].printBoard()
                }

                !point.isNotCommand() -> {
                    with(board) {
                        this[point.component1()][point.component2()] = requestCurrentIndex().correctStep().first()
                        printBoard()
                        board.copy()
                        currentIndex++
                    }
                }
            }

            val userInput = requestUserStep().correctStep()
            board[point.component1()][point.component2()] = userInput.first()
            board.printBoard()
            currentIndex++
        } while (point.isIncorrectPoint())
    }
}