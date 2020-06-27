package com.example.camemode.Task

import android.util.Log
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery

class SearchUtil {

    fun searchUserInfo() {
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
            }
        }
    }

}