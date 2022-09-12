import Game.copy
import TicTacToe.board
import TicTacToe.boardSize
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BoardTest : StringSpec() {
    init {
        val firstBoard = Array(boardSize) { Array(boardSize) { ' ' } } // true board
        val secondBoard = Array(boardSize) { Array(boardSize) { ' ' } } // false board
        firstBoard[0][0] = 'X'
        firstBoard[1][1] = 'X'
        firstBoard[2][2] = 'X'
        secondBoard[0][0] = 'X'
        secondBoard[0][1] = ' '
        secondBoard[0][2] = 'X'

        "print 3x3" {
            board.printBoard() shouldBe firstBoard.printBoard()
        }

        "is true board" {
            firstBoard.checkWin() shouldBe 'X'
        }

        "is false board" {
            secondBoard.checkWin() shouldBe ' '
        }

        "is fill" {
            secondBoard.isFill() shouldBe true
        }

        "point" {
            "1 0".pointFromString() shouldBe Pair(1, 0)
        }

        "isRightMove" {
            secondBoard.isRightMove(Pair(0, 1)) shouldBe true
        }
    }
}

class TestXO : StringSpec({
    "TicTacToe - winX" {
        board[0][0] = 'X'
        board[1][1] = 'X'
        board[2][2] = 'X'
        game() shouldBe println("First player won")
    }
    "TicTacToe - win0" {
        board[0][1] = '0'
        board[1][1] = '0'
        board[2][1] = '0'
        game() shouldBe println("Second player won")
    }
    "TicTacToe - draw" {
        board[0][0] = '0'
        board[0][1] = '0'
        board[0][2] = 'X'

        board[1][0] = '0'
        board[1][1] = '0'
        board[1][2] = 'X'

        board[2][0] = '0'
        board[2][1] = '0'
        board[2][2] = 'X'
        game() shouldBe println("Draw!")
    }
    "TicTacToe - incorrect step" {
        Pair(0, 2).isIncorrectPoint() shouldBe false
        Pair(-1, 0).isIncorrectPoint() shouldBe true
        Pair(0, 2).isIncorrectPoint() shouldBe false
        Pair(0, 0).isIncorrectPoint() shouldBe false
    }

    "copy" {
        board.copy()
        Game.game.first().printBoard() shouldBe board.printBoard()
    }
})