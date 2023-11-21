package com.base.app.testing.model.api

import com.base.app.testing.model.responses.DataResponse
import com.base.app.testing.model.responses.UserData
import com.base.app.testing.model.responses.UserRepoData
import com.base.app.testing.util.BASE_URL
import io.reactivex.Observable

class ApiClient : ApiMethod() {

    fun getUserSearch(q: String, page: Int): Observable<DataResponse> {
        return rxGetRequest(
            BASE_URL.plus("search/users"),
            headers = hashMapOf("content-type" to "application/json; charset=utf-8"),
            body = hashMapOf("q" to q, "page" to page.toString())
        ).getObjectObservable(DataResponse::class.java)
    }

    fun getUserInfo(user_name: String): Observable<UserData> {
        return rxGetRequestPath(
            BASE_URL.plus("users/{user}"),
            headers = hashMapOf("content-type" to "application/json; charset=utf-8"),
            pathPrm = "user",
            body = user_name
        ).getObjectObservable(UserData::class.java)
    }

    fun getUserRepo(user_name: String): Observable<List<UserRepoData>> {
        return rxGetRequestPath(
            BASE_URL.plus("users/{user}/repos"),
            headers = hashMapOf("content-type" to "application/json; charset=utf-8"),
            pathPrm = "user",
            body = user_name
        ).getObjectListObservable(UserRepoData::class.java)
    }

}
