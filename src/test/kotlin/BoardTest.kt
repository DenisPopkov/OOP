import BoardTestUtil.board3x3Array
import BoardTestUtil.board4x3Array
import BoardTestUtil.isFillBoardArray
import BoardTestUtil.isRightMoveBoardArray
import BoardTestUtil.outputBuffer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import util.isFill
import util.isRightMove
import util.printBoard

class BoardTest : StringSpec({

    "print" {
        board3x3Array.printBoard() shouldBe "\n|     |\n|  X  |\n|    0|"
    }

    "print 4x3" {
        outputBuffer.reset()
        board4x3Array.printBoard() shouldBe "\n|     |\n|  X  |\n|X   0|\n|0 X 0|"
    }

    "util.isFill" {
        isFillBoardArray.isFill() shouldBe true
    }

    "util.isRightMove" {
        isRightMoveBoardArray.isRightMove(Pair(2, 2)) shouldBe true
    }
})