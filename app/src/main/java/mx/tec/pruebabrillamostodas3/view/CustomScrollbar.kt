package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun CustomScrollbar(
    state: LazyListState,
    modifier: Modifier = Modifier
) {
    val visibleItemsInfo = state.layoutInfo.visibleItemsInfo
    if (visibleItemsInfo.isNotEmpty()) {
        val firstVisibleItemIndex = visibleItemsInfo.first().index
        val lastVisibleItemIndex = visibleItemsInfo.last().index
        val totalItemsCount = state.layoutInfo.totalItemsCount

        val scrollbarHeight = LocalConfiguration.current.screenHeightDp *
                (lastVisibleItemIndex - firstVisibleItemIndex + 1) / totalItemsCount

        val scrollbarOffsetY = LocalConfiguration.current.screenHeightDp *
                firstVisibleItemIndex / totalItemsCount

        Box(
            modifier = modifier
                .fillMaxHeight()
                .width(8.dp)
                .padding(end = 16.dp)
                .graphicsLayer {
                    translationY = scrollbarOffsetY.toFloat()
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(scrollbarHeight.dp)
                    .background(Color.Black)
            )
        }
    }
}