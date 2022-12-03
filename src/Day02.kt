import Outcome.*
import Shape.*

enum class Shape(private val beats: String, val points: Int) {
    ROCK("C", 1),
    PAPER("A", 2),
    SCISSORS("B", 3);

    fun beats() = convertShape(beats)
}

fun convertShape(code: String) =
    when (code) {
        "A", "X" -> ROCK
        "B", "Y" -> PAPER
        "C", "Z" -> SCISSORS
        else -> throw RuntimeException("$code is not a valid shape")
    }

data class Match(val opponent: Shape, val mine: Shape) {

    fun points() = mine.points + result(opponent, mine)

}

fun result(opponent: Shape, mine: Shape) =
    when (opponent) {
        mine -> 3
        else -> if (mine == opponent.beats()) 0 else 6
    }

enum class Outcome {
    LOSE,
    DRAW,
    WIN,
}

fun convertOutcome(code: String) =
    when (code) {
        "X" -> LOSE
        "Y" -> DRAW
        "Z" -> WIN
        else -> throw RuntimeException("$code is not a valid outcome")
    }

fun response(opponent: Shape, outcome: Outcome) =
    when (outcome) {
        DRAW -> opponent
        LOSE -> opponent.beats()
        WIN -> opponent.beats().beats()
    }

fun main() {

    fun calculatePoints(input: List<String>, mapper: (List<String>) -> Match) =
        input
            .map { it.split(" ") }
            .map(mapper)
            .sumOf { it.points() }

    fun part1(input: List<String>) =
        calculatePoints(input) {
            Match(convertShape(it[0]), convertShape(it[1]))
        }

    fun part2(input: List<String>) =
        calculatePoints(input) {
            val opponent = convertShape(it[0])
            Match(opponent, response(opponent, convertOutcome(it[1])))
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
