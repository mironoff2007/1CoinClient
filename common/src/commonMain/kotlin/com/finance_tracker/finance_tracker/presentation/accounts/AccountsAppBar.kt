package com.finance_tracker.finance_tracker.presentation.accounts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.finance_tracker.finance_tracker.core.common.stringResource
import com.finance_tracker.finance_tracker.core.navigation.main.MainNavigationTree
import com.finance_tracker.finance_tracker.core.theme.CoinTheme
import com.finance_tracker.finance_tracker.core.ui.AppBarIcon
import com.finance_tracker.finance_tracker.core.ui.CoinTopAppBar
import com.finance_tracker.finance_tracker.core.ui.rememberVectorPainter
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AccountsAppBar(
    modifier: Modifier = Modifier
) {
    val navController = LocalRootController.current.findRootController()
    CoinTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource("accounts_screen_header"),
                style = CoinTheme.typography.h4
            )
        },
        actions = {
            AppBarIcon(
                painter = rememberVectorPainter("ic_plus"),
                tint = CoinTheme.color.primary,
                onClick = {
                    navController.push(MainNavigationTree.AddAccount.name)
                }
            )
        }
    )
}