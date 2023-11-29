package com.example.test_github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textSource: TextView = itemView.findViewById(R.id.textSource)
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)

        private val textAuthor: TextView = itemView.findViewById(R.id.textAuthor)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)


        fun bind(article: Article) {
            // Mettez à jour les vues avec les données de l'article
            textSource.text = "Source: ${article.source.name}"
            textAuthor.text = "Auteur: ${article.author}"
            textTitle.text="Title: ${article.title}"

            Glide.with(itemView.context).load(article.urlToImage).fitCenter().into(imageView)

            // Mettez à jour d'autres vues avec les autres détails de l'article
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_holder, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

