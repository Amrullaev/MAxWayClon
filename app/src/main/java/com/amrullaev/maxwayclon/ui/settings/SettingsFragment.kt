import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.prefs.MySharedPref
import com.amrullaev.maxwayclon.databinding.FragmentSettingsBinding
import com.amrullaev.maxwayclon.ui.dialogs.ChooseLanguageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var mySharedPref: MySharedPref
    private val viewBinding: FragmentSettingsBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewBinding.containerLanguage.clicks().debounce(100L).onEach {
            val dialog = ChooseLanguageDialog()
            dialog.setChangeLanguageListener {
                changeLanguage()
            }
            dialog.show(childFragmentManager, "language")
        }.launchIn(lifecycleScope)

        changeLanguage()
    }


    private fun changeLanguage() {
        viewBinding.tvLan.text =
            resources.getString(if (mySharedPref.language == 0) R.string.uzbek else R.string.english)
    }
}