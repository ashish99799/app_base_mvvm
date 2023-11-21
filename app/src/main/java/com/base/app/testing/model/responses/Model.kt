package com.base.app.testing.model.responses

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

enum class ResponseCode constructor(val code: Int) {
    OK(200),
    BadRequest(400),
    Unauthenticated(401),
    Unauthorized(403),
    NotFound(404),
    RequestTimeOut(408),
    Conflict(409),
    InValidateData(422),
    Blocked(423),
    ForceUpdate(426),
    ServerError(500);
}

@Keep
data class BaseModelResponse<T>(
    var status: Boolean,
    val message: String = "",
    val data: T? = null
) : Serializable

@Keep
data class BaseListResponse<T>(
    var status: Boolean,
    val message: String = "",
    val data: ArrayList<T>? = null
) : Serializable

@Keep
@Parcelize
data class JsonData(
    var gym_list: ArrayList<GymList>? = arrayListOf()
) : Parcelable

@Keep
@Parcelize
data class GymList(
    val id: Int,
    val title: String? = "",
    val date_time: String? = "",
    val image: String? = "",
    val favorite: Boolean? = false,
    val price: Double? = 0.0,
    val rating: Double? = 0.0,
    val popular_gym: List<PopularGym>? = arrayListOf()
) : Parcelable

@Keep
@Parcelize
data class PopularGym(
    val id: Int,
    val title: String? = "",
    val description: String? = "",
    val favorite: Boolean? = false,
    val price: Double? = 0.0,
    val rating: Double? = 0.0,
    val location: String? = "",
    val image: String? = ""
) : Parcelable

@Keep
@Parcelize
data class UserData(
    var login: String? = "",
    var id: Int? = 0,
    var following: Int? = 0,
    var followers: Int? = 0,
    var created_at: String? = "",
    var location: String? = "",
    var node_id: String? = "",
    var gravatar_id: String? = "",
    var url: String? = "",
    var email: String? = "",
    var repos_url: String? = "",
    var avatar_url: String? = ""
) : Parcelable

@Keep
@Parcelize
data class UserRepoData(
    var name: String? = "",
    var forks: Int? = 0,
    var stargazers_count: Int? = 0
) : Parcelable

@Keep
@Parcelize
data class DataResponse(
    var message: String? = "",
    var documentation_url: String? = "",
    var errors: ArrayList<Errors>? = arrayListOf(),
    var total_count: Int? = 0,
    var incomplete_results: Boolean? = false,
    var items: ArrayList<RowData>? = arrayListOf()
) : Parcelable

@Keep
@Parcelize
data class RowData(
    var login: String? = "",
    var id: Int? = 0,
    var node_id: String? = "",
    var gravatar_id: String? = "",
    var url: String? = "",
    var repos_url: String? = "",
    var avatar_url: String? = ""
) : Parcelable

@Keep
@Parcelize
data class Errors(
    var resource: String? = "",
    var code: String? = "",
    var field: String? = ""
) : Parcelable

@Keep
@Parcelize
data class LoginUserResponse(
    val username: String? = "",
    val companyname: String? = "",
    val firstname: String? = "",
    val lastname: String? = "",
    val gender: String? = "",
    val phone: String? = "",
    val email: String? = "",
    val dob: String? = "",
    val pincode: String? = "",
    var image: String? = "",
    val relational_status: String? = "",
    val education: String? = "",
    val occupation: String? = "",
    val religion: String? = "",
    val interest: String? = "",
    val hobbies: String? = "",
    val family_member: String? = "",
    val family_income: String? = "",
    val blood_group: String? = "",
    var token: String? = "",
    var fcmtoken: String? = "",
    val iskycverified: Int? = 0,  // 0 - InActive | 1 - Active
    val issuspended: Int? = 0,  // 0 - InActive | 1 - Active
    val walletamount: Double? = 0.0,
    val spendamount: Double? = 0.0,
    val referredBy: String? = "",
    val verifiydate: String? = "",
    val verifiedby: String? = "",
    val gstnumber: String? = "",
    val totalcoins: Long? = 0,
    val country: Int? = 0,
    val state: Int? = 0,
    val city: Int? = 0,
    val roleid: String? = "",
    val isverified: Int? = 0,
    val referralcode: String? = "",
    val createddate: String? = "",
    val created_at: String? = "",
    val last_updated: String? = "",
    val doc_id: String? = "",
    val msg: String? = ""
) : Parcelable