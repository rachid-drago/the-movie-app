package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.FilmResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Film
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.parse
import com.gmail.eamosse.idbdata.utils.safeCall

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getCategories()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }

    /**
     * Get films via Gender
     */
    suspend fun getFilms(categoryId :String): Result<List<FilmResponse.Film>> {
        return safeCall {
            val response = service.getFilms(categoryId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.films)
                is Result.Error -> result
            }
        }
    }

    /**
     * Get film via id
     */



    suspend fun getFilm(filmId: String): Result<FilmResponse.Film> {
        return safeCall {
            val response = service.getFilmById(filmId.toInt())
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }

}



