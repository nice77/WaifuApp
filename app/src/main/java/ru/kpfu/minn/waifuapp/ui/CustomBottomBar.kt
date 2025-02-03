package ru.kpfu.minn.waifuapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.waifuapp.R

@Composable
fun CustomBottomBar(
    stringResProvider: StringResProvider,
    onDashboardClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onProfileClicked: () -> Unit,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomIconButton(
            icon = Icons.Filled.Dashboard,
            text = stringResProvider.getString(R.string.dashboard),
            onClick = onDashboardClicked,
        )
        CustomIconButton(
            icon = Icons.Filled.Search,
            text = stringResProvider.getString(R.string.search),
            onClick = onSearchClicked,
        )
        CustomIconButton(
            icon = Icons.Filled.Person,
            text = stringResProvider.getString(R.string.profile),
            onClick = onProfileClicked,
        )
    }
}
