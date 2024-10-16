package com.example.bookstory.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstory.ContentActivity
import com.example.bookstory.R
import com.example.bookstory.adapter.BooksAdapter
import com.example.bookstory.adapter.ItemBooks
import com.example.bookstory.database.MainDb
import com.example.bookstory.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(), BooksAdapter.Listener {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private val adapter = BooksAdapter(this)

    private var Categ = "Всё"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        if (Categ.equals("Всё")){
            observeData()
        }
        binding.tagAll.setOnClickListener{
            adapter.clear()
            Categ = "Всё"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.nav_bg)
            binding.textView21.setTextColor(Color.argb(255, 241, 238, 227))
            binding.tagDetect.setBackgroundResource(R.drawable.cat_bg)
            binding.textView20.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagRom.setBackgroundResource(R.drawable.cat_bg)
            binding.textView19.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagHistory.setBackgroundResource(R.drawable.cat_bg)
            binding.textView18.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagTale.setBackgroundResource(R.drawable.cat_bg)
            binding.textView17.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagOther.setBackgroundResource(R.drawable.cat_bg)
            binding.textView16.setTextColor(Color.argb(255, 25, 24, 21))
        }
        binding.tagDetect.setOnClickListener{
            adapter.clear()
            Categ = "Детективы"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.cat_bg)
            binding.textView21.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagDetect.setBackgroundResource(R.drawable.nav_bg)
            binding.textView20.setTextColor(Color.argb(255, 241, 238, 227))
            binding.tagRom.setBackgroundResource(R.drawable.cat_bg)
            binding.textView19.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagHistory.setBackgroundResource(R.drawable.cat_bg)
            binding.textView18.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagTale.setBackgroundResource(R.drawable.cat_bg)
            binding.textView17.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagOther.setBackgroundResource(R.drawable.cat_bg)
            binding.textView16.setTextColor(Color.argb(255, 25, 24, 21))
        }
        binding.tagRom.setOnClickListener{
            adapter.clear()
            Categ = "Романы"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.cat_bg)
            binding.textView21.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagDetect.setBackgroundResource(R.drawable.cat_bg)
            binding.textView20.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagRom.setBackgroundResource(R.drawable.nav_bg)
            binding.textView19.setTextColor(Color.argb(255, 241, 238, 227))
            binding.tagHistory.setBackgroundResource(R.drawable.cat_bg)
            binding.textView18.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagTale.setBackgroundResource(R.drawable.cat_bg)
            binding.textView17.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagOther.setBackgroundResource(R.drawable.cat_bg)
            binding.textView16.setTextColor(Color.argb(255, 25, 24, 21))
        }
        binding.tagHistory.setOnClickListener{
            adapter.clear()
            Categ = "Историческике"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.cat_bg)
            binding.textView21.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagDetect.setBackgroundResource(R.drawable.cat_bg)
            binding.textView20.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagRom.setBackgroundResource(R.drawable.cat_bg)
            binding.textView19.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagHistory.setBackgroundResource(R.drawable.nav_bg)
            binding.textView18.setTextColor(Color.argb(255, 241, 238, 227))
            binding.tagTale.setBackgroundResource(R.drawable.cat_bg)
            binding.textView17.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagOther.setBackgroundResource(R.drawable.cat_bg)
            binding.textView16.setTextColor(Color.argb(255, 25, 24, 21))
        }
        binding.tagTale.setOnClickListener{
            adapter.clear()
            Categ = "Сказки"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.cat_bg)
            binding.textView21.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagDetect.setBackgroundResource(R.drawable.cat_bg)
            binding.textView20.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagRom.setBackgroundResource(R.drawable.cat_bg)
            binding.textView19.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagHistory.setBackgroundResource(R.drawable.cat_bg)
            binding.textView18.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagTale.setBackgroundResource(R.drawable.nav_bg)
            binding.textView17.setTextColor(Color.argb(255, 241, 238, 227))
            binding.tagOther.setBackgroundResource(R.drawable.cat_bg)
            binding.textView16.setTextColor(Color.argb(255, 25, 24, 21))
        }
        binding.tagOther.setOnClickListener{
            adapter.clear()
            Categ = "Остальное"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.cat_bg)
            binding.textView21.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagDetect.setBackgroundResource(R.drawable.cat_bg)
            binding.textView20.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagRom.setBackgroundResource(R.drawable.cat_bg)
            binding.textView19.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagHistory.setBackgroundResource(R.drawable.cat_bg)
            binding.textView18.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagTale.setBackgroundResource(R.drawable.cat_bg)
            binding.textView17.setTextColor(Color.argb(255, 25, 24, 21))
            binding.tagOther.setBackgroundResource(R.drawable.nav_bg)
            binding.textView16.setTextColor(Color.argb(255, 241, 238, 227))
        }
    }

    private fun setupRecyclerView() {
        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcView.adapter = adapter
    }

    private fun observeData() {
        var isDataHandledBooks = true

        val db = MainDb.getDb(requireContext())
        db.getDao().getAllItems().asLiveData().observe(viewLifecycleOwner) {
            if(isDataHandledBooks){
                it.forEach { item ->
                    val itemBooks = ItemBooks(
                        item.id,
                        item.category,
                        item.namebook,
                        item.author,
                        item.textbook,
                        item.star,
                        item.avatarUrl,
                        item.favorite
                    )
                    if(itemBooks.category.equals(Categ)) {
                       adapter.addBooks(itemBooks)
                    }
                    if(Categ.equals("Всё")) {
                        adapter.addBooks(itemBooks)
                    }
                }
                isDataHandledBooks = false
            }
        }
    }

    override fun onClick(itemBooks: ItemBooks) {
        startActivity(Intent(requireActivity(), ContentActivity::class.java).apply {
            putExtra("item", itemBooks)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = CatalogFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}