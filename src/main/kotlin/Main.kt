fun main() {
    println("Игра крестики-нолики\nПопков Денис, 21м\n")
    TicTacToe.apply { board.printBoard(outputConsole) }
    game()
}