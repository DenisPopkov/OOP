fun main() {
    println("Игра крестики-нолики\nПопков Денис, 21м\n")
    TicTacToe.apply { Array(boardSize) { Array(boardSize) { ' ' } }.printBoard(outputConsole) }
    game()
}