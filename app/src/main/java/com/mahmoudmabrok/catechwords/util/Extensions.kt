package com.mahmoudmabrok.catechwords.util

import android.app.Activity
import android.content.Intent


fun Activity.goto(dst: Class<Activity>) {
    val intent = Intent(this, dst)
    startActivity(intent)
}