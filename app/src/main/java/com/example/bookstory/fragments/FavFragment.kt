package com.example.bookstory.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstory.ContentActivity
import com.example.bookstory.adapter.BooksAdapter
import com.example.bookstory.adapter.ItemBooks
import com.example.bookstory.database.MainDb
import com.example.bookstory.databinding.FragmentFavBinding

class FavFragment : Fragment(), BooksAdapter.Listener{

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private val adapter = BooksAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rcViewFav.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcViewFav.adapter = adapter
    }

    private fun observeData() {
        var isDataHandledBooks = true

        val db = MainDb.getDb(requireContext())
        db.getDao().getFavoriteItems().asLiveData().observe(viewLifecycleOwner) {
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
                    adapter.addBooks(itemBooks)
                    if(adapter.itemCount.equals(0))
                        binding.textView7.visibility = View.VISIBLE
                    else
                        binding.textView7.visibility = View.INVISIBLE
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
        fun newInstance() = FavFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}