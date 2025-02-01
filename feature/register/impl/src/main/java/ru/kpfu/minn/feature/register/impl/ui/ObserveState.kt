package ru.kpfu.minn.feature.register.impl.ui

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
import ru.kpfu.minn.feature.register.impl.R
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterState

@Composable
fun ObserveState(
    state: RegisterState,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRetypeChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    onSignInClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.username,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onUsernameChanged,
            label = { Text(LocalContext.current.getString(R.string.username)) },
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = state.email,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onEmailChanged,
            label = { Text(LocalContext.current.getString(R.string.email)) },
            isError = !state.isEmailValid,
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = state.password,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onPasswordChanged,
            label = { Text(LocalContext.current.getString(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = !state.arePasswordsEqual,
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = state.passwordRetype,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onPasswordRetypeChanged,
            label = { Text(LocalContext.current.getString(R.string.retype_password)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = !state.arePasswordsEqual,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSubmitClicked,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = !state.isLoading && state.arePasswordsEqual && state.isEmailValid,
        ) { Text(LocalContext.current.getString(R.string.submit)) }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = onSignInClicked,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = !state.isLoading && state.arePasswordsEqual && state.isEmailValid,
        ) { Text(LocalContext.current.getString(R.string.sign_in)) }
    }
}
