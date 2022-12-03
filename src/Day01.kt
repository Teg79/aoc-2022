fun main() {

    fun foldElfs(input: List<String>) =
        input.fold(mutableListOf(mutableListOf<Int>())) { acc, line ->

            if (line.isBlank())
                acc.add(mutableListOf())
            else
                acc.last().add(line.toInt())
            acc
        }

    fun part1(input: List<String>) =
        foldElfs(input)
            .maxOf { it.sum() }

    fun part2(input: List<String>) =
        foldElfs(input)
            .map { it.sum() }
            .sortedDescending()
            .subList(0, 3)
            .sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
