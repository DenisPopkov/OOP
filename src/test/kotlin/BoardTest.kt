import BoardTestUtil.board3x3Array
import BoardTestUtil.board4x3Array
import BoardTestUtil.isFillBoardArray
import BoardTestUtil.isRightMoveBoardArray
import BoardTestUtil.notWinCombinationsOnBoardArray
import BoardTestUtil.output
import BoardTestUtil.outputBuffer
import BoardTestUtil.winBoardArray
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BoardTest : StringSpec({

    "print" {
        board3x3Array.printBoard() shouldBe "\n|     |\n|  X  |\n|    0|"
    }

    "print 4x3" {
        outputBuffer.reset()
        board4x3Array.printBoard() shouldBe "\n|     |\n|  X  |\n|X   0|\n|0 X 0|"
    }

    "winBoard" {
        State(Board(winBoardArray)).checkWin() shouldBe 'X'
    }

    "notWinCombinationsOnBoard" {
        State(Board(notWinCombinationsOnBoardArray)).checkWin() shouldBe ' '
    }

    "isFill" {
        isFillBoardArray.isFill() shouldBe true
    }

    "isRightMove" {
        isRightMoveBoardArray.isRightMove(Pair(2, 2)) shouldBe true
    }
})