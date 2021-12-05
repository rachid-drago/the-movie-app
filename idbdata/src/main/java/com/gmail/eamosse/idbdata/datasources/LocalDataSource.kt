package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


internal class LocalDataSource: KoinComponent {
    private val tokenDao: TokenDao by inject()

    suspend fun getToken() = withContext(Dispatchers.IO) {
        tokenDao.retrieve()
    }

    suspend fun saveToken(entity: TokenEntity) = withContext(Dispatchers.IO) {
        tokenDao.insert(entity)
    }
}