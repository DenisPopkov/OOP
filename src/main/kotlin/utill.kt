import Game.copy
import Game.game
import TicTacToe.board
import TicTacToe.currentIndex
import TicTacToe.outputConsole
import java.io.PrintStream

<<<<<<< Updated upstream
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

=======
>>>>>>> Stashed changes
fun requestUserPoint(): String {
    if (currentIndex % 2 == 0) println("First player, your X") else println("Second player, your 0")
    print("Enter your point: ")
    return readLine().toString()
}

fun requestCurrentIndex(): String {
    print("Enter your step: ")
    return readLine().toString().uppercase()
}

<<<<<<< Updated upstream
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
=======
fun game(output: PrintStream = outputConsole) {
    run breakable@ {
        while (board.isFill()) {
            do {
                if (board.checkWin().toString().isNotBlank()) {
                    if (currentIndex % 2 != 0) println("First player won") else println("Second player won")
                    return@breakable
                }
                val point = requestUserPoint().pointFromString() ?: Pair(0, 0)
                
                when {
                    !board.isFill() -> {
                        println("Draw!")
                        return@breakable
                    }

                    point.isIncorrectPoint() -> {
                        println("Incorrect step")
                        return@breakable
                    }

                    point.isNotCommand() -> {
                        currentIndex = point.second
                        board = game[point.second]
                        game[point.second].printBoard()
                    }

                    !point.isNotCommand() -> {
                        with (board) {
                            this[point.component1()][point.component2()] = requestCurrentIndex().correctStep().first()
                            printBoard()
                            board.copy()
                            currentIndex++
                        }
                    }
                }
            } while (point.isIncorrectPoint())
        }
>>>>>>> Stashed changes
    }
}