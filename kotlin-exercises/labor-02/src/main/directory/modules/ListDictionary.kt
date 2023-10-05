package main.directory.modules

import main.directory.interfaces.IDictionary
import java.io.File

object ListDictionary:IDictionary {

    private val list = mutableListOf<String>()

    init {
        File(IDictionary.DICTIONARY_FILE).forEachLine {
            list.add(it);
        }
    }

    override fun add(string: String): Boolean {
        if (find(string)) {
            list.add(string)
            return true;
        }
        else{
            return false
        }
    }

    override fun find(string: String): Boolean {
        return list.contains(string);
    }

    override fun size(): Int {
        return list.size
    }

}