package game

import util.COMMAND
import util.Point
import util.isIncorrectStep
import util.toPoint

sealed interface Input {

    object Exit : Input

    class TakeBack(val shift: Int): Input

    class Step(val x: Int, val y: Int, val param: List<String> = emptyList()): Input {
        val point = Point(x, y)
    }

    companion object {
        fun parse(string: String): Input {

            val input = string.split(" ").map { it.toInt() }
            val (x, y) = input
            val isCommand = x == COMMAND
            //val statesCount = 0..MultiGame().boardSize

            return when {
                !isCommand && input.toPoint().isIncorrectStep() -> Exit
                //isCommand && y !in statesCount -> Exit
                isCommand -> TakeBack(shift = x)
                else -> Step(x, y)
            }
        }
    }
}