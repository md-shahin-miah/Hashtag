package com.example.myappli.screen.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.R
import com.example.myappli.common.EventObserver
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.screen.category.CategoryAdapter
import com.example.myappli.screen.subCategory.SubCategoryActivity
import com.example.myappli.screen.tag.TagActivity
import com.google.gson.Gson
import java.util.*


class HomeFragment : Fragment(), CategoryAdapter.OnItemClickListener,
    FeatureAdapter.OnItemClickListener, SearchAdapter.OnItemClickListener {


    private lateinit var factory: HomeModelFactory
    private lateinit var viewModel: HomeViewModel
    private lateinit var appDatabase: AppDatabase

    private lateinit var recyclerView: RecyclerView
    private lateinit var featureList: RecyclerView
    private lateinit var searchList: RecyclerView

    private var categoryAdapter: CategoryAdapter? = null
    private var searchAdapter: SearchAdapter? = null
    private var featureAdapter: FeatureAdapter? = null

    private lateinit var firstLayout: LinearLayout
    private lateinit var searchCancel: ImageView
    private lateinit var searchBox: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        appDatabase = AppDatabase.getAppDataBase(requireContext())!!
        factory = HomeModelFactory(appDatabase)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        recyclerView = root.findViewById(R.id.categoryList)
        featureList = root.findViewById(R.id.featureList)
        searchList = root.findViewById(R.id.searchList)
        firstLayout = root.findViewById(R.id.firstLayout)
        searchCancel = root.findViewById(R.id.search_cancel)
        searchBox = root.findViewById(R.id.search_box)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.categoryFetchSuccess.observe(viewLifecycleOwner, EventObserver { categories ->

            val features: MutableList<Category> = mutableListOf()
            val categoryList: MutableList<Category> = mutableListOf()

            categories.forEach { cat ->
                when (cat.name) {
                    "Nature" -> {
                        features.add(cat)
                    }
                    "Animals" -> {
                        features.add(cat)
                    }
                    "Food" -> {
                        features.add(cat)
                    }
                    "Fashion" -> {
                        features.add(cat)
                    }
                    "Celebrities" -> {
                        features.add(cat)
                    }
                    "Travel/Active/Sports" -> {
                        features.add(cat)
                    }
                    else -> {
                        categoryList.add(cat)
                    }
                }
            }
            features.shuffle()
            featureAdapter?.setNewList(features)
            categoryAdapter?.setNewList(categoryList)
        })


        viewModel.searchCategoryFetchSuccess.observe(
            viewLifecycleOwner,
            EventObserver { categories ->
                searchAdapter?.setNewList(categories)
                Log.d("TAG", "onActivityCreated: $categories")
            })

        viewModel.getCategories(0)

        featureAdapter = FeatureAdapter(requireContext())
        featureAdapter?.setListener(this)
        featureList.also {
            it.layoutManager = GridLayoutManager(requireContext(), 3)
            it.adapter = featureAdapter
        }

        categoryAdapter = CategoryAdapter(requireContext())
        categoryAdapter?.setListener(this)
        recyclerView.also {
            it.hasFixedSize()
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = categoryAdapter
        }


        searchAdapter = SearchAdapter(requireContext())
        searchAdapter?.setListener(this)
        searchList.also {
            it.hasFixedSize()
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = searchAdapter
        }

        searchCancel.setOnClickListener {
            searchBox.text.clear()
        }

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isEmpty()){
                    firstLayout.visibility = View.VISIBLE
                    searchList.visibility = View.GONE

                }else{
                    firstLayout.visibility = View.GONE
                    searchList.visibility = View.VISIBLE
                    viewModel.getSearchList(s.toString())

                }

            }
        })
    }

    override fun onItemClick(category: Category) {
        val intent = Intent(requireContext(), SubCategoryActivity::class.java)
        intent.putExtra("cat", Gson().toJson(category))
        startActivity(intent)

    }

    override fun onItemClicked(category: Category) {
        val intent = Intent(requireContext(), TagActivity::class.java)
        intent.putExtra("cat",Gson().toJson(category))
        startActivity(intent)
    }
}