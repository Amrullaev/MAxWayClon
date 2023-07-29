
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentProfileBinding
import com.amrullaev.maxwayclon.viewModels.ProfileViewModel
import com.amrullaev.maxwayclon.viewModels.impl.ProfileViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    private val viewBinding: FragmentProfileBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.nameFlow.onEach {
            viewBinding.tvUserName.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.phoneFlow.onEach {
            viewBinding.tvPhoneNumber.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.containerLocation.clicks().debounce(100L).onEach {
            viewModel.openBranches()
        }.launchIn(lifecycleScope)

        viewBinding.containerSettings.clicks().debounce(100L).onEach {
            viewModel.openSettings()
        }.launchIn(lifecycleScope)

        viewBinding.containerService.clicks().debounce(100L).onEach {
            viewModel.openServices()
        }.launchIn(lifecycleScope)

        viewBinding.imageEdit.clicks().debounce(100L).onEach {
            viewModel.editProfile()
        }.launchIn(lifecycleScope)

        viewModel.getData()
    }


}