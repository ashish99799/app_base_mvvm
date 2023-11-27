package com.base.app.testing.model.api

import com.androidnetworking.common.Priority
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking

open class ApiMethod {

    fun rxGetRequest(
        path: String,
        body: HashMap<String, String>,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        return Rx2AndroidNetworking.get(path)
            .addHeaders(headers)
            .addQueryParameter(body)
            .setTag("GetRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun rxGetRequestPath(
        path: String,
        pathPrm: String,
        body: String,
        headers: HashMap<String, String>
    ): Rx2ANRequest {
        return Rx2AndroidNetworking.get(path)
            .addHeaders(headers)
            .addPathParameter(pathPrm, body)
            .setTag("GetRequest")
            .setPriority(Priority.HIGH)
            .build()
    }

}