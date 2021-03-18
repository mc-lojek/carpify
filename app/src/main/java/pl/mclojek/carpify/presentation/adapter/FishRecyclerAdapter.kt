package pl.mclojek.carpify.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.model.Fish

class FishRecyclerAdapter(
    private var fishList: ArrayList<Fish>,
    private val onItemClicked: (Fish) -> Unit
) : RecyclerView.Adapter<FishRecyclerAdapter.ViewHolder>() {

    fun changeList(list: ArrayList<Fish>) {
        this.fishList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.view_fish_list_item, parent, false)
        return ViewHolder(viewHolder) {
            onItemClicked(fishList[it])
        }
    }

    override fun getItemCount(): Int {
        return fishList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fish = fishList.get(position)
        holder.bind(fish)
    }

    class ViewHolder(val v: View, onItemlicked: (Int) -> Unit) : RecyclerView.ViewHolder(v) {
        init {
            itemView.setOnClickListener {
                onItemlicked(adapterPosition)
            }
        }

        fun bind(fish: Fish) {
            //bind data
            v.findViewById<TextView>(R.id.species).text = fish.species
            v.findViewById<TextView>(R.id.lake_name).text = "Dobro Klasztorne" //TODO: Hardcoded
        }
    }
}