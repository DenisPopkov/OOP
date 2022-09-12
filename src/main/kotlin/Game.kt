object Game {

    var game = ArrayList<Array<Array<Char>>>()

    fun Array<Array<Char>>.copy() {
        game.add(this.map { row -> row.copyOf() }.toTypedArray())
    }
}