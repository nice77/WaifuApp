package ru.kpfu.minn.feature.profile.impl.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kpfu.minn.core.systemdesign.theme.ShimmerColorShades

@Composable
fun ShimmerItem(
    modifier: Modifier = Modifier,
    brush: Brush
) {
    Column {
        Spacer(
            modifier = modifier
                .background(brush = brush)
                .heightIn(128.dp)
        )
    }
}

@Composable
fun ShimmerAnimation(
    modifier: Modifier = Modifier,
) {
    val limit = 1000f
    val transition = rememberInfiniteTransition()
    val progressAnimated by transition.animateFloat(
        initialValue = -limit,
        targetValue = limit,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "shimmer"
    )
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(progressAnimated, 0f),
        end = Offset(progressAnimated + 2000f, 0f)
    )
    ShimmerItem(
        modifier = modifier,
        brush = brush,
    )
}


@Composable
@Preview(showBackground = true)
fun kek() {
    ShimmerAnimation(
        modifier = Modifier
            .fillMaxWidth()
    )
}