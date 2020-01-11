package com.mahmoudmabrok.catechwords.features.displayWords

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudmabrok.catechwords.R
import com.mahmoudmabrok.catechwords.model.Word
import java.util.*


class WordAdapter : RecyclerView.Adapter<WordAdapter.Holder>() {
    private var list: ArrayList<Word> = ArrayList()
    private var finished: ArrayList<Int> = ArrayList()
    private var selected = -1

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
        if (finished.contains(i)) {
            // item was correctly selected
            holder.itemView.visibility = View.INVISIBLE
        } else {
            holder.itemView.visibility = View.VISIBLE
            if (i == selected) {
                holder.tvWord.text = list[i].word
            } else {
                holder.tvWord.text = ""
            }
        }



        holder.itemView.setOnClickListener {
            when {
                selected == i -> {
                    // case an already selected item
                    alreadyCase(i)
                }

                selected == -1 -> {
                    firstSelected(i)
                }

                selected >= 0 -> {
                    checkCase(i, holder)
                }
            }
        }

    }


    private fun alreadyCase(i: Int) {
        //todo  (1) flip
        selected = -1
        notifyItemChanged(i)
        notifyItemRangeChanged(i, 1)

    }

    private fun firstSelected(i: Int) {
        // todo  (1) flip
        selected = i  // add to selected list
        notifyItemChanged(i)
        notifyItemRangeChanged(i, 1)
    }

    /**
     * select second item so we must check
     */
    private fun checkCase(secondIdx: Int, holder: WordAdapter.Holder) {
        // todo  (1) flip
        val prev = list[selected]
        val current = list[secondIdx]
        // place word in UI
        holder.tvWord.text = current.word
        // wait before apply action
        /*  Thread.sleep(1000)
          Log.d("TTA" , "after")
          if (current.isSame(prev)){
              // colorCorrect()
              // remove two elements
              finished.add(selected)
              finished.add(secondIdx)
              notifyDataSetChanged()
              Log.d("TTA" , "correct")
          }else{
              // false case
              holder.tvWord.text = "" // clear current one
              Log.d("TTA" , "Wrong")
          }
          selected = -1 // to select again
  */
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