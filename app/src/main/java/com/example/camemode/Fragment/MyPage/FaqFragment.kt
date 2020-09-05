package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camemode.Adapter.FaqListViewAdapter
import com.example.camemode.Fragment.BaseFragment
import com.example.camemode.Model.FaqInfo

import com.example.camemode.R
import kotlinx.android.synthetic.main.fragment_faq.*

class FaqFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val faqInfoList = ArrayList<FaqInfo>()
        faqInfoList.add(FaqInfo("質問文１", "回答文１"))
        faqInfoList.add(FaqInfo("質問文２", "回答文２"))
        faqInfoList.add(FaqInfo("質問文３", "回答文３"))
        faqInfoList.add(FaqInfo("質問文４", "回答文４"))
        faqInfoList.add(FaqInfo("質問文５", "回答文５"))

        val mAdapter = context?.let { FaqListViewAdapter(faqInfoList, parentFragmentManager, it) }
        faq_info_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = mAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
