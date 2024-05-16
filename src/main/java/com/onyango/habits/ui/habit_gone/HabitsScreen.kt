package com.onyango.habits.ui.habit_gone

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.onyango.habits.R
import com.onyango.habits.data.HabitRepository
import com.onyango.habits.ui.HabitItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun HabitsScreen(
    modifier: Modifier = Modifier,
    habitRepository: HabitRepository,
    navController: NavController,
    viewModel: HabitViewModel = viewModel(
        factory = HabitViewModelFactory(habitRepository)
    ),
) {

    val bottomSheetState =
        rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            AddHabitBottomSheet(viewModel, closeBottomSheetCallback = {
                scope.launch {
                    bottomSheetState.collapse()
                }
            })
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Habits",
                    style = TextStyle(fontSize = 24.sp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add habit",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            scope.launch {
                                if (bottomSheetState.isCollapsed) {
                                    bottomSheetState.expand()
                                } else {
                                    bottomSheetState.collapse()
                                }
                            }
                        }
                )

                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "add habit",
                    tint = Color.DarkGray
                )
            }

            Divider(Modifier.padding(vertical = 16.dp))

            val habits by viewModel.habitsData.observeAsState()
            LazyColumn {
                items(items = habits ?: arrayListOf()) { habit ->
                    HabitItem(habit = habit)
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun HabitsScreenPreview() {
    HabitsScreen(
        modifier = Modifier,
        viewModel = HabitViewModel(HabitRepository())
    )
}*/
