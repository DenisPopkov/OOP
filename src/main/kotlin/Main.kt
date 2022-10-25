import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

fun game(
    inputStream: InputStream = System.`in`,
    output: PrintStream = outputConsole
) {
    val reader = BufferedReader(inputStream.reader())
    val game = Game()
    var finish = false
    val lastBoard = game.states.last().board.cells
    output.println(game)

    do {
        output.print("Ваш ход или команда: ")
        var step = reader.readLine().split(" ")
        val isIncorrectStep = step.toIntArray().isIncorrectStep()

        while (isIncorrectStep.thenIfNotTrue { lastBoard.isRightStep(step) } && step.isCommand()) {
            printIncorrectStepMessage()
            step = reader.readLine().split(" ")
        }

        if (step.size != 2) {
            finish = true
        } else {
            val x = step[0].toIntOrNull()
            val y = step[1].toIntOrNull()

            if (x == null || y == null) {
                output.println("Неверные координаты или команда\n")
            } else {
                if (x == -1) {
                    if (game.takeBack(y)) output.println(game) else output.println("Неправильная команда\n")
                } else {
                    game.step(Point(x, y))
                    output.println(game)
                }
            }
        }
    } while (!finish && !game.gameOver)
}

fun main() {
    println("Игра крестики-нолики\nПопков Денис, 21м\n")
    game()
}