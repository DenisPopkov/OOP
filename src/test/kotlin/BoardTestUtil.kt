import java.io.ByteArrayOutputStream
import java.io.PrintStream

object BoardTestUtil {
    val outputBuffer = ByteArrayOutputStream()
    val output = PrintStream(outputBuffer)
    val board3x3Array = arrayOf(
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', 'X', ' '),
        arrayOf(' ', ' ', '0')
    )
    val board4x3Array = arrayOf(
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', 'X', ' '),
        arrayOf('X', ' ', '0'),
        arrayOf('0', 'X', '0')
    )
    val winBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', 'X', ' '),
        arrayOf(' ', ' ', 'X')
    )
    val notWinCombinationsOnBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', 'X')
    )
    val isFillBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', 'X')
    )
    val isRightMoveBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' ')
    )
}