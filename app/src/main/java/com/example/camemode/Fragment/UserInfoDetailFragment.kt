package com.example.camemode.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_user_info_detail.*

class UserInfoDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mInflatedView : View = inflater.inflate(R.layout.fragment_user_info_detail, container, false)
        return setInflateFragment(mInflatedView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun initView() {
        back_button.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    private fun setInflateFragment(inflatedView: View): View {
        
        return inflatedView
    }
}
