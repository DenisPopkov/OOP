package game

import util.COMMAND
import util.Point
import util.isIncorrectStep
import util.toPoint

sealed interface Input {

    class TakeBack(val shift: Int) : Input
    class Step(val x: Int, val y: Int, val param: List<String> = emptyList()) : Input {
        val point = Point(x, y)
    }

    object Exit : Input
    
    companion object {
        fun parse(string: String): Input {

            val input = string.split(" ").map { it.toInt() }
            val (x, y) = input
            val isCommand = x == COMMAND
            val statesCount = 0..MultiGame().states.size

            return when {
                !input.toPoint().isIncorrectStep() -> Step(x, y)
                isCommand && y in statesCount -> TakeBack(shift = y)
                else -> Exit
            }
        }
    }
}