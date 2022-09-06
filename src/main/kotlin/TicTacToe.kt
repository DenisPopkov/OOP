import java.io.ByteArrayOutputStream
import java.io.PrintStream

object TicTacToe {

    const val boardSize = 3
    var userStep = 0
    val outputConsole = PrintStream(System.out, true, "UTF-8")
    var outputBuffer = ByteArrayOutputStream()
    var board = Array(boardSize) { Array(boardSize) { ' ' } }
    val winLines = arrayOf(
        arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(0, 2)),
        arrayOf(arrayOf(1, 0), arrayOf(1, 1), arrayOf(1, 2)),
        arrayOf(arrayOf(2, 0), arrayOf(2, 1), arrayOf(2, 2)),
        arrayOf(arrayOf(0, 0), arrayOf(1, 0), arrayOf(2, 0)),
        arrayOf(arrayOf(0, 1), arrayOf(1, 1), arrayOf(2, 1)),
        arrayOf(arrayOf(0, 2), arrayOf(1, 2), arrayOf(2, 2)),
        arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(2, 2))
    )
}