package com.base.app.testing.model.repositorys

import com.application.base.AppBaseRepository
import com.base.app.testing.model.api.ApiClient
import com.base.app.testing.model.responses.DataResponse
import com.base.app.testing.model.responses.UserData
import com.base.app.testing.model.responses.UserRepoData
import io.reactivex.Observable


// Repository
class AppRepository : AppBaseRepository<ApiClient>() {

    override fun setApiClass() = ApiClient()

    fun getSearchUser(query: String, page: Int): Observable<DataResponse> {
        // Rx2AndroidNetworking API Calling
        return api.getUserSearch(query, page)
    }

    fun onUserInfo(query: String): Observable<UserData> {
        // Rx2AndroidNetworking API Calling
        return api.getUserInfo(query)
    }

    fun onUserRepo(query: String): Observable<List<UserRepoData>> {
        // Rx2AndroidNetworking API Calling
        return api.getUserRepo(query)
    }
}