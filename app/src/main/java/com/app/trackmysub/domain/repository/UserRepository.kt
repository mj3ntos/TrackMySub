package com.app.trackmysub.domain.repository

import com.app.trackmysub.data.local.dao.UserDao
import com.app.trackmysub.domain.model.entity.UserEntity
import com.app.trackmysub.data.local.enums.Gender
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    val currentUser = userDao.getCurrentUser()

    suspend fun createUser(
        firstName: String,
        lastName: String?,
        gender: Gender,
        birthDate: Long,
        profileImg: String?
    ) {
        userDao.upsertUser(
            UserEntity(
                userId = "local_user",
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                birthDate = birthDate,
                profileImg = profileImg
            )
        )
    }

    suspend fun updateProfile(
        firstName: String? = null,
        lastName: String? = null,
        gender: Gender? = Gender.NOT_SPECIFIED,
        birthDate: Long? = null,
        profileImg: String? = null
    ) {
        currentUser.firstOrNull()?.let { user ->
            userDao.upsertUser(
                user.copy(
                    firstName = firstName ?: user.firstName,
                    lastName = lastName ?: user.lastName,
                    gender = gender ?: user.gender,
                    birthDate = birthDate ?: user.birthDate,
                    profileImg = profileImg ?: user.profileImg
                )
            )
        }
    }
}