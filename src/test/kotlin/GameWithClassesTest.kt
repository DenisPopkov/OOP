import BoardTestUtil.board3x3Array
import BoardTestUtil.drawBoardArray
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
})