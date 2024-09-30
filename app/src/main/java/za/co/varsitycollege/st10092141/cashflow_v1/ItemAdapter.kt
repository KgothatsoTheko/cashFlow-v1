package za.co.varsitycollege.st10092141.cashflow_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Item Adapter adapted from Medium
//https://medium.com/android-beginners/mvvm-with-retrofit-and-recyclerview-in-kotlin-example-f01a7bd41073#:~:text=MVVM%20With%20Retrofit%20and%20Recyclerview%20in%20Kotlin%20[Example]%20MVVM%20architecture
//Velmurugan Murugesan
//https://velmurugan-murugesan.medium.com/
class ItemAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        //Using recycle
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textName)
        val descriptionTextView: TextView = view.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.descriptionTextView.text = item.description
    }

    override fun getItemCount() = items.size
}