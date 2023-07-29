import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentPasswordCheckoutBinding
import com.amrullaev.maxwayclon.utils.extensions.toast
import com.amrullaev.maxwayclon.viewModels.PasswordCheckoutViewModel
import com.amrullaev.maxwayclon.viewModels.impl.PasswordCheckoutViewModelImpl
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class PasswordCheckoutFragment : Fragment(R.layout.fragment_password_checkout) {


    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var verificationId: String

    private val viewModel: PasswordCheckoutViewModel by viewModels<PasswordCheckoutViewModelImpl>()

    private val viewBinding: FragmentPasswordCheckoutBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firebaseAuth = FirebaseAuth.getInstance()
        sendVerificationCode("+998942929333")
        viewBinding.btnCheck.clicks()
            .debounce(100L)
            .onEach {
                val sms = viewBinding.smsChecker.enteredCode
                viewModel.check(sms, sms)
            }.launchIn(lifecycleScope)
    }


    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    verificationId = p0
                }

                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    val code = p0.smsCode
                    if (code != null) {
                        verificationId =code
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    toast(p0.localizedMessage!!)
                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}