package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.mypage_faq_button)

        val faqInfoList = ArrayList<FaqInfo>()
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_01), getString(R.string.faq_answer_01)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_02), getString(R.string.faq_answer_02)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_03), getString(R.string.faq_answer_03)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_04), getString(R.string.faq_answer_04)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_05), getString(R.string.faq_answer_05)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_06), getString(R.string.faq_answer_06)))
        faqInfoList.add(FaqInfo(getString(R.string.faq_question_last), getString(R.string.faq_answer_last)))

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
