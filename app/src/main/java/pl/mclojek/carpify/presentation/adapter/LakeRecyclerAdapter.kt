package pl.mclojek.carpify.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.utils.ItemClickedListener
import pl.mclojek.carpify.presentation.view.LakeListItemView

class LakeRecyclerAdapter(
        private val itemClickedListener: ItemClickedListener<Lake>
) : RecyclerView.Adapter<LakeRecyclerAdapter.ViewHolder>() {

    private var items: List<Lake> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LakeListItemView(parent.context))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.lakeListItemView.bind(item)
        holder.itemView.setOnClickListener {
            itemClickedListener.onItemClicked(item)
        }
    }

    fun updateData(list: List<Lake>) {
        this.items = list
        notifyDataSetChanged()
    }

    class ViewHolder(val lakeListItemView: LakeListItemView) : RecyclerView.ViewHolder(lakeListItemView)
}