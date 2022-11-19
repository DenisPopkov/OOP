package util

class WrongStepException {
    fun throwException(point: Point) = when {
        point.isIncorrectStep() -> NoCellException
        else -> WrongCommandException
    }
}