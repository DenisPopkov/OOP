import BoardTestUtil.board3x3Array
import BoardTestUtil.setAndCopyArray
import BoardTestUtil.winBoardArray
import game.Board
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import util.Point
import util.arrayIndexes
import util.pointsIndexes

class MultiGameWithClassesTest: StringSpec({

    "stringToArray" {
        Board("   \n X \n  0").cells shouldBe board3x3Array
        Board("X  \n X \n  X").cells shouldBe winBoardArray
    }

    "printBoardWithToString" {
        Board(board3x3Array).toString() shouldBe "\n|     |\n|  X  |\n|    0|"
        Board(winBoardArray).toString() shouldBe "\n|X    |\n|  X  |\n|    X|"
    }

    "boardFromLine" {
        val board = Board("   \n X \n  0")
        Board(board = board).cells shouldBe board3x3Array
    }

    "getFromPoint" {
        Board()[pointsIndexes] shouldBe ' '
        Board(cells = board3x3Array)[Point(1, 1)] shouldBe 'X'
    }

    "getFromArray" {
        Board()[arrayIndexes] shouldBe ' '
        Board(cells = board3x3Array)[arrayOf(1, 1)] shouldBe 'X'
    }

    "getOrNull" {
        Board().getOrNull(pointsIndexes) shouldBe null
        Board().getOrNull(Point(1, 1)) shouldBe null
    }

    "setAndCopy" {
        Board().setAndCopy(pointsIndexes, ' ').toString() shouldBe Board().toString()
        Board().setAndCopy(Point(2, 2), 'X').toString() shouldBe Board(cells = setAndCopyArray).toString()
    }
})