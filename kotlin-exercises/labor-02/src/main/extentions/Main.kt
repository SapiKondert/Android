package main.extentions

fun main(args: Array<String>) {
    val name = "Kondert Matyas"
    println(name.monogram())
    val fruits = listOf("apple", "orange", "banana")
    println(fruits.joinBySeparator("-"))
    println(fruits.longest())
}

private fun List<String>.longest() = maxByOrNull { it.length }

private fun List<String>.joinBySeparator(string: String) = joinToString(string);

private fun String.monogram() = split(' ').map { it.first() }.joinToString("")
