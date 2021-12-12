package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.api.response.toTv
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.data.Tv
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineTvDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.gmail.eamosse.idbdata.utils.Result

/**
 * La classe permettant de gérer les données de l'application
 */
class TvRepository : KoinComponent {
    // Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    // Gestion des sources de données en lignes
    private val online: OnlineTvDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Succes -> {
                // save the response in the local database
                local.saveToken(result.data.toEntity())
                // return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getTvCategories(): Result<List<Category>> {
        return when (val result = online.getTvCategories()) {
            is Result.Succes -> {
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTvsByCategoryId(categoryId: String, language: String): Result<List<Tv>> {
        return when (val result = online.getTvsByCategoryId(categoryId, language)) {
            is Result.Succes -> {
                val results = result.data.map {
                    it.toTv()
                }
                Result.Succes(results)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTvById(tvId: String, language: String): Result<Tv> {
        return when (val result = online.getTvById(tvId, language)) {
            is Result.Succes -> {
                val result = result.data.toTv()
                Result.Succes(result)
            }
            is Result.Error -> result
        }
    }
}
