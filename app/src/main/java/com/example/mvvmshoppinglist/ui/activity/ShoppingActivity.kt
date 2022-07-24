package com.example.mvvmshoppinglist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.databinding.ActivityShoppingBinding
import com.example.mvvmshoppinglist.ui.viewmodel.ShoppingViewModel
import com.example.mvvmshoppinglist.ui.adapter.ShoppingItemAdapter
import com.example.mvvmshoppinglist.ui.dialog.AddShoppingItemDialog
import com.example.mvvmshoppinglist.ui.utils.AddDialogListener
import com.example.mvvmshoppinglist.ui.viewmodel.ShoppingViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter()
        adapter.deleteClickListener = { item ->
            viewModel.delete(item)
        }
        adapter.plusClickListener = { item ->
            viewModel.plusItem(item)
        }
        adapter.minusClickListener = { item ->
            viewModel.minusItem(item)
        }
        binding.rvShoppingItem.apply {
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            this.adapter = adapter
        }
        viewModel.getAllShoppingItems().observe(this) {
            it?.let { items ->
                adapter.replaceItems(items)
            }
        }
        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this@ShoppingActivity,
                object : AddDialogListener {
                    override fun onAddButtonClicked(shoppingItem: ShoppingItem) {
                        viewModel.upsert(shoppingItem)
                    }
                }
            ).show()
        }
    }
}