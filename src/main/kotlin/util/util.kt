package util

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
val baldaArray = arrayOf(
    arrayOf(' ', ' ', ' ', ' ', ' '),
    arrayOf(' ', ' ', ' ', ' ', ' '),
    arrayOf('И', 'С', 'К', 'Р', 'А'),
    arrayOf(' ', ' ', ' ', ' ', ' '),
    arrayOf(' ', ' ', ' ', ' ', ' ')
)
const val COMMAND = -1
val arrayIndexes = arrayOf(0, 0)
val pointsIndexes = Point(0, 0)

fun getEmptyArray(size: Int) = Array(size) { Array(size) { ' ' } }

fun Array<Array<Char>>.get(point: Pair<Int, Int>): Char = this[point.first][point.second]

fun Array<Array<Char>>.get(point: Array<Int>): Char = this[point[0]][point[1]]
fun Array<Array<Char>>.set(point: Pair<Int, Int>, char: Char) {
    this[point.first][point.second] = char
}

fun Array<Array<Char>>.set(s: Array<String>, step: Point) {
    val charArray = s.first().map { it }
    for ((index, i) in (step.x downTo 0).withIndex()) {
        this[i][step.y] = charArray[index].uppercaseChar()
    }
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

fun printIncorrectStepMessage() {
    println("Неверные координаты или команда\n")
    print("Ваш ход или команда: ")
}

fun Point.isIncorrectStep() = (x < 0 || y < 0) || (x > 2 || y > 2)

fun Int.getWinner() = if (this % 2 == 0) "Первый игрок победил" else "Второй игрок победил"

fun List<Int>.toPoint() = Point(this[0], this[1])

infix fun <T> Boolean.invokeOrNull(action : () -> Unit): T? {
    if (this) action.invoke()
    return null
}

fun Array<Array<Char>>.merge(anotherArray: Array<Array<Char>>) = anotherArray.mapIndexed { index, array ->
    array.mapIndexed { index2, c ->
        if (c == ' ') this[index][index2] else c
    }.toTypedArray()
}.toTypedArray()