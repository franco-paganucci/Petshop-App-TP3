package com.example.petshopapptp3.screens.login.createAccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.ui.theme.disableButton
import androidx.compose.runtime.*
import com.example.petshopapptp3.components.loginComponents.HaveAccount
import com.example.petshopapptp3.components.shared.ClickeableText

@Composable
fun CreateAccount(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLoginClick: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        TitleSection(stringResource(R.string.createAccount_Title))
        Spacer(modifier = Modifier.height(24.dp))
        SubtitleSection(stringResource(R.string.login_SubTitle_General))
        Spacer(modifier = Modifier.height(24.dp))
        InputField("Full Name")
        Spacer(modifier = Modifier.height(24.dp))
        InputField()
        Spacer(modifier = Modifier.height(24.dp))
        InputField("Password",true)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = "I Agree to the ",
                style = MaterialTheme.typography.bodySmall
            )
            ClickeableText("Terms of service ", onClick = onTermsClick)
            Text(
                text = "And ",
                style = MaterialTheme.typography.bodySmall
            )
            ClickeableText("Privacy Policy ", onClick = onPrivacyClick)
        }

        Spacer(modifier = Modifier.height(24.dp))
        HaveAccount(onLoginClick = onLoginClick)
        Spacer(modifier = Modifier.height(24.dp))
        StartButton(ButtonColor = disableButton, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCreateAccount() {
    var isChecked by remember { mutableStateOf(false) }

    CreateAccount(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        onTermsClick = {  },
        onPrivacyClick = {  },
        onLoginClick = { }
    )
}