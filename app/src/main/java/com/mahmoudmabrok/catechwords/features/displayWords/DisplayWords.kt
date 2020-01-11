package com.mahmoudmabrok.catechwords.features.displayWords

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.data.DataSource
import com.mahmoudmabrok.catechwords.model.Word
import com.mahmoudmabrok.catechwords.util.showToast
import kotlinx.android.synthetic.main.activity_display_words.*

class DisplayWords : AppCompatActivity(), WordAdapter.IScoreListener {

    lateinit var adapter:WordAdapter
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_words)

        setup()

    }

    private fun setup() {
        adapter = WordAdapter(this)
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
        return list
    }

    override fun onCorrect() {
        score++
        this.showToast("Score::$score")
    }


    var a = 0
    override fun onFinish() {
        a++
        this.showToast("$a Success!!!!")
        score = 0
        object :CountDownTimer(1500,500){
            override fun onFinish() {
                finish()
            }

            override fun onTick(p0: Long) {
                  }

        }.start()
    }


}
