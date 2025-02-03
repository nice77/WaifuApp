package ru.kpfu.minn.auth.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ru.kpfu.minn.auth.impl.R
import ru.kpfu.minn.auth.impl.ui.model.AuthState

@Composable
fun ObserveState(
    state: AuthState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmitClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text(LocalContext.current.getString(R.string.email)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text(LocalContext.current.getString(R.string.password)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = !state.isLoading,
        ) {
            Text(LocalContext.current.getString(R.string.submit))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = !state.isLoading,
        ) {
            Text(LocalContext.current.getString(R.string.register))
        }
    }
}
