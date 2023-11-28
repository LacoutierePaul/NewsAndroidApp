package com.example.test_github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSource: TextView = itemView.findViewById(R.id.textSource)
        val textAuthor: TextView = itemView.findViewById(R.id.textAuthor)
        // Ajoutez d'autres vues pour les autres détails de l'article
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articles[position]

        // Mettez à jour les vues avec les données de l'article actuel
        holder.textSource.text = "Source: ${currentArticle.source.name}"
        holder.textAuthor.text = "Auteur: ${currentArticle.author}"
        // Mettez à jour d'autres vues avec les autres détails de l'article
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
