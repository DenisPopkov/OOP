import TicTacToe.board
import TicTacToe.outputConsole
import TicTacToe.userStep
import TicTacToe.winLines
import java.io.PrintStream

fun printBoard(out: PrintStream = outputConsole) {
    board.indices.forEach {
        out.println("|${board[it].contentToString().replaceUnusualSymbols()}|")
    }
}

fun isFill(board: Array<Array<Char>>) = board.flatten().contains(' ')

fun pointFromString(string: String) =
    arrayOf(string.trim().split(" ").component1().toInt(), string.split(" ").component2().toInt())

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

fun isRightMove(board: Array<Array<Char>>, point: Array<Int>) = board[point.component1()][point.component2()].toString().isBlank()

fun requestUserPoint(): String {
    if (userStep % 2 == 0) println("First player, your X") else println("Second player, your 0")
    print("Enter your point: ")
    return readLine().toString()
}

fun requestUserStep(): String {
    print("Enter your step: ")
    return readLine().toString().uppercase()
}

fun game(output: PrintStream = outputConsole): Unit {
    while (isFill(board)) {
        do {
            if (checkWin(board).toString().isNotBlank()) {
                if (userStep % 2 != 0) println("First player won") else println("Second player won")
                return
            }
            val point = pointFromString(requestUserPoint())
            when {
                !isFill(board) -> {
                    println("Draw!")
                    return
                }

                point.isIncorrectPoint() -> {
                    println("Incorrect step")
                    return
                }
            }

            val userInput = requestUserStep().correctStep()
            board[point.component1()][point.component2()] = userInput.first()
            printBoard()
            userStep++
        } while (point.isIncorrectPoint())
    }
}