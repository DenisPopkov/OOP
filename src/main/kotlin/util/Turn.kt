package util

enum class Turn {
    GAME_XO,
    GAME_BALDA;

    companion object {
        fun next(turn: Turn) = Turn.values().single { it != turn }
        fun toString(turn: Turn) = Turn.values().find { it == turn }?.name
    }
}