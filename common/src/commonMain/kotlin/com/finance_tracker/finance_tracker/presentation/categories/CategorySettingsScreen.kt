package com.finance_tracker.finance_tracker.presentation.categories

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finance_tracker.finance_tracker.core.common.getViewModel
import com.finance_tracker.finance_tracker.core.common.statusBarsPadding
import com.finance_tracker.finance_tracker.core.theme.CoinTheme
import com.finance_tracker.finance_tracker.core.ui.CategoryCard
import com.finance_tracker.finance_tracker.core.ui.ExpenseIncomeTabs

@Composable
fun CategorySettingsScreen(
    viewModel: CategorySettingsScreenViewModel = getViewModel()
) {


    val categories by viewModel.categories.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CategorySettingsAppBar()
        
        ExpenseIncomeTabs(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 20.dp
                ),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .border(
                    width = 1.dp,
                    color = CoinTheme.color.dividers,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                ),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 36.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(categories) { category ->
                CategoryCard(data = category)
            }
        }
    }
}