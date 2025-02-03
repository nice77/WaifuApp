package ru.kpfu.minn.waifuapp.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIconButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
) {
    IconButton (
        onClick = onClick,
    ) {
        Icon(
            icon,
            contentDescription = text,
        )
    }
}
