package com.mahmoudmabrok.catechwords.model


data class Word(val word: String, val mean: String) {

    fun getReflected(): Word = Word(mean, word)

    fun Word.isSame(other: Word): Boolean = word.equals(other.mean)

}