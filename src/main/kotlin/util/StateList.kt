package util

class StateList<T>(
    init: T,
    private val array: ArrayList<T> = ArrayList()
) {

    init {
        array.add(init)
    }

    val state: T
        get() = array.last()

    fun add(state: T) {
        array.add(state)
    }

    fun move(to: Int): T {
        if (to in array.indices) {
            array.addAll(array.slice(0..to))
        } else {
            throw WrongCommandException
        }

        return array[to]
    }
}