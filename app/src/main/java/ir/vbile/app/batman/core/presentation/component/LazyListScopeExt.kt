package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun LazyListScope.gridItems(
    count: Int,
    cells: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(Int) -> Unit,
) {
    gridItems(
        data = List(count) { it },
        columns = cells,
        horizontalArrangement = horizontalArrangement,
        itemContent = itemContent,
        modifier = modifier
    )
}

fun <T> LazyListScope.gridItems(
    data: List<T>,
    columns: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable() (BoxScope.(T) -> Unit),
    modifier: Modifier = Modifier,
) {
    val rows = if (data.count() == 0) 0 else 1 + (data.count() - 1) / columns
    items(rows) { rowIndex ->
        Row(horizontalArrangement = horizontalArrangement, modifier = modifier) {
            for (columnIndex in 0 until columns) {
                val itemIndex = rowIndex * columns + columnIndex
                if (itemIndex < data.count()) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent.invoke(this, data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}