package com.example.mvvmshoppinglist.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmshoppinglist.R
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.databinding.DialogAddShoppingItemBinding
import com.example.mvvmshoppinglist.ui.utils.AddDialogListener


class AddShoppingItemDialog(context: Context, val addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        with(binding) {
            tvAdd.setOnClickListener {
                val name = "${etName.text}"
                val amount = "${etAmount.text}".toInt()
                if (name.isBlank() || amount <= 0) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.please_enter_all_data_correctly),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val item = ShoppingItem(
                    name = name,
                    amount = amount
                )
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }
            tvCancel.setOnClickListener {
                cancel()
            }
        }
    }
}