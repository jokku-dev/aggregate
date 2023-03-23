package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.views.BigActionButton

@Composable
fun VerificationCodeScreen(
    navController: NavHostController
) {
    var otpValue by rememberSaveable { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp),
                text = stringResource(id = R.string.verification_code),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp),
                text = stringResource(id = R.string.you_need_to_enter_code),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                value = otpValue,
                onValueChange = { value -> if (value.length <= 4) otpValue = value },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    repeat(4) { index ->
                        val char = when {
                            index >= otpValue.length -> "-"
                            else -> otpValue[index].toString()
                        }
                        val isFocused = otpValue.length == index
                        Text(
                            modifier = Modifier
                                .size(72.dp)
                                .border(
                                    width = 1.dp,
                                    shape = MaterialTheme.shapes.medium,
                                    color = if (isFocused) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.secondary
                                )
                                .background(
                                    color = if (isFocused) MaterialTheme.colorScheme.surface
                                    else MaterialTheme.colorScheme.secondary
                                ),
                            text = char,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            BigActionButton(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.confirm)
            ) {
                TODO()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationCodeScreenPreview() {
    VerificationCodeScreen(navController = rememberNavController())
}