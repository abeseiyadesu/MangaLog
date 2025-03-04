package com.example.completereadbookapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.completereadbookapp.data.ComicViewModel
import androidx.navigation.compose.rememberNavController
import com.example.completereadbookapp.R

@Composable
fun DetailScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    comicViewModel: ComicViewModel = viewModel(),
    comicId: Int
) {
    var detailScreenTitle by remember { mutableStateOf("") }
    var detailScreenComent by remember { mutableStateOf("") }

    val comic by comicViewModel.getComicById(comicId).observeAsState()

    // 初期値を設定
    LaunchedEffect(comic) {
        comic?.let {
            detailScreenTitle = it.title
            detailScreenComent = it.detail
        }
    }

    Scaffold(
        topBar = {
            DetailScreenTopBar(
                onBackClick = onBackClick,
                navController = navController,
                comicId = comicId
            )
        },
        content = { paddingValues ->
            DetailScreenLayout(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                comicId = comicId,
                detailScreenTitle = detailScreenTitle,
                detailScreenComent = detailScreenComent
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(
    onBackClick: () -> Unit,
    navController: NavController,
    comicId: Int
) {
    CenterAlignedTopAppBar(
        title = {
            Text("")
        }
        ,// top bar 色を指定する
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF25beb1), // 背景色を指定
            titleContentColor = Color.White, // タイトルの色を指定
            navigationIconContentColor = Color.White, // ナビゲーションアイコンの色
            actionIconContentColor = Color.White // アクションアイコンの色
        ),
        // ホーム画面へ移動
        // 左上 戻るボタン
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        // 編集ボタンを作成
        actions = {
            IconButton(
                onClick = {
                    // 編集画面へ
                    navController.navigate("edit/${comicId}")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_save_24),
                    contentDescription = "",
                )
            }
        }
    )
}

@Composable
fun DetailScreenLayout(
    modifier: Modifier,
    navController: NavController,
    comicId: Int,
    detailScreenTitle: String,
    detailScreenComent: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "タイトル",
            fontSize = 10.sp,
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider(thickness = 1.dp)

        // タイトル
        TextButton(
            onClick = {
                navController.navigate("edit/${comicId}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = detailScreenTitle,
                style = TextStyle(
                    fontSize = 30.sp
                ),
                color = Color(0xFF2b2d30)
            )
        }

        HorizontalDivider(thickness = 1.dp)
        Text(
            text = "コメント",
            fontSize = 10.sp,
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider(thickness = 1.dp)

        // 詳細
        TextButton(
            onClick = {
                navController.navigate("edit/${comicId}")
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = detailScreenComent,
                style = TextStyle(
                    fontSize = 30.sp
                ),
                color = Color(0xFF2b2d30)
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenTopBarPreview() {
    val comicIdDummy = 1
    DetailScreenTopBar(
        onBackClick = {},
        navController = rememberNavController(),
        comicId = comicIdDummy
    )
}