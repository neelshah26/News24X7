package com.example.news24x7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news24x7.R
import com.example.news24x7.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    lateinit var context: Context

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val ivArticleImage =itemView.findViewById<ImageView>(R.id.ivArticleImage)
        private val tvSource =itemView.findViewById<TextView>(R.id.tvSource)
        private val tvTitle =itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvDescription =itemView.findViewById<TextView>(R.id.tvDescription)
        private val tvPublishedAt =itemView.findViewById<TextView>(R.id.tvPublishedAt)

        fun bind(article: Article){
            Glide.with(context).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        context = parent.context
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_article_preview,
            parent,false
        ))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(article) }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }



}