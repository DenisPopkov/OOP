import game.Board
import game.Input
import game.MultiGame
import state.AbstractState
import state.StateBalda
import state.StateXO
import util.GameCommand
import util.baldaArray
import util.outputConsole
import util.printIncorrectStepMessage
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

private fun getGameCommand() {
    println("0: Крестики-нолики\n1: Балда")
    when (readln().toInt()) {
        GameCommand.GAME_XO.ordinal -> StateXO()
        GameCommand.GAME_BALDA.ordinal -> StateBalda(Board(board = baldaArray, boardSize = 5))
        else -> null
    }.let { if (it != null) game(abstractState = it) else println("Некорректный номер игры") }
}

fun game(
    inputStream: InputStream = System.`in`,
    output: PrintStream = outputConsole,
    abstractState: AbstractState
) {
    val reader = BufferedReader(inputStream.reader())
    val game = MultiGame(abstractState)
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
            is Input.Step -> StateXO().step(parsedInput.point).also { StateBalda().step(parsedInput.point) }
        }

        output.println(game)

    } while (!game.gameOver)
}

fun main() {
    getGameCommand()
}