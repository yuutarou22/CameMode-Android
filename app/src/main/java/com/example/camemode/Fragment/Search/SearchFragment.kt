package com.example.camemode.Fragment.Search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Fragment.BaseFragment

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // このViewは戻り値
        return inflater.inflate(R.layout.fragment_search, container, false)
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
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    private fun initView() {
        search_simple_button?.setOnClickListener {
            showFragment(SearchSimpleFragment())
        }

        search_detail_button.setOnClickListener {
            showFragment(SearchDetailFragment())
        }
    }
}
