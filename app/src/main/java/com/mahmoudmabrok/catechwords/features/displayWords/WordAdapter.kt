package com.mahmoudmabrok.catechwords.features.displayWords

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.model.Word
import com.mahmoudmabrok.catechwords.model.isSame
import com.mahmoudmabrok.catechwords.util.log
import java.util.*


class WordAdapter(val listener:IScoreListener) : RecyclerView.Adapter<WordAdapter.Holder>() {
    private var list: ArrayList<Word> = ArrayList()
    private var finished: ArrayList<Int> = ArrayList()
    private var firstSelected = -1
    private var countOfSelected = 0

    private val MAX_SELECTEED = 2
    private val INTERVAL = 300L

    fun setList(newList: ArrayList<Word>) {
        list = ArrayList(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.word_item, viewGroup, false)
        return Holder(view)
    }


    override fun onBindViewHolder(holder: Holder, i: Int) {
        "onBindViewHolder $i, first:$firstSelected ".log()
        if (finished.contains(i)) {
            holder.itemView.visibility = View.INVISIBLE
        } else {
            holder.itemView.visibility = View.VISIBLE
            if (i == firstSelected) {
                holder.tvWord.text = list[i].word
            } else {
                holder.tvWord.text = ""
            }
        }

        holder.itemView.setOnClickListener {
            when {
                firstSelected == i -> {
                    alreadyCase(i)
                }

                firstSelected == -1 -> {
                    firstSelected(i)
                }

                firstSelected >= 0 -> {
                    countOfSelected++
                    holder.tvWord.text = list[i].word // place to ui
                    object : CountDownTimer(INTERVAL, INTERVAL) {
                        override fun onFinish() {
                            checkCase(i)
                        }

                        override fun onTick(p0: Long) {
                        }

                    }.start()

                }


            }
        }

        "finished.size == list.size"
/*
        if (finished.size == list.size){
            "finised ${finished.size}"
             listener.onFinish()
        }*/
    }


    /**
     * when click on item that already clicked so ->  hide test
     */
    private fun alreadyCase(i: Int) {
        countOfSelected--
        firstSelected = -1
        notifyItemChanged(i)

    }

    /**
     * first card to be clicked so show it's content
     */
    private fun firstSelected(i: Int) {
        countOfSelected++
        firstSelected = i  // add to firstSelected list
        notifyItemChanged(i)
    }

    /**
     * select second item so we must check
     */
    private fun checkCase(secondIdx: Int) {
        val prev = list[firstSelected]
        val current = list[secondIdx]
        // extension function to test is two cards are correct
        if (current.isSame(prev)) {
            // add ot finished to be hidden next bind
            finished.add(firstSelected)
            finished.add(secondIdx)
            listener.onCorrect()
        } else {
            //todo make vibrate
            "Wrong".log()
        }
        // so when check again  on onBind it will be cleared
        // for second one it is not first index so not displayed at onBind
        firstSelected = -1
        notifyDataSetChanged() //todo find less way
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvWord: TextView = itemView.findViewById(R.id.tvWord)

    }

    interface IScoreListener{
        fun onCorrect()
        fun onFinish()
    }

}