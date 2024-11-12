@file:OptIn(ExperimentalFoundationApi::class)

package org.matthias.fetchtakehome.screen.hiring

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.matthias.fetchtakehome.model.Hire
import org.matthias.fetchtakehome.ui.composables.hiring.HireItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HireList(groupedHires: Map<Int, List<Hire>>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        groupedHires.forEach { (listId, hireList) ->
            stickyHeader {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    HiringHeader(
                        header = "List ID: $listId",
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .padding(12.dp)
                    )
                }
            }
            itemsIndexed(hireList, key = { _, hire -> hire.id }) { idx, hire ->
                HireItem(
                    hire.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorFromIndex(idx))
                        .clip(RoundedCornerShape(percent = 25))
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

private fun colorFromIndex(index: Int) = when (index % 3) {
    0 -> Color.White
    1 -> Color.LightGray
    else -> Color.Transparent
}

@Preview(showBackground = true)
@Composable
fun HireListPreview() {
    val group = listOf(
        Hire(1, 1, "A name"),
        Hire(2, 2, "Another name"),
        Hire(3, 1, "A third name"),
        Hire(5, 2, "Cat 2"),
        Hire(6, 2, "Another in cat 2"),
        Hire(4, 3, "Last name")
    ).sortedWith(compareBy({ it.listId }, { it.name })).groupBy { it.listId }
    HireList(groupedHires = group)
}