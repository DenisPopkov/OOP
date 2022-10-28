sealed interface Input {

    object Exit : Input

    class TakeBack(val shift: Int): Input

    class Step(val x: Int, val y: Int, val param: List<String> = emptyList()): Input {
        val point = Point(x, y)
    }

    companion object {
        private fun String.stringToArray() = toCharArray().toTypedArray().toIntArray()
        fun parse(string: String) = when {
            string.stringToArray().toPoint().isIncorrectStep() -> Exit
            string.stringToArray().first() == COMMAND -> TakeBack(0)
            else -> Step(0, 0)
        }
    }
}