import game.Board
import game.Input
import game.EMultiGame
import state.EAbstractState
import state.EStateBalda
import state.EStateXO
import util.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.PrintStream

private fun getGameCommand() {
    println("0: Крестики-нолики\n1: Балда")
    when (readln().toInt()) {
        Turn.GAME_XO.ordinal -> EStateXO()
        Turn.GAME_BALDA.ordinal -> EStateBalda(Board(board = baldaArray, boardSize = 5))
        else -> null
    }.let { if (it != null) game(abstractState = it) else println("Некорректный номер игры") }
}

fun game(
    inputStream: InputStream = System.`in`,
    output: PrintStream = outputConsole,
    abstractState: EAbstractState
) {
    val reader = BufferedReader(inputStream.reader())
    val game = EMultiGame(abstractState)
    output.println(game)

    do {
        output.print("Ваш ход или команда: ")
        var step = reader.readLine()

        try {
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
        } catch (e: GameException) {
            output.println(e.message)
        }

        output.println(game)

    } while (!game.gameOver)
}

fun main() {
    getGameCommand()
}