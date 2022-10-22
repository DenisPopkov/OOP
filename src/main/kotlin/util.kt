fun Array<Array<Char>>.get(point: Pair<Int, Int>): Char = this[point.first][point.second]
fun Array<Array<Char>>.get(point: Array<Int>): Char = this[point[0]][point[1]]
fun Array<Array<Char>>.set(point: Pair<Int, Int>, char: Char) {
    this[point.first][point.second] = char
}

fun String.isSame() = this == "XXX" || this == "000"

fun Pair<Int, Int>.isCommand() = first == -1

fun Array<Array<Char>>.isFill() = flatten().contains(' ')

fun Array<Array<Char>>.isRightMove(point: Pair<Int, Int>) = this[point.first][point.second].toString().isBlank()