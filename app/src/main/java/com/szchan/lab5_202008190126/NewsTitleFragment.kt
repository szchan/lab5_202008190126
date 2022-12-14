package com.szchan.lab5_202008190126

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szchan.lab5_202008190126.databinding.NewsTitleFragBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class NewsTitleFragment:Fragment() {

    private lateinit var binding:NewsTitleFragBinding
    private var isTwoPane = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsTitleFragBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) !=null
        val layoutManager = LinearLayoutManager(activity)
        binding.newsTitleRecyclerView.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        binding.newsTitleRecyclerView.adapter = adapter
    }
    private fun getNews():List<News>{
        val newsList = ArrayList<News>()
        for(i in 1..50){
            val news = News("This is news title $i",getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }
    private fun getRandomLengthString(str:String):String{
        val n = Random().nextInt(20)+1

        return str * n
    }
    inner class NewsAdapter(val newsList:List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener{
                val news = newsList[holder.adapterPosition]
                if(isTwoPane){

                    val fragment = fragmentManager?.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
                    fragment.refresh(news.title,news.content)

                }else{
                    NewsContentActivity.actionStart(parent.context,news.title,news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }

}