package main

import java.util.*

fun printSumOfNumbers(number1:Int, number2:Int) {
    println("$number1 + $number2 = ${number1 + number2}");
}

fun imutableLists() {
    val days = listOf<String>("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    days.forEach{
        println("$it ")
    }
    println();
    val daysThatStartWithT = days.filter { it.startsWith('t') }
    println("days that start with t: ")
    daysThatStartWithT.forEach{
        println("$it ")
    }
    println();

    val daysThatContainE = days.filter { it.contains('e') }
    println("days that contain e: ")
    daysThatContainE.forEach{
        println("$it ")
    }
    println();

    val daysThatAreSixLetterLong = days.filter { it.length == 6 }
    println("days that are six letter long: ")
    daysThatAreSixLetterLong.forEach{
        println("$it ")
    }
}

fun isPrime (number:Int) : Boolean{
    if (number <= 0 || number == 1){
        return false
    }
    var i = 2;
    while (i*i<number){
        if (number % i == 0){
            return false
        }
        i++
    }
    return true
}

fun encodeMassage(message:String):String{
    return Base64.getEncoder().encodeToString(message.toByteArray())
}

fun decodeMassage(message:String):String{
    return String(Base64.getDecoder().decode(message.toByteArray()))
}

fun messageCoding(message:String,codingFunction:(String)->String):String{
    return codingFunction(message)
}

fun evenNumbers(list:List<Int>):List<Int> = list.filter { it % 2 == 0 }

fun doubleNumbers(list:List<Int>):List<Int> = list.map { it * 2 }

fun capitolizeDays(list:List<String>):List<String> = list.map { it.uppercase() }

fun capitolizeFirstLetterInDays(list:List<String>):List<String> = list.map { it.replaceFirstChar { it.uppercase() } }

fun lengthOfDays(list:List<String>):List<Int> = list.map { it.length }

fun avarageLenghtOfDays(list:List<String>):Double = list.map { it.length }.average()

fun main(args: Array<String>) {
    //printSumOfNumbers(10,15);
    //imutableLists();
//    for (i in 100..10000){
//        if (isPrime(i)){
//            println(i)
//        }
//    }
//    println(evenNumbers(listOf(1,2,3,4,5,6,7,8,9,10)));
//    println(doubleNumbers(listOf(1,2,3,4,5,6,7,8,9,10)));
//    println(capitolizeDays(listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")));
//    println(capitolizeFirstLetterInDays(listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")))
//    println(lengthOfDays(listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")))
    println(avarageLenghtOfDays(listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")))
}