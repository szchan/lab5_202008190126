package com.szchan.lab5_202008190126

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szchan.lab5_202008190126.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewsContentBinding
    companion object{
        fun actionStart(context: Context,title:String,content:String){
            val intent = Intent(context,NewsContentActivity::class.java).apply {
                putExtra("news_title",title)
                putExtra("news_content",content)
            }
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("new_title")
        val content = intent.getStringExtra("new_content")
        if(title !=null && content !=null){
            val fragment = this.supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
            fragment.refresh(title,content)
        }
    }
}