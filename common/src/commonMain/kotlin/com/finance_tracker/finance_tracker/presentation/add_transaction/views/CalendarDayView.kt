package com.finance_tracker.finance_tracker.presentation.add_transaction.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.finance_tracker.finance_tracker.core.common.stringResource
import com.finance_tracker.finance_tracker.core.theme.CoinTheme
import com.finance_tracker.finance_tracker.core.ui.CalendarDialog
import com.finance_tracker.finance_tracker.core.ui.CalendarDialogController
import com.finance_tracker.finance_tracker.core.ui.StubCalendarDialogController
import com.finance_tracker.finance_tracker.core.ui.rememberVectorPainter
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun CalendarDayView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        var calendarDialogController: CalendarDialogController by remember {
            mutableStateOf(StubCalendarDialogController)
        }
        var date by remember { mutableStateOf(LocalDate.now()) }
        CalendarDialog(
            onControllerCreate = {
                calendarDialogController = it
            },
            onDateChangeListener = { date = it }
        )

        Row(
            modifier = Modifier
                .background(CoinTheme.color.background)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { calendarDialogController.show() }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(
                LocalContentColor provides LocalContentColor.current.copy(alpha = 0.8f)
            ) {
                Icon(
                    painter = rememberVectorPainter("ic_calendar"),
                    contentDescription = null,
                    tint = LocalContentColor.current
                )

                val formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM"))
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = when {
                        date.isToday() -> "${stringResource("add_transaction_today")}, $formattedDate"
                        date.isYesterday() -> "${stringResource("add_transaction_yesterday")}, $formattedDate"
                        else -> formattedDate
                    },
                    style = CoinTheme.typography.subtitle2,
                    color = LocalContentColor.current
                )
            }
        }
    }
}

private fun LocalDate.isToday(): Boolean {
    val todayDate = LocalDate.now()
    return todayDate.dayOfYear == dayOfYear && todayDate.year == year
}

private fun LocalDate.isYesterday(): Boolean {
    val currentCalendar = asCalendar()
    val yesterdayCalendar = Calendar.getInstance().apply {
        add(Calendar.DATE, -1)
    }
    return yesterdayCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
            yesterdayCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)
}

private fun LocalDate.asCalendar(): Calendar {
    return Calendar.getInstance().apply {
        time = Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())
    }
}