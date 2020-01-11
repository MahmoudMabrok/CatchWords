package com.mahmoudmabrok.catechwords.util

import android.app.Activity
import android.content.Intent
import android.util.Log


fun Activity.goto(dst: Class<Activity>) {
    val intent = Intent(this, dst)
    startActivity(intent)
}

fun String.log() {
    Log.d("P11", this)
}