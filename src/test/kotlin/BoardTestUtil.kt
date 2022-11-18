import java.io.ByteArrayOutputStream

object BoardTestUtil {
    val outputBuffer = ByteArrayOutputStream()
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
    val isFillBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', 'X')
    )
    val setAndCopyArray = arrayOf(
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', 'X')
    )
    val isRightMoveBoardArray = arrayOf(
        arrayOf('X', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' ')
    )
}