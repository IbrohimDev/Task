package uz.gita.task.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import uz.gita.task.R
import uz.gita.task.app.App
import uz.gita.task.data.remote.response.ArticlesItem

class AllDataAdapter : PagingDataAdapter<ArticlesItem, AllDataAdapter.Vh>(DiffUtil) {

    object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class Vh(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = itemView.findViewById<RoundedImageView>(R.id.image_view)
        val textView = itemView.findViewById<TextView>(R.id.item_text)

        init {
            addLoadStateListener {combinedLoadStates ->

            }
        }

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            Glide.with(App.instance)
                .load(data!!.urlToImage)
                .into(imageView)
            textView.text = data.title
        }

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
    )
}