package pl.mclojek.carpify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.model.Lake

class LakeRecyclerAdapter(
    private var lakeList: ArrayList<Lake>,
    private val onItemClicked: (Lake) -> Unit
) : RecyclerView.Adapter<LakeRecyclerAdapter.ViewHolder>() {

    fun changeList(list: ArrayList<Lake>) {
        this.lakeList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.lake_recycler_item, parent, false)
        return ViewHolder(viewHolder) {
            onItemClicked(lakeList[it])
        }
    }

    override fun getItemCount(): Int {
        return lakeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lake = lakeList.get(position)
        holder.bind(lake)
    }

    class ViewHolder(val v: View, onItemlicked: (Int) -> Unit) : RecyclerView.ViewHolder(v) {
        init {
            itemView.setOnClickListener {
                onItemlicked(adapterPosition)
            }
        }

        fun bind(lake: Lake) {
            //bind data

        }
    }
}