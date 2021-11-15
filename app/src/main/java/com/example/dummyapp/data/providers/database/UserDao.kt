package com.example.dummyapp.data.providers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummyapp.data.models.database.UserEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity WHERE page = :page")
    fun getAll(page: Int): Single<List<UserEntity>>
    
    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    fun getById(userId: String): Single<UserEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(userEntities: List<UserEntity>)
    
    @Query("UPDATE UserEntity SET gender = :gender, email = :email, dateOfBirth = :dateOfBirth, registerDate = :registerDate, phone = :phone, street = :street, city = :city, state = :state, country = :country WHERE id = :userId")
    fun updateUser(
        userId: String,
        gender: String,
        email: String,
        dateOfBirth: String,
        registerDate: String,
        phone: String,
        street: String,
        city: String,
        state: String,
        country: String
    )
}