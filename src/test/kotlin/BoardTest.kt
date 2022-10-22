import BoardTestUtil.board3x3Array
import BoardTestUtil.board4x3Array
import BoardTestUtil.isFillBoardArray
import BoardTestUtil.isRightMoveBoardArray
import BoardTestUtil.notWinCombinationsOnBoardArray
import BoardTestUtil.output
import BoardTestUtil.outputBuffer
import BoardTestUtil.winBoardArray
import Game.copy
import GameTestUtil.getBufferLastLine
import TicTacToe.board
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayInputStream

class BoardTest : StringSpec({

    "print" {
        board3x3Array.printBoard(output)
        outputBuffer.toString() shouldBe "\n|     |\n|  X  |\n|    0|\n"
    }

    "print 4x3" {
        outputBuffer.reset()
        board4x3Array.printBoard(output)
        outputBuffer.toString() shouldBe "\n|     |\n|  X  |\n|X   0|\n|0 X 0|\n"
    }

    "winBoard" {
        winBoardArray.checkWin() shouldBe 'X'
    }

    "notWinCombinationsOnBoard" {
        notWinCombinationsOnBoardArray.checkWin() shouldBe ' '
    }

    "isFill" {
        isFillBoardArray.isFill() shouldBe true
    }

    "point" {
        "1 0".pointFromString() shouldBe Pair(1, 0)
    }

    "isRightMove" {
        isRightMoveBoardArray.isRightMove(Pair(2, 2)) shouldBe true
    }
})

class GameTest : StringSpec({

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

    "copy" {
        board.copy()
        Game.game.first().printBoard() shouldBe board.printBoard()
    }
})