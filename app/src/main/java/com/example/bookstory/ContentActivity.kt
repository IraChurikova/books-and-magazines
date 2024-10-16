package com.example.bookstory

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.bookstory.adapter.ItemBooks
import com.example.bookstory.database.Item
import com.example.bookstory.database.MainDb
import com.example.bookstory.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = MainDb.getDb(this)
        val item = intent.getSerializableExtra("item") as ItemBooks
        var isfav = item.favorite
        var isDataHandledBooks = true
        binding.apply {
            imageView3.setImageResource(item.avatarUrl)
            textView8.text = (item.author + " - " + item.namebook)
            textView14.text = item.star.toString()
            textView13.text = item.textbook
            if (isfav == 0) {
                imageView5.setImageResource(R.drawable.heart_outline)
            }
            else{
                imageView5.setImageResource(R.drawable.heart_solid)
            }


            imageView5.setOnClickListener{
            val itemp = Item(item.id,item.category,item.namebook,item.author,item.textbook,item.star,item.avatarUrl, 1)
            val itemm = Item(item.id,item.category,item.namebook,item.author,item.textbook,item.star,item.avatarUrl, 0)
            isDataHandledBooks = true
            if (isfav == 0){
                db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                    if(isDataHandledBooks){
                        Thread{
                            db.getDao().insertItem(itemp)
                        }.start()
                        imageView5.setImageResource(R.drawable.heart_solid)
                        isfav = 1
                    }
                    isDataHandledBooks = false
                }
            }
            else{
                db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                    if(isDataHandledBooks) {
                        Thread {
                            db.getDao().insertItem(itemm)
                        }.start()
                        imageView5.setImageResource(R.drawable.heart_outline)
                        isfav = 0
                    }
                    isDataHandledBooks = false
                }
            }
        }
            imageView.setOnClickListener {
                finish()
            }

    }
}
}