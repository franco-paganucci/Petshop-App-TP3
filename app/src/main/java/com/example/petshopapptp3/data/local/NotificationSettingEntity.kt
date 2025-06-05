package com.example.petshopapptp3.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_settings")
data class NotificationSettingEntity(
    @PrimaryKey val id: String,
    val enabled: Boolean
)
