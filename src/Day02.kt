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

enum class Outcome(val points: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6),
}

fun convertOutcome(code: String) =
    when (code) {
        "X" -> LOSE
        "Y" -> DRAW
        "Z" -> WIN
        else -> throw RuntimeException("$code is not a valid outcome")
    }

data class Match(val opponent: Shape, val mine: Shape) {
    fun points() = mine.points +
            when (opponent) {
                mine -> DRAW.points
                else -> if (mine == opponent.beats()) LOSE.points else WIN.points
            }
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
