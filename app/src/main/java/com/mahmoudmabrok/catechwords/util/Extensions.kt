package com.mahmoudmabrok.catechwords.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


fun Activity.goto(dst: Class<Activity>) {
    val intent = Intent(this, dst)
    startActivity(intent)
}
fun Context.showToast(msg: String) {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}


fun String.log() {
    Log.d("P11", this)
}