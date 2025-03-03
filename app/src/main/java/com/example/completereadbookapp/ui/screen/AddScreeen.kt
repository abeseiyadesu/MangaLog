package com.example.completereadbookapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.completereadbookapp.data.Comic
import com.example.completereadbookapp.data.ComicViewModel

@Composable
fun AddScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    comicViewModel: ComicViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            AddScreenTopBar(onBackClick)
        },
        content = { paddingValues ->
            AddScreenLayout(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                comicViewModel = comicViewModel
            )
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenTopBar(
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text("Simple Todo")
        },// top bar 色を指定する
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF25beb1), // 背景色を指定
            titleContentColor = Color.White, // タイトルの色を指定
            navigationIconContentColor = Color.White, // ナビゲーションアイコンの色
            actionIconContentColor = Color.White // アクションアイコンの色
        ),
        // 左上 戻るボタン
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    // ホーム画面へ戻る
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun AddScreenLayout(
    modifier: Modifier,
    navController: NavController,
    comicViewModel: ComicViewModel = viewModel()
) {
    var inputValue by remember { mutableStateOf("") }
    var inputDetail by remember { mutableStateOf("") }

    var errorState by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                errorState = inputValue.isBlank()
            },
            label = { Text("タイトルを入力") },
            isError = errorState,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )

        // コメント欄
        OutlinedTextField(
            value = inputDetail,
            onValueChange = {
                inputDetail = it
                errorState = inputDetail.isBlank()
            },
            label = { Text("コメントを入力（任意）") },
            isError = errorState,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(100.dp),
        )

        Button(
            onClick = {
                if (inputValue.isNotBlank()) {
                    // タスクを作成
                    val comic = Comic(
                        id = 0,
                        title = inputValue,
                        detail = inputDetail
                    )
                    // ここに入れる
                    // データは永続化される
                    comicViewModel.insertComic(comic)
                    inputValue = ""

                    // ホーム画面へ
                    navController.navigate("home")
                } else {
                    errorState = true
                }
            },

            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()

        ) {
            Text(text = "読破")
        }
    }
}

@Preview
@Composable
fun AddScreenLayoutPreview() {
    val navController = rememberNavController()
    val paddingValues = PaddingValues(16.dp)

    AddScreenLayout(
        modifier = Modifier.padding(paddingValues),

        navController = navController,
    )
}