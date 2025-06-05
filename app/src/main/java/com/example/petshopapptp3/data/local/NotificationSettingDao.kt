package com.example.petshopapptp3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationSettingDao {
    @Query("SELECT * FROM notification_settings")
    suspend fun getAllSettings(): List<NotificationSettingEntity>

    @Query("SELECT * FROM notification_settings WHERE id = :id")
    suspend fun getSettingById(id: String): NotificationSettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(setting: NotificationSettingEntity)
}
