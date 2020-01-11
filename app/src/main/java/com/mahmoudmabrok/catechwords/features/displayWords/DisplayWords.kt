package com.mahmoudmabrok.catechwords.features.displayWords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.data.DataSource
import com.mahmoudmabrok.catechwords.model.Word
import kotlinx.android.synthetic.main.activity_display_words.*

class DisplayWords : AppCompatActivity() {


    val adapter = WordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_words)

        setup()

    }

    private fun setup() {
        rvWords.adapter = adapter
        adapter.setList(getData())
    }

    private fun getData(): java.util.ArrayList<Word> {
        val list = java.util.ArrayList<Word>()
        for (word in DataSource.data) {
            list.add(word)
            list.add(word.getReflected())
        }
        list.shuffle()
        list.shuffle()
        list.shuffle()
        return list
    }
}
