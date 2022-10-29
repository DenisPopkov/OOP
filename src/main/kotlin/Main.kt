import game.Game
import game.Input
import util.outputConsole
import util.printIncorrectStepMessage
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

fun game(
    inputStream: InputStream = System.`in`,
    output: PrintStream = outputConsole
) {
    val reader = BufferedReader(inputStream.reader())
    val game = Game()
    output.println(game)

    do {
        output.print("Ваш ход или команда: ")
        var step = reader.readLine()

        when (val parsedInput = Input.parse(step)) {
            is Input.Exit -> {
                while (Input.parse(step) is Input.Exit) {
                    printIncorrectStepMessage()
                    step = reader.readLine()
                }
            }
            is Input.TakeBack -> game.takeBack(parsedInput.shift)
            is Input.Step -> game.step(parsedInput.point)
        }

        output.println(game)

    } while (!game.gameOver)
}

fun main() {
    println("Игра крестики-нолики\nПопков Денис, 21м\n")
    game()
}