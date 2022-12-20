package com.finance_tracker.finance_tracker.presentation.add_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finance_tracker.finance_tracker.core.common.StoredViewModel
import com.finance_tracker.finance_tracker.core.common.stringResource
import com.finance_tracker.finance_tracker.core.common.view_models.watchViewActions
import com.finance_tracker.finance_tracker.core.ui.PrimaryButton
import com.finance_tracker.finance_tracker.domain.models.Account
import com.finance_tracker.finance_tracker.presentation.add_account.views.AccountColorTextField
import com.finance_tracker.finance_tracker.presentation.add_account.views.AccountNameTextField
import com.finance_tracker.finance_tracker.presentation.add_account.views.AccountTypeTextField
import com.finance_tracker.finance_tracker.presentation.add_account.views.AddAccountTopBar
import com.finance_tracker.finance_tracker.presentation.add_account.views.AmountTextField
import com.finance_tracker.finance_tracker.presentation.add_account.views.EditAccountActions
import org.koin.core.parameter.parametersOf

@Composable
fun AddAccountScreen(
    account: Account
) {
    StoredViewModel<AddAccountViewModel>(
        parameters = { parametersOf(account) }
    ) { viewModel ->

        val scaffoldState = rememberScaffoldState()
        viewModel.watchViewActions { action, baseLocalsStorage ->
            handleAction(
                action = action,
                baseLocalsStorage = baseLocalsStorage,
                scaffoldState = scaffoldState
            )
        }

        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Column {
                AddAccountTopBar(
                    topBarTextId = if (account == Account.EMPTY) {
                        "new_account_title"
                    } else {
                        "accounts_screen_top_bar"
                    }
                )

                AccountNameTextField(viewModel = viewModel)

                Row(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    AccountTypeTextField(viewModel = viewModel)
                    Spacer(modifier = Modifier.width(8.dp))
                    AccountColorTextField(viewModel = viewModel)
                }

                AmountTextField(viewModel = viewModel)

                val isAddButtonEnabled by viewModel.isAddButtonEnabled.collectAsState()
                if (account == Account.EMPTY) {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 24.dp,
                                start = 16.dp,
                                end = 16.dp
                            ),
                        text = stringResource("new_account_btn_add"),
                        onClick = viewModel::onAddAccountClick,
                        enable = isAddButtonEnabled
                    )
                } else {
                    EditAccountActions(
                        viewModel = viewModel,
                        account = account,
                        addEnable = isAddButtonEnabled
                    )
                }
            }
        }
    }
}