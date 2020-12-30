package pl.mclojek.carpify.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.Fish
import timber.log.Timber

class FishRecyclerAdapter(private val fishList: ArrayList<Fish>, private val context: Context):
        RecyclerView.Adapter<FishRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.fish_recycler_item, parent, false))
    }

    override fun getItemCount(): Int {
        return fishList.size
    }

    override fun onBindViewHolder(holder: FishRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv.text = fishList[position].species + " " + fishList[position].coords
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener
    {
        val tv = v.findViewById<TextView>(R.id.tv)


        override fun onClick(v: View?) {
            Timber.d("View clicked")
        }
    }
}