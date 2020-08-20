package com.example.camemode.Task

import android.util.Log
import com.example.camemode.Model.UserInfo.Companion.FIELD_AGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CATEGORY_ROLE
import com.example.camemode.Model.UserInfo.Companion.FIELD_CHARGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_DISPLAY_NAME
import com.example.camemode.Model.UserInfo.Companion.FIELD_OBJECT_ID
import com.example.camemode.Model.UserInfo.Companion.FIELD_PHOTO_IMAGE
import com.example.camemode.Model.UserInfo.Companion.FIELD_REGION
import com.example.camemode.Model.UserInfo.Companion.FIELD_SEX
import com.example.camemode.Model.UserInfo.Companion.FIELD_TWITTER_ID
import com.example.camemode.Model.UserInfoModel
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery

class SearchUtil {

    lateinit var listener: SearchedListener
    var userInfoList: ArrayList<UserInfoModel> = arrayListOf()

    fun searchUserInfo(): ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        query.addOrderByDescending("updateDate")
        query.setLimit(15)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
                saveUserInfo(mutableList)
            }
        }
        return userInfoList
    }

    /**
     * かんたん検索画面用　検索メソッド
     */
    fun searchUserInfo(categoryRole: Int?, region: Int?): ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        query.addOrderByDescending("updateDate")
        query.whereEqualTo(FIELD_CATEGORY_ROLE, categoryRole)
        query.whereEqualTo(FIELD_REGION, region)
        query.setLimit(15)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
                saveUserInfo(mutableList)
            }
        }
        return userInfoList
    }

    /**
     * 詳細検索画面用　検索メソッド
     */
    fun searchUserInfo(categoryRoleIndex: Int?,
                       whichChargeIndex: Int?,
                       regionIndex: Int?,
                       sexIndex: Int?,
                       ageIndex: Int?)
            :ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")

        query.addOrderByDescending("updateDate")
        query.whereEqualTo(FIELD_CATEGORY_ROLE, categoryRoleIndex)
        query.whereEqualTo(FIELD_CHARGE, whichChargeIndex)
        query.whereEqualTo(FIELD_REGION, regionIndex)
        query.whereEqualTo(FIELD_SEX, sexIndex)
        query.whereEqualTo(FIELD_AGE, ageIndex)

        query.setLimit(15)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
                saveUserInfo(mutableList)
            }
        }
        return userInfoList
    }

    fun saveUserInfo(list: List<NCMBObject>) {
        // 2回目以降の更新時のため、リストを初期化
        userInfoList.clear()

        for (obj in list)
            userInfoList.add(convertUserInfo(obj))

        if (listener != null)
            listener.onSuccess(userInfoList)
    }

    fun convertUserInfo(obj: NCMBObject): UserInfoModel {
        var item: UserInfoModel = UserInfoModel(
            objectId = obj.getString(FIELD_OBJECT_ID),
            categoryRole = obj.getInt(FIELD_CATEGORY_ROLE),
            displayName = obj.getString(FIELD_DISPLAY_NAME),
            photoImage = obj.getString(FIELD_PHOTO_IMAGE),
            twitterId = obj.getString(FIELD_TWITTER_ID),
            age = obj.getInt(FIELD_AGE),
            region = obj.getInt(FIELD_REGION),
            sex = obj.getInt(FIELD_SEX),
            charge = obj.getInt(FIELD_CHARGE)
        )
        return item
    }

    fun setSearchLitener(_listener: SearchedListener) {
        this.listener = _listener
    }

    interface SearchedListener {
        fun onSuccess(list: ArrayList<UserInfoModel>)
    }

}