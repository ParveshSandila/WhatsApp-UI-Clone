package com.example.whatsappui

import android.R
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsappui.ui.screens.CallsScreen
import com.example.whatsappui.ui.screens.ChatsScreen
import com.example.whatsappui.ui.screens.StatusScreen
import com.example.whatsappui.ui.theme.WhatsAppUITheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppUITheme {
                // A surface container using the 'background' color from the theme

                val pagerState = rememberPagerState(pageCount = 3)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colors.primary,
                        )
                ) {
                    Header()
                    TabsContainer(pagerState = pagerState)
                    TabsContent(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.background
                            ),
                        pagerState = pagerState
                    )
                }
            }
        }
    }
}

@Composable
fun Header(
    modifier : Modifier = Modifier
){
    Box(
       modifier = modifier
           .fillMaxWidth()
           .padding(
               horizontal = 15.dp,
               vertical = 15.dp
           )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.whatsapp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContainer(
    pagerState: PagerState
){
    val scope = rememberCoroutineScope()

    TabRow(
        modifier= Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        contentColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.White
            )
        }
    ){
        stringArrayResource(id = R.array.tabs).forEachIndexed { index, label ->
           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .clickable {
                       scope.launch {
                           pagerState.animateScrollToPage(index)
                       }
                   }
                   .padding(
                       vertical = 10.dp,
                       horizontal = 5.dp
                   ),
               contentAlignment = Alignment.Center
           ) {
               Text(
                   text = label
               )
           }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    modifier: Modifier,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ChatsScreen(modifier)
            1 -> StatusScreen(modifier)
            2 -> CallsScreen(modifier)
        }
    }
}


