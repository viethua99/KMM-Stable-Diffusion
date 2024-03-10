package com.vproject.stablediffusion.repository.userdata

import com.vproject.stablediffusion.UserDataEntity
import com.vproject.stablediffusion.database.dao.UserDataDao
import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.model.UserData
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

class UserDataRepositoryImpl(
    private val userDataDao: UserDataDao
) : UserDataRepository {
//    /**
//     * Stream of [UserData]
//     */
//    private val _userData = MutableStateFlow(UserData(DarkThemeConfig.DARK))
//    override val userData = _userData.map {
//        withContext(stableDiffusionDispatchers.io) {
//            val userDataEntity = userDataDao.getUserDataById(0)
//            userDataEntity?.let { nonNullUserDataEntity ->
//                return@withContext nonNullUserDataEntity.toUserData()
//            } ?: run {
//                userDataDao.insert(UserDataEntity(0, DarkThemeConfig.DARK.name))
//                return@withContext UserData(DarkThemeConfig.DARK)
//            }
//        }
//    }

    override suspend fun getUserData(): Flow<UserData> {
        return userDataDao.getUserDataById(0).map { userDataEntity ->
            userDataEntity?.let { nonNullUserDataEntity ->
                return@map nonNullUserDataEntity.toUserData()
            } ?: run {
                userDataDao.insert(UserDataEntity(0, DarkThemeConfig.DARK.name))
                return@map UserData(DarkThemeConfig.DARK)
            }
        }
    }
    /**
     * Sets the desired dark theme config.
     */
    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        withContext(stableDiffusionDispatchers.io) {
            userDataDao.insert(UserDataEntity(0, darkThemeConfig.name))
        }
    }
}