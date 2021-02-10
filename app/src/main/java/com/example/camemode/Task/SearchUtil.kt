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
import com.nifcloud.mbaas.core.NCMBBase
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchUtil {

    lateinit var listener: SearchedListener

    /**
     * ユーザ情報の最後の更新日保持用
     * （最下部スクロール時に使用）
     */
    object lastUpdateDate {
        var date: String? = null

        fun get(): Date? {
            val tz = TimeZone.getTimeZone("Asia/Tokyo")
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

            val calendar = Calendar.getInstance()
            calendar.time = format.parse(date)
            calendar.add(Calendar.HOUR_OF_DAY, 9)
            val time = calendar.time
            format.timeZone = tz
            return time
        }
        fun set(date: String) {
            this.date = date
        }
    }

    /**
     * ユーザ情報リスト
     */
    object userInfoList {
        var userInfoList: ArrayList<UserInfoModel> = arrayListOf()

        fun get(): ArrayList<UserInfoModel> {
            return userInfoList
        }

        fun add(userInfo: UserInfoModel) {
            userInfoList.add(userInfo)
        }

        fun clear() {
            userInfoList.clear()
        }
    }

    /**
     * ホーム画面表示時　検索メソッド
     */
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
                saveUserInfo(mutableList, false)
            }
        }
        return userInfoList.get()
    }

    /**
     * ホーム画面スクロール時　検索メソッド（自動更新用）
     */
    fun searchUserInfoAuto(): ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        query.addOrderByDescending("updateDate")
        query.setLimit(30)
        query.whereLessThan("updateDate", lastUpdateDate.get())

        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                saveUserInfo(mutableList, true)
            }
        }
        return userInfoList.get()
    }

    /**
     * かんたん検索画面用　検索メソッド
     */
    fun searchUserInfo(categoryRole: Int?, region: Int?): ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        var queryA = NCMBQuery<NCMBObject>("UserInfo")
        var queryB = NCMBQuery<NCMBObject>("UserInfo")

        queryA.whereEqualTo(FIELD_CATEGORY_ROLE, categoryRole)
        queryB.whereEqualTo(FIELD_CATEGORY_ROLE, 2)

        query.or(Arrays.asList(queryA, queryB) as Collection<NCMBQuery<NCMBBase>>?)
        query.addOrderByDescending("updateDate")
        query.whereEqualTo(FIELD_REGION, region)
        query.setLimit(7)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
                saveUserInfo(mutableList, false)
            }
        }
        return userInfoList.get()
    }

    /**
     * かんたん検索画面用　検索メソッド（自動更新用）
     */
    fun searchUserInfoAuto(categoryRole: Int?, region: Int?): ArrayList<UserInfoModel>? {
        var query = NCMBQuery<NCMBObject>("UserInfo")
        var queryA = NCMBQuery<NCMBObject>("UserInfo")
        var queryB = NCMBQuery<NCMBObject>("UserInfo")

        queryA.whereEqualTo(FIELD_CATEGORY_ROLE, categoryRole)
        queryB.whereEqualTo(FIELD_CATEGORY_ROLE, 2)

        query.or(Arrays.asList(queryA, queryB) as Collection<NCMBQuery<NCMBBase>>?)
        query.addOrderByDescending("updateDate")
        query.whereLessThan("updateDate", lastUpdateDate.get())
        query.whereEqualTo(FIELD_REGION, region)
        query.setLimit(7)
        query.findInBackground { mutableList, ncmbException ->
            if (ncmbException != null) {
                Log.d("ERROR", "NCMB findInBackground error: " + ncmbException.message)
            } else {
                for (obj in mutableList) {
                    Log.d("TEST", obj.getString("displayName"))
                }
                saveUserInfo(mutableList, true)
            }
        }
        return userInfoList.get()
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
                saveUserInfo(mutableList, false)
            }
        }
        return userInfoList.get()
    }

    /**
     * 詳細検索画面用　検索メソッド（自動更新用）
     */
    fun searchUserInfoAuto(categoryRoleIndex: Int?,
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
                saveUserInfo(mutableList, false)
            }
        }
        return userInfoList.get()
    }

    fun saveUserInfo(list: List<NCMBObject>, isAutoLord: Boolean) {
        if (!isAutoLord) {
            // 2回目以降の更新時のため、リストを初期化
            userInfoList.clear()
        }

        for (obj in list)
            userInfoList.add(convertUserInfo(obj))

        if (listener != null)
            listener.onSuccess(userInfoList.get())

        // 取得したユーザ情報、最後データの更新日時を取得
        if (list.isNotEmpty()) {
            lastUpdateDate.set(list.get(list.size-1).getString("updateDate"))
        }
    }

    fun saveUserInfoAutoLoad(list: List<NCMBObject>) {
        for (obj in list)
            userInfoList.add(convertUserInfo(obj))

        if (listener != null)
            listener.onSuccess(userInfoList.get())

        if (list.isNotEmpty()) {
            lastUpdateDate.set(list.get(list.size-1).getString("updateDate"))
        }
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