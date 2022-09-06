package com.bubu.apiinterface

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.EOFException
import java.net.SocketTimeoutException

class UserAuthModule(override val userData : Any?) : UserApiInterface {
    interface UserAuthModuleInterface {
        @Headers("Content-Type: application/json")
        @POST("/api/accounts/v1/token/verify/")
        fun get(
            @Body body: JsonObject
        ): Call<Any>
        //보내는 데이터 형식
    }

    interface UserRefreshTokenInterface {
        @Headers("Content-Type: application/json")
        @POST("/api/accounts/v1/token/refresh/")
        fun get(
            @Body body: JsonObject
        ): Call<Any>
        //보내는 데이터 형식
    }

    /**
     *
     * {
    "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjYyMjA5Nzc3LCJpYXQiOjE2NjIyMDg4MTcsImp0aSI6ImQ0Y2ZlNDZiNTExNzQ5YmE5YWJmY2NhNDcwNGRlN2JiIiwidXNlcl9pZCI6MTZ9.DK8tSdwFYqHWC8UMNMzhPMv4n7COq0r5flayAko3-JM",
    "access_token_expiration": "2022-09-03T12:56:17.841231Z"
    }
     */

    //{"refresh" : "" }


    private suspend fun refreshTokenData(): Any {
        val retrofit = Retrofit.Builder()
            .baseUrl(super.serverAddress)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitObject = retrofit.create(UserRefreshTokenInterface::class.java)
        try {
            var data = JsonObject()
            data.addProperty("refresh", userInformation.refreshToken)
            var resp = retrofitObject.get(data).execute()
            if (resp.code() in 100..199) {
                return super.handle100(resp)
            } else if (resp.code() in 200..299) {
                var responseBody = super.handle200(resp)
                val jsonObject: JsonObject = Gson().toJsonTree(responseBody).asJsonObject
                userInformation.accessToken = jsonObject.get("access").toString().replace("\"", "")
                Log.d("result2", "token updated!")
                return true
            } else if (resp.code() in 300..399) {
                return super.handle300(resp)
            } else if (resp.code() in 400..499) {
                return refreshTokenData()
            } else {
                return super.handle500(resp)
            }
        } catch (e: SocketTimeoutException) {
            Log.d("TimeOutException Maybe Server Closed", e.toString())
            return e
        } catch (e: EOFException) {
            Log.d("EOFException Maybe Response Data Type Mismatch", e.toString())
            return e
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return e
        }
    }

    override suspend fun getApiData(): Any? {
        val retrofit = Retrofit.Builder()
            .baseUrl(super.serverAddress)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitObject = retrofit.create(UserAuthModuleInterface::class.java)
        try {
            var accessTokenObject = JsonObject()
            accessTokenObject.addProperty("token", userInformation.accessToken)
            var resp = retrofitObject.get(accessTokenObject).execute()
            if (resp.code() in 100..199) {
                return super.handle100(resp)
            } else if (resp.code() in 200..299) { //if successful -> continue
                Log.d("result", "accessToken is Valid!")
                return true
            } else if (resp.code() in 300..399) {
                return super.handle300(resp)
            } else if (resp.code() in 400..499) { //must require refresh!
                Log.d("result", "accessToken is inValid!")
                return refreshTokenData()
            } else {
                return super.handle500(resp)
            }
        } catch (e: UninitializedPropertyAccessException) {
            Log.d("UninitializedPropertyAccessException", "must be userInformation class init!!")
            return e
        } catch (e: SocketTimeoutException) {
            Log.d("TimeOutException Maybe Server Closed", e.toString())
            return e
        } catch (e: EOFException) {
            Log.d("EOFException Maybe Response Data Type Mismatch", e.toString())
            return e
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return e
        }
    }


}


