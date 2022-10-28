class Step(val x: Int, val y: Int, val param: List<String> = emptyList()): Input {
    val point = Point(x, y)
}