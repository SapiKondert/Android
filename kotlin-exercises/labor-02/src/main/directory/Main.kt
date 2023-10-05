package main

import main.directory.interfaces.IDictionary
import main.directory.modules.DictionaryType
import main.directory.modules.ListDictionary
import main.directory.providers.DictionaryProvider


fun main(args: Array<String>) {
    val dict: IDictionary = DictionaryProvider().createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

}