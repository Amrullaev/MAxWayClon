package com.amrullaev.maxwayclon.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.amrullaev.maxwayclon.databinding.DialogConfirmBinding
import com.amrullaev.maxwayclon.utils.extensions.config

class ConfirmDialog(ctx: Context) : Dialog(ctx) {

    private var confirmClickListener: (() -> Unit)? = null

    private lateinit var binding: DialogConfirmBinding

    fun setConfirmClickListener(block: () -> Unit) {
        confirmClickListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogConfirmBinding.inflate(layoutInflater)
        config(viewBinding = binding)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnConfirm.setOnClickListener {
            confirmClickListener?.invoke()
            dismiss()
        }
    }

}