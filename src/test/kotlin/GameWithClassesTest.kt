import BoardTestUtil.board3x3Array
import BoardTestUtil.drawBoardArray
import BoardTestUtil.setAndCopyArray
import BoardTestUtil.winBoardArray
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameWithClassesTest: StringSpec({

    "stringToArray" {
        Board("   \n X \n  0").cells shouldBe board3x3Array
        Board("\n|X    |\n|  X  |\n|    X|").cells shouldBe winBoardArray
    }

    "printBoardWithToString" {
        Board(board3x3Array).toString() shouldBe "\n|     |\n|  X  |\n|    0|"
        Board(winBoardArray).toString() shouldBe "\n|X    |\n|  X  |\n|    X|"
    }

    "draw" {
        State(board = Board(cells = drawBoardArray)).toString() shouldBe "Ничья"
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

    "gameStep" {
        Game(State(Board(cells = winBoardArray))).step(pointsIndexes) shouldBe false
        Game(State(Board(cells = winBoardArray))).step(Point(1, 1)) shouldBe false
    }

    "takeBack" {
        Game().takeBack(-3) shouldBe false
        Game().takeBack(-7) shouldBe false
        Game().takeBack(2) shouldBe false
        Game().takeBack(0) shouldBe true
    }
})