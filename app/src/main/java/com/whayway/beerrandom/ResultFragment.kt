package com.whayway.beerrandom

import android.content.Intent
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.whayway.beerrandom.ResultFragmentArgs.Companion.fromBundle
import com.whayway.beerrandom.databinding.FragmentGameBinding
import com.whayway.beerrandom.databinding.FragmentResultBinding
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {
   // private lateinit var viewModel: ResultViewModel
   // private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result, container, false)

       binding.playAgainButton.setOnClickListener { view : View ->
           view.findNavController().navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
       }
       val args = ResultFragmentArgs.fromBundle(requireArguments())
/*       Toast.makeText(context, "NumCorrect: ${args.score}", Toast.LENGTH_LONG).show()*/
       if(args.score < 15){
           binding.imageView.setImageResource(R.drawable.lech_free)
       }else if((args.score > 15) || (args.score < 30)){
           binding.imageView.setImageResource(R.drawable.lech_free)
       }else{
           binding.imageView.setImageResource(R.drawable.lech_free)
       }
        return binding.root
    }
    // Creating our Share Intent
    private fun getShareIntent() : Intent {
        //retriving data, like for toas massage
        val args = ResultFragmentArgs.fromBundle(requireArguments())
        //to deliver the message that the user wants to share.

        val shareIntent = Intent(Intent.ACTION_SEND)
        //setType sets MIME type
        //The actual data to be delivered is specified in the EXTRA_TEXT. (The share_success_text string is defined in the strings.xml resource file.
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.about))
        return shareIntent
    }
    // Starting an Activity with our new Intent
    //. This method gets the Intent from getShareIntent() and calls startActivity() to begin sharing.
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.winner_menu, menu)
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)    }

}