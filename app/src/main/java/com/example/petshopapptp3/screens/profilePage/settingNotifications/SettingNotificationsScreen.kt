package com.example.petshopapptp3.screens.profilePage.settingNotifications

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.data.local.AppDatabase
import com.example.petshopapptp3.data.local.NotificationSettingEntity
import kotlinx.coroutines.launch

@Composable
fun SettingNotificationsScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val db = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "app-db").build()
    }
    val dao = db.notificationSettingDao()

    var likedPost by remember { mutableStateOf(true) }
    var newMessage by remember { mutableStateOf(true) }
    var itemSold by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        likedPost = dao.getSettingById("liked_post")?.enabled ?: true
        newMessage = dao.getSettingById("new_message")?.enabled ?: true
        itemSold = dao.getSettingById("item_sold")?.enabled ?: true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ArrowTitle("Notification"){
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Social",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotificationRow("Liked Post", likedPost) {
            likedPost = it
            scope.launch {
                dao.upsert(NotificationSettingEntity("liked_post", it))
            }
        }

        NotificationRow("New Message", newMessage) {
            newMessage = it
            scope.launch {
                dao.upsert(NotificationSettingEntity("new_message", it))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Store",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotificationRow("Item Sold", itemSold) {
            itemSold = it
            scope.launch {
                dao.upsert(NotificationSettingEntity("item_sold", it))
            }
        }
    }
}

@Composable
fun NotificationRow(title: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 14.sp)
        Switch(
            checked = checked,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF7B61FF),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}