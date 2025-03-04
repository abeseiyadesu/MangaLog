package com.example.completereadbookapp.ui.Home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.completereadbookapp.data.ComicViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.completereadbookapp.data.Comic
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults

@Composable
fun HomeScreen(
    navController: NavController,
    comicViewModel: ComicViewModel = viewModel()
) {
    // データベース　にある　すべての漫画　を　呼び出す
    val comics by comicViewModel.allComics.observeAsState(initial = emptyList())


    Scaffold(
        // トップバー
        topBar = {
            HomeScreenTopBar()
        },
        // 漫画追加ボタン
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor =Color(0xFF25beb1),
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        // メインコンテンツ
        content = { paddingValues ->
            HomeScreenLayout(
                comics = comics,
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text("読破")
        },
        // top bar 色を指定する
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF25beb1), // 背景色を指定
            titleContentColor = Color.White, // タイトルの色を指定
            navigationIconContentColor = Color.White, // ナビゲーションアイコンの色
            actionIconContentColor = Color.White // アクションアイコンの色
        )
    )
}

@Composable
fun HomeScreenLayout(
    comics: List<Comic>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    LazyColumn(modifier = modifier) {
        items(comics) { comic ->
            HorizontalDivider(thickness = 1.dp)
            TextButton(
                onClick = {
                    navController.navigate("detail/${comic.id}")
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = comic.title,
                    style = TextStyle(fontSize = 30.sp),
                    color = Color(0xFF2b2d30)
                )
            }
            HorizontalDivider(thickness = 1.dp)

        }
    }
}


@Preview
@Composable
fun HomeScreenLayoutPreview() {
    val navController = rememberNavController()
    val paddingValues = PaddingValues(16.dp)
    // 仮のデータを用意
    val comics = listOf(
        Comic(id = 1, title = "Comic 1", ""),
        Comic(id = 2, title = "Comic 2", ""),
        Comic(id = 3, title = "Comic 3", "")
    )

    HomeScreenLayout(
        comics = comics,
        modifier = Modifier.padding(paddingValues),
        navController = navController
    )
}