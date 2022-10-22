import Game.copy
import TicTacToe.board
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

val outputBuffer = ByteArrayOutputStream()
val output = PrintStream(outputBuffer)

class BoardTest : StringSpec({
    "print" {
        val board = arrayOf(
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', 'X', ' '),
            arrayOf(' ', ' ', '0')
        )
        board.printBoard(output)
        outputBuffer.toString() shouldBe "\n|     |\n|  X  |\n|    0|\n"
    }

    "print 4x3" {
        outputBuffer.reset()
        val board = arrayOf(
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', 'X', ' '),
            arrayOf('X', ' ', '0'),
            arrayOf('0', 'X', '0')
        )
        board.printBoard(output)
        outputBuffer.toString() shouldBe "\n|     |\n|  X  |\n|X   0|\n|0 X 0|\n"
    }

    "trueBoard" {
        val board = arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf(' ', 'X', ' '),
            arrayOf(' ', ' ', 'X')
        )
        board.checkWin() shouldBe 'X'
    }

    "falseBoard" {
        val board = arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', ' ', 'X')
        )
        board.checkWin() shouldBe ' '
    }

    "isFill" {
        val board = arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', ' ', 'X')
        )
        board.isFill() shouldBe true
    }

    "point" {
        "1 0".pointFromString() shouldBe Pair(1, 0)
    }

    "isRightMove" {
        val board = arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', ' ', ' ')
        )
        board.isRightMove(Pair(2, 2)) shouldBe true
    }
})

class TestXO : StringSpec({
    fun String.getBufferLastLine() = substring(this.lastIndexOf("\n"))

    "game1 (X)" {
        val input = "1 1\n1 2\n0 1\n0 2\n2 1\n"
        game(ByteArrayInputStream(input.toByteArray()), output)
        outputBuffer.toString().getBufferLastLine() shouldBe "\nFirst player won"
    }

    "game2 (0)" {
        val input = "0 0\n1 1\n1 0\n0 1\n2 2\n2 1\n"
        game(ByteArrayInputStream(input.toByteArray()), output)
        outputBuffer.toString().getBufferLastLine() shouldBe "\nSecond player won"
    }

    "gameDraw" {
        val input = "1 1\n0 0\n1 0\n1 2\n2 1\n0 1\n0 2\n2 0\n2 2\n1 1\n"
        game(ByteArrayInputStream(input.toByteArray()), output)
        outputBuffer.toString().getBufferLastLine() shouldBe "\nDraw!"
    }

    "isRightMove" {
        val board = arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf(' ', ' ', ' '),
            arrayOf(' ', ' ', '0')
        )
        board.isRightMove(Pair(2, 2)) shouldBe false
        board.isRightMove(Pair(1, 2)) shouldBe true
        board.isRightMove(Pair(0, 0)) shouldBe false
        board.isRightMove(Pair(1, 1)) shouldBe true
    }

    "copy" {
        board.copy()
        Game.game.first().printBoard() shouldBe board.printBoard()
    }
})