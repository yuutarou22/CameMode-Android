package com.example.camemode.Fragment.MyPage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camemode.Fragment.BaseFragment

import com.example.camemode.R
import com.example.camemode.UserInfoRegistActivity
import kotlinx.android.synthetic.main.content_my_page_my_data.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.my_data

class MyPageFragment : BaseFragment() {
    private var listener: OnFragmentInteractionListener? = null

    /**
     * ローカル保持用
     */
    lateinit var data : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = context!!.getSharedPreferences("UserInfoData", Context.MODE_PRIVATE)
        editor = data.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_page, container, false)
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
        // ToDo:レイアウトを共通化する

        outputSharedPreferences()

        if (data.contains("displayName")) {
            my_data_label.text = "マイデータ編集"
        }

        my_data.setOnClickListener {
            if (data.contains("displayName")) {
                // 登録している場合
                showFragment(MyDataShowFragment())
            } else {
                // 登録していない場合
                val intent = Intent(context, UserInfoRegistActivity::class.java)
                startActivity(intent)
            }
        }

        faq.setOnClickListener {
            showFragment(FaqFragment())
        }

        app_term.setOnClickListener {
            showFragment(AppTermFragment())
        }
    }

    private fun outputSharedPreferences() {
        android.util.Log.d("TEST", "SharedPreferences---objectId: ${data.getString("objectId","")}")
        android.util.Log.d("TEST", "SharedPreferences---categoryRole: ${data.getInt("categoryRole",1)}")
        android.util.Log.d("TEST", "SharedPreferences---displayName: ${data.getString("displayName","")}")
        android.util.Log.d("TEST", "SharedPreferences---twitterId: ${data.getString("twitterId","")}")
        android.util.Log.d("TEST", "SharedPreferences---charge: ${data.getInt("charge",1)}")
        android.util.Log.d("TEST", "SharedPreferences---region: ${data.getInt("region",1)}")
        android.util.Log.d("TEST", "SharedPreferences---sex: ${data.getInt("sex",1)}")
        android.util.Log.d("TEST", "SharedPreferences---age: ${data.getInt("age",1)}")
        android.util.Log.d("TEST", "SharedPreferences---photoImage: ${data.getString("photoImage","")}")
    }
}
