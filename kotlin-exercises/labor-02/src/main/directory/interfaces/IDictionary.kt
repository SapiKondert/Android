package main.directory.interfaces

interface IDictionary {

    companion object {
        const val DICTIONARY_FILE = "dictionary.txt"
    }

    fun add(string: String):Boolean
    fun find(string:String):Boolean
    fun size():Int
}