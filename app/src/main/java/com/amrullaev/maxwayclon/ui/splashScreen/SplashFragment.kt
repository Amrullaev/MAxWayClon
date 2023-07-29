
import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.viewModels.SplashViewModel
import com.amrullaev.maxwayclon.viewModels.impl.SplashViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onResume() {
        super.onResume()
        viewModel.navigate()
    }
}