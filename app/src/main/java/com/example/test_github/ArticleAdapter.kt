package com.example.test_github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


interface OnItemClickListener {
    fun onItemClick(article: Article)
}

class ArticleAdapter(
    private val articles: List<Article>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textSource: TextView = itemView.findViewById(R.id.textSource)
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textAuthor: TextView = itemView.findViewById(R.id.textAuthor)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(article: Article) {
            textSource.text = "Source: ${article.source?.name}"
            textAuthor.text = "Auteur: ${article.author}"
            textTitle.text = "Title: ${article.title}"
            if(article.urlToImage==null || article.urlToImage=="default")
            {
                //Glide.with(itemView.context).load("https://cdn.vectorstock.com/i/preview-1x/82/99/no-image-available-like-missing-picture-vector-43938299.jpg").fitCenter().into(imageView)
            } else {
                Glide.with(itemView.context).load(article.urlToImage).fitCenter().into(imageView)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_holder, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = articles[position]
        holder.bind(articles[position])
        holder.itemView.setOnClickListener {
            // Utilisez l'interface pour gérer l'événement de clic
            itemClickListener.onItemClick(current)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
