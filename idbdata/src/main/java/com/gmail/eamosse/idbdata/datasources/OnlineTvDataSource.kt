package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.TvResponse
import com.gmail.eamosse.idbdata.api.service.TvService
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.parse
import com.gmail.eamosse.idbdata.utils.safeCall



internal class OnlineTvDataSource(private val serviceTv: TvService) {


    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = serviceTv.getToken()
            response.parse()
        }
    }

    suspend fun getTvCategories(): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = serviceTv.getTvCategories()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTvsByCategoryId(categoryId: String, language: String): Result<List<TvResponse.Result>> {
        return safeCall {
            val response = serviceTv.getTvsByCategoryId(categoryId, language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTvById(tvId: String, language: String): Result<TvResponse.Result> {
        return safeCall {
            val response = serviceTv.getTvById(tvId.toInt(), language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }




}