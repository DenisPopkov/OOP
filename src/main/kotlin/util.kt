import java.io.PrintStream

val outputConsole = PrintStream(System.out, true, "UTF-8")
val winLines = arrayOf(
    arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(0, 2)),
    arrayOf(arrayOf(1, 0), arrayOf(1, 1), arrayOf(1, 2)),
    arrayOf(arrayOf(2, 0), arrayOf(2, 1), arrayOf(2, 2)),
    arrayOf(arrayOf(0, 0), arrayOf(1, 0), arrayOf(2, 0)),
    arrayOf(arrayOf(0, 1), arrayOf(1, 1), arrayOf(2, 1)),
    arrayOf(arrayOf(0, 2), arrayOf(1, 2), arrayOf(2, 2)),
    arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(2, 2)),
    arrayOf(arrayOf(0, 2), arrayOf(1, 1), arrayOf(2, 0))
)
val arrayIndexes = arrayOf(0, 0)
val pointsIndexes = Point(0, 0)

fun getEmptyArray(size: Int) = Array(size) { Array(size) { ' ' } }

fun Array<Array<Char>>.get(point: Pair<Int, Int>): Char = this[point.first][point.second]
fun Array<Array<Char>>.get(point: Array<Int>): Char = this[point[0]][point[1]]
fun Array<Array<Char>>.set(point: Pair<Int, Int>, char: Char) {
    this[point.first][point.second] = char
}

fun String.isSame() = this == "XXX" || this == "000"

fun Array<Array<Char>>.isFill() = flatten().contains(' ')

fun Array<Array<Char>>.isRightMove(point: Pair<Int, Int>) = this[point.first][point.second].toString().isBlank()

fun Point.toPair() = Pair(x, y)

fun Array<Array<Char>>.copy() = map { row -> row.copyOf() }.toTypedArray()

fun Array<Array<Char>>.printBoard(): String {
    val boardString = joinToString(
        separator = "\n",
        prefix = "\n",
    ) { row ->
        row.joinToString(separator = " ", prefix = "|", postfix = "|")
    }

    return boardString
}

fun Int.getTurn() = if (this % 2 == 0) 'X' else '0'

fun printIncorrectStepMessage(output: PrintStream) {
    output.println("Неверные координаты или команда\n")
    output.print("Ваш ход или команда: ")
}

fun List<String>.toIntArray() = this.map { it.toInt() }.toTypedArray()

fun Array<Array<Char>>.isRightStep(step: List<String>) = get(step.toIntArray()).toString().isNotBlank()

fun Int.printWinner() = if (this % 2 == 0) "Первый игрок победил" else "Второй игрок победил"

infix fun Boolean.then(action : () -> Unit): Boolean {
    if (this) action.invoke()
    return this
}