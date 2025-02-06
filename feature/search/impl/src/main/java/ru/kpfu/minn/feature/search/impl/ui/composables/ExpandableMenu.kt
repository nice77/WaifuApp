package ru.kpfu.minn.feature.search.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.search.impl.R
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchState

@Composable
fun ExpandableMenu(
    state: SearchState,
    stringResProvider: StringResProvider,
    onSafeSearchChecked: (Boolean) -> Unit,
    onApplyFilterClicked: () -> Unit,
    onTagClicked: (TagUiModel) -> Unit,
    onSwitchClicked: (Boolean) -> Unit,
) {
    AnimatedVisibility(
        visible = state.isExpanded,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Switch(
                    modifier = Modifier.padding(4.dp),
                    checked = state.isImageSearch,
                    onCheckedChange = {
                        onSwitchClicked(it)
                    },
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResProvider.getString(R.string.images_search),
                )
            }
            TagsMenu(
                state = state,
                stringResProvider = stringResProvider,
                onSafeSearchChecked = onSafeSearchChecked,
                onApplyFilterClicked = onApplyFilterClicked,
                onTagClicked = onTagClicked,
            )
        }
    }
}