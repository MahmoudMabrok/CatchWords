package com.mahmoudmabrok.catechwords.features.displayWords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.data.DataSource
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
        adapter.list = DataSource.data
    }
}
