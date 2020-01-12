package com.mahmoudmabrok.catechwords.features.displayWords

import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.data.DataSource
import com.mahmoudmabrok.catechwords.model.Word
import com.mahmoudmabrok.catechwords.util.Rumble
import com.mahmoudmabrok.catechwords.util.showToast
import kotlinx.android.synthetic.main.activity_display_words.*
import kotlinx.android.synthetic.main.score_layout.*

class DisplayWords : AppCompatActivity(), WordAdapter.IScoreListener {

    lateinit var adapter:WordAdapter
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_words)

        if (savedInstanceState != null)
            score = savedInstanceState.getInt("score")

        setup()
        Rumble.init(applicationContext)
        setScoreInUi()
    }

    private fun setScoreInUi() {
        tvScore.text = "Score:$score"
    }

    private fun setup() {
        adapter = WordAdapter(this,getData())
        rvWords.adapter = adapter
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
        setScoreInUi()
    }


    override fun onFinishGame() {
        this.showToast(" Success!!!!")
        score = 0
        object :CountDownTimer(700,500){
            override fun onFinish() {
                finish()
            }

            override fun onTick(p0: Long) {
                  }

        }.start()
    }

    override fun vibrate() {
        Rumble.once(1200)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("score",score)
    }
}
