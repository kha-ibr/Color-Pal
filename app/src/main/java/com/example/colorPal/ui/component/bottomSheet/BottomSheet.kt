package com.example.colorPal.ui.component.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    items: List<BottomSheetModel>,
    onItemClick: (Int, BottomSheetModel?) -> Unit,
    onDismissSheet: () -> Unit,
) {
    val padding = 16.dp
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        dragHandle = null,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding, top = padding, end = padding)
            ) {
                items.forEachIndexed { index, item ->
                    BottomSheetItem(item = item, index, onItemClick = onItemClick)
                }
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
            }
        },
        onDismissRequest = onDismissSheet
    )
}

@Composable
fun BottomSheetItem(
    item: BottomSheetModel,
    index: Int,
    onItemClick: (Int, BottomSheetModel?) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                onItemClick(index, item)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (item.icon != null) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                )
            }
            Text(text = item.text)
        }

        if (item.secondaryIcon != null) {
            Icon(
                imageVector = item.secondaryIcon,
                contentDescription = item.contentDescription
            )
        }
    }
}
