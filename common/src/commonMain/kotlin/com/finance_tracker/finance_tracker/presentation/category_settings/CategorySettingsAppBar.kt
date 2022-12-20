package com.finance_tracker.finance_tracker.presentation.category_settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.finance_tracker.finance_tracker.core.common.stringResource
import com.finance_tracker.finance_tracker.core.navigation.main.MainNavigationTree
import com.finance_tracker.finance_tracker.core.theme.CoinTheme
import com.finance_tracker.finance_tracker.core.ui.AppBarIcon
import com.finance_tracker.finance_tracker.core.ui.CoinTopAppBar
import com.finance_tracker.finance_tracker.core.ui.rememberVectorPainter
import com.finance_tracker.finance_tracker.core.ui.tab_rows.TransactionTypeTab
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun CategorySettingsAppBar(
    selectedTransactionTypeTab: TransactionTypeTab
) {
    val rootController = LocalRootController.current

    CoinTopAppBar(
        navigationIcon = {
            AppBarIcon(
                painter = rememberVectorPainter("ic_arrow_back"),
                onClick = { rootController.findRootController().popBackStack() },
            )
        },
        title = {
            Text(
                text = stringResource("category_settings"),
                style = CoinTheme.typography.h4
            )
        },
        actions = {
            AppBarIcon(
                rememberVectorPainter("ic_plus"),
                onClick = {
                    rootController.findRootController().push(
                        MainNavigationTree.AddCategory.name,
                        params = selectedTransactionTypeTab
                    )
                },
                tint = CoinTheme.color.primary,
            )
        },
    )
}