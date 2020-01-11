package com.mahmoudmabrok.catechwords.features.displayWords

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


class WordAdapter : RecyclerView.Adapter<WordAdapter.Holder>() {
    private var list: ArrayList<Word> = ArrayList()
    private var finished: ArrayList<Int> = ArrayList()
    private var firstSelected = -1
    private var secondSelected = -1

    private val MAX_SELECTEED = 2

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
        "onBindViewHolder $i, first:$firstSelected second:$secondSelected".log()
        if (finished.contains(i)) {
            holder.itemView.visibility = View.INVISIBLE
        } else {
            holder.itemView.visibility = View.VISIBLE
            if (i == firstSelected) {
                "${i == firstSelected} ${i == secondSelected}".log()
                holder.tvWord.text = list[i].word
            } else {
                holder.tvWord.text = ""
            }
        }


        holder.itemView.setOnClickListener {
            "## i$i, first:$firstSelected second:$secondSelected".log()
            when {
                firstSelected == i -> {
                    // case an already firstSelected item
                    alreadyCase(i)
                }

                firstSelected == -1 -> {
                    firstSelected(i)
                }

                firstSelected >= 0 -> {
                    // place to ui
                    holder.tvWord.text = list[i].word
                    checkCase(i)
                }
            }
        }

    }


    private fun alreadyCase(i: Int) {
        //todo  (1) flip
        firstSelected = -1
        notifyItemChanged(i)
        notifyItemRangeChanged(i, 1)

    }

    private fun firstSelected(i: Int) {
        firstSelected = i  // add to firstSelected list
        notifyItemChanged(i)
        notifyItemRangeChanged(i, 1)
    }

    /**
     * select second item so we must check
     */
    private fun checkCase(secondIdx: Int) {
        "check".log()
        val prev = list[firstSelected]
        val current = list[secondIdx]

        // wait before apply action
        if (current.isSame(prev)) {
            // colorCorrect()
            // add ot finished to be hidden next bind
            finished.add(firstSelected)
            finished.add(secondIdx)
            "correct".log()
        } else {
            "Wrong".log()
        }
        // so when check again  on onBind it will be cleared
        // for second one it is not first index so not displayed at onBind
        firstSelected = -1
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvWord: TextView = itemView.findViewById(R.id.tvWord)

    }

    companion object {

        private val TAG = "TafseerAdapter"
    }

}