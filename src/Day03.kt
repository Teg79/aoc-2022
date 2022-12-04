fun main() {

    fun priority(type: Char) =
        if (type.isLowerCase()) type - 'a' + 1
        else type - 'A' + 27

    fun Sequence<List<String>>.intersectionPriority() =
        map { s -> s.map { it.asIterable().toSet() } }
            .map { it.reduce { acc, elem -> acc intersect elem } }
            .map { it.first() }
            .sumOf { priority(it) }

    fun part1(input: List<String>) =
        input.asSequence()
            .map { it.chunked(it.length / 2) }
            .intersectionPriority()

    fun part2(input: List<String>) =
        input.asSequence()
            .chunked(3)
            .intersectionPriority()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))

}
