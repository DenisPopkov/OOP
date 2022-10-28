sealed interface Input {

    class Exit(): Input

    class TakeBack(val shift: Int): Input

    companion object {
        private fun String.stringToArray() = toCharArray().toTypedArray().toIntArray()
        fun parse(string: String) = when {
            string.stringToArray().toPoint().isIncorrectStep() -> Exit()
            string.stringToArray().first() == COMMAND -> TakeBack(0)
            else -> Step(0, 0)
        }
    }
}