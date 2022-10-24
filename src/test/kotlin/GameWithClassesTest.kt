import BoardTestUtil.board3x3Array
import BoardTestUtil.drawBoardArray
import BoardTestUtil.winBoardArray
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameWithClassesTest: StringSpec({

    "stringToArray" {
        Board("   \n X \n  0").cells shouldBe board3x3Array
    }

    "printBoardWithToString" {
        Board(board3x3Array).toString() shouldBe "\n|     |\n|  X  |\n|    0|"
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
    }

    "getFromArray" {
        Board()[arrayIndexes] shouldBe ' '
    }

    "getOrNull" {
        Board().getOrNull(pointsIndexes) shouldBe null
    }

    "setAndCopy" {
        Board().setAndCopy(pointsIndexes, ' ').toString() shouldBe Board().toString()
    }

    "gameStep" {
        Game(State(Board(cells = winBoardArray))).step(pointsIndexes) shouldBe false
    }

    "takeBack" {
        Game().takeBack(-3) shouldBe false
    }
})