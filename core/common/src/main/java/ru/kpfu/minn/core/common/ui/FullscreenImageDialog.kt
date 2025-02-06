package ru.kpfu.minn.core.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.kpfu.minn.core.common.R


@Composable
fun FullscreenImageDialog(
    modifier: Modifier = Modifier,
    imageUrl: String,
    isLiked: MutableState<Boolean>,
    onDismissDialogClicked: () -> Unit,
    onSetAsWallpaperClicked: () -> Unit,
    onLikeClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { onDismissDialogClicked() },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.Center,
        ) {
            SubcomposeAsyncImage (
                model = imageUrl,
                contentDescription = "Enlarged Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                loading = {
                    ShimmerAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 512.dp)
                    )
                }
            )
            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  },
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    IconButton(
                        onClick = { onSetAsWallpaperClicked() },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = LocalContext.current.getString(R.string.set_as_profile_picture)
                        )
                    }
                    IconButton(
                        onClick = { onLikeClicked() },
                    ) {
                        val currentImageState by remember { isLiked }
                        Icon(
                            imageVector = if (currentImageState) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                            contentDescription = LocalContext.current.getString(R.string.like)
                        )
                    }
                }
            }
        }
    }
}