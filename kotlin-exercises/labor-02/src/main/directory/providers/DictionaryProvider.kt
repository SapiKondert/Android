package main.directory.providers

import main.directory.interfaces.IDictionary
import main.directory.modules.DictionaryType
import main.directory.modules.HashSetDictionary
import main.directory.modules.ListDictionary
import main.directory.modules.TreeSetDictionary

class DictionaryProvider {

    fun createDictionary(dictionaryType: DictionaryType): IDictionary {
        return when (dictionaryType) {
            DictionaryType.ARRAY_LIST -> ListDictionary
            DictionaryType.TREE_SET -> TreeSetDictionary
            DictionaryType.HASH_SET -> HashSetDictionary
        }
    }

}