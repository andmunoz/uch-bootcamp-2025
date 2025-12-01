package cl.uchile.postgrado.mobile.calendar.view

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cl.uchile.postgrado.mobile.calendar.model.database.HolidayDatabase
import cl.uchile.postgrado.mobile.calendar.view.layouts.ExpandedLayout
import cl.uchile.postgrado.mobile.calendar.view.tools.WindowSize
import cl.uchile.postgrado.mobile.calendar.view.tools.getWindowSizeClass
import cl.uchile.postgrado.mobile.calendar.viewmodel.CalendarViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("ViewModelConstructorInComposable")
@Composable
@Preview
fun App(database: HolidayDatabase) {
    val calendarVM = CalendarViewModel(database)
    MaterialTheme {
        BoxWithConstraints {
            val width = this.maxWidth
            when(getWindowSizeClass(width)) {
                WindowSize.COMPACT -> ExpandedLayout(1, 1, calendarVM)
                WindowSize.MEDIUM -> ExpandedLayout(4, 2, calendarVM)
                WindowSize.EXPANDED -> ExpandedLayout(6, 3, calendarVM)
/*              WindowSize.COMPACT -> CompactLayout()
                WindowSize.MEDIUM -> MediumLayout()
                WindowSize.EXPANDED -> ExpandedLayout() */
            }
        }
    }
}