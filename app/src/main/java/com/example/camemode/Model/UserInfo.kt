package com.example.camemode.Model

import kotlinx.serialization.Serializable

class UserInfo {

    companion object {
        const val FIELD_OBJECT_ID = "objectId"
        const val FIELD_CATEGORY_ROLE = "categoryRole"
        const val FIELD_DISPLAY_NAME = "displayName"
        const val FIELD_PHOTO_IMAGE = "photoImage"
        const val FIELD_TWITTER_ID = "twitterId"
        const val FIELD_AGE = "age"
        const val FIELD_REGION = "region"
        const val FIELD_SEX = "sex"
        const val FIELD_CHARGE = "charge"
    }
}

@Serializable
data class UserInfoModel(
    var objectId: String,
    var categoryRole: Int,
    var displayName: String,
    var photoImage: String,
    var twitterId: String,
    var age: Int,
    var region: Int,
    var sex: Int,
    var charge: Int
)