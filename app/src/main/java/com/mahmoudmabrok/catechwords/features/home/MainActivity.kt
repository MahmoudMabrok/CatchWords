package com.mahmoudmabrok.catechwords.features.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.features.displayWords.DisplayWords

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoNext(view: View) {
        val intent = Intent(this, DisplayWords::class.java)
        startActivity(intent)

//        this.goto(DisplayWords::class.java)

    }
}
