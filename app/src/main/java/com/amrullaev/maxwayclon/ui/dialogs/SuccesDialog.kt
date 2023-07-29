package com.amrullaev.maxwayclon.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.amrullaev.maxwayclon.databinding.DialogSuccessBinding
import com.amrullaev.maxwayclon.utils.extensions.config

class SuccessDialog(ctx: Context, private val message: String) : Dialog(ctx) {

    private lateinit var binding: DialogSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogSuccessBinding.inflate(layoutInflater)
        config(binding)
        binding.tvMessage.text = message

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}