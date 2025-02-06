package ru.kpfu.minn.feature.search.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.kpfu.minn.core.common.ui.ShimmerAnimation
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.search.impl.R
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsMenu(
    state: SearchState,
    stringResProvider: StringResProvider,
    onSafeSearchChecked: (Boolean) -> Unit,
    onApplyFilterClicked: () -> Unit,
    onTagClicked: (TagUiModel) -> Unit,
) {
    AnimatedVisibility (
        visible = state.isImageSearch,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ){
        Column {
            OutlinedIconToggleButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                checked = state.isSafeSearchEnabled,
                onCheckedChange = onSafeSearchChecked,
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    text = stringResProvider.getString(R.string.nsfw),
                    modifier = Modifier
                        .padding(4.dp),
                )
            }
            FlowRow(
                overflow = FlowRowOverflow.Visible,
                maxLines = 1,
            ) {
                if (!state.areTagsLoaded) {
                    repeat((0 until 10).count()) {
                        ShimmerAnimation(
                            modifier = Modifier
                                .height(32.dp)
                                .width(128.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }
                } else {
                    state.tagsList
                        .filter { item ->
                            (item.nsfw && !state.isSafeSearchEnabled) || !item.nsfw
                        }
                        .forEach { item ->
                            Button(
                                modifier = Modifier
                                    .padding(2.dp),
                                shape = RoundedCornerShape(4.dp),
                                onClick = { onTagClicked(item) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (item.selected) {
                                        ButtonDefaults.buttonColors().containerColor
                                    } else {
                                        ButtonDefaults.buttonColors().disabledContainerColor
                                    },
                                    contentColor = if (item.selected) {
                                        ButtonDefaults.buttonColors().contentColor
                                    } else {
                                        ButtonDefaults.buttonColors().disabledContentColor
                                    }
                                )
                            ) {
                                Text(text = item.tag)
                            }
                        }
                }
            }
            Button(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                onClick = onApplyFilterClicked,
            ) {
                Text(text = stringResProvider.getString(R.string.apply))
            }
        }
    }
}