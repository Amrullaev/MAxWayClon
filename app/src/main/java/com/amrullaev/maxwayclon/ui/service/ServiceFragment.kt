
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentServiceBinding

class ServiceFragment : Fragment(R.layout.fragment_service) {

    private val viewBinding: FragmentServiceBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.containerContingencyPolicy.setOnClickListener {
            val urlString = "https://maxway.uz/about"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.android.chrome")
            requireContext().startActivity(intent)
        }

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }


}