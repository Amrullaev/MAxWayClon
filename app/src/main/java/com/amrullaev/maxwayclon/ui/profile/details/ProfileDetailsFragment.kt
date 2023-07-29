
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentProfileDetailsBinding
import com.amrullaev.maxwayclon.ui.dialogs.ChooseDateDialog
import com.amrullaev.maxwayclon.utils.extensions.getCurrentDate
import com.amrullaev.maxwayclon.utils.extensions.toDate
import com.amrullaev.maxwayclon.viewModels.ProfileDetailsViewModel
import com.amrullaev.maxwayclon.viewModels.impl.ProfileDetailsViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

@AndroidEntryPoint
class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private val viewModel: ProfileDetailsViewModel by viewModels<ProfileDetailsViewModelImpl>()

    private val viewBinding: FragmentProfileDetailsBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.nameFlow.onEach {
            viewBinding.inputName.setText(it)
        }.launchIn(lifecycleScope)

        viewModel.phoneFlow.onEach {
            viewBinding.inputPhone.text = it
        }.launchIn(lifecycleScope)

        viewModel.birthdayFlow.onEach {
            viewBinding.inputBirthday.text = it
        }.launchIn(lifecycleScope)

        viewModel.openCalendarDialog.onEach {
            val dialog = ChooseDateDialog(
                requireContext(),
                viewBinding.inputBirthday.text.toString().toDate()
            )
            dialog.setConfirmClickListener {
                viewBinding.inputBirthday.text = getCurrentDate(it)
            }
            dialog.show()
        }.launchIn(lifecycleScope)

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.inputBirthday.setOnClickListener {
            viewModel.openCalendar()
        }
        viewBinding.btnConfirm.clicks().debounce(100L).onEach {
            viewModel.saveClicked(
                viewBinding.inputName.text.toString(),
                viewBinding.inputBirthday.text.toString()
            )
            findNavController().navigateUp()
        }.launchIn(lifecycleScope)
    }

}