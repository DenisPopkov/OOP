import TicTacToe.board
import TicTacToe.boardSize
import TicTacToe.outputBuffer
import TicTacToe.outputConsole
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayInputStream

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
            printBoard(outputConsole)
            outputBuffer.toString() shouldBe "  012\n0    \n1  X \n2   0\n"
        }
        "print 3x4" {
            printBoard(outputConsole)
            outputBuffer.toString() shouldBe "   0123\n0    \n1  X  \n2   0 \n"
        }

        "is true board" {
            checkWin(firstBoard) shouldBe 'X'
        }

        "is false board" {
            checkWin(secondBoard) shouldBe ' '
        }

        "is fill" {
            isFill(secondBoard) shouldBe true
        }

        "point" {
            pointFromString("1 0") shouldBe arrayOf(1, 0)
        }

        "isRightMove" {
            isRightMove(secondBoard, arrayOf(1, 0)) shouldBe true
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
        arrayOf(0, 4).isIncorrectPoint() shouldBe true
        arrayOf(-1, 0).isIncorrectPoint() shouldBe true
        arrayOf(9, -1).isIncorrectPoint() shouldBe true
        arrayOf(0, 2).isIncorrectPoint() shouldBe false
    }
})