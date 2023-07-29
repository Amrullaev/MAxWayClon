package com.amrullaev.maxwayclon.ui.registr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentLoginBinding
import com.amrullaev.maxwayclon.utils.extensions.*
import com.amrullaev.maxwayclon.viewModels.LoginViewModel
import com.amrullaev.maxwayclon.viewModels.impl.LoginViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    val viewBinding: FragmentLoginBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.btnCheck.clicks()
            .debounce(100L)
            .onEach {
                val phone = viewBinding.inputPhone.text.toString()
                val name = viewBinding.inputName.text.toString()
                viewModel.login(phone, name)
            }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.toastFlow.onEach {
            toast(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

    }
}