package com.example.camemode.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Model.UserInfo

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_user_info_detail.*

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    protected fun showFragment(f: Fragment) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.replace(R.id.main_content, f)
        fragmentTransaction?.commit()
    }

    fun setupSnsButton(twitterId: String?, instagramId: String?) {

        // TwitterIDが空の場合
        if (twitterId.isNullOrEmpty()) {
            twitter_transition_button.visibility = View.GONE
        }

        // InstagramIDが空の場合
        if (instagramId.isNullOrEmpty()) {
            instagram_transition_button.visibility = View.GONE
        }

        twitter_transition_button.setOnClickListener {
            val uri: Uri = Uri.parse(context?.resources?.getString(R.string.twitter_url) + arguments?.getString(
                UserInfo.FIELD_TWITTER_ID
            ) + "/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        instagram_transition_button.setOnClickListener {
            val uri: Uri = Uri.parse(context?.resources?.getString(R.string.instagram_url) + arguments?.getString(
                UserInfo.FIELD_INSTAGRAM_ID
            ) + "/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
