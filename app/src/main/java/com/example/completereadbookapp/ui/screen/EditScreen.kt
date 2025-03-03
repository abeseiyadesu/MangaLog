package com.example.completereadbookapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.completereadbookapp.data.Comic
import com.example.completereadbookapp.data.ComicViewModel

@Composable
fun EditScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    comicViewModel: ComicViewModel = viewModel(),
    comicId: Int
) {
    Scaffold(
        topBar = {
            EditScreenTopBar(onBackClick)
        },
        content = { paddingValues ->
            EditScreenLayout(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                comicViewModel = comicViewModel,
                comicId = comicId
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenTopBar(
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text("")
        },// top bar 色を指定する
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
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun EditScreenLayout(
    modifier: Modifier,
    navController: NavController,
    comicViewModel: ComicViewModel = viewModel(),
    comicId: Int
) {
    var inputValue by remember { mutableStateOf("") }
    var inputDetail by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val comic by comicViewModel.getComicById(comicId).observeAsState()

    // 初期値を設定
    LaunchedEffect(comic) {
        comic?.let {
            inputValue = it.title
            inputDetail = it.detail
        }
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                errorState = inputValue.isBlank()
            },
            label = { Text("タイトルを編集") },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(70.dp)
        )

        OutlinedTextField(
            value = inputDetail,
            onValueChange = {
                inputDetail = it
                errorState = inputDetail.isBlank()
            },
            label = { Text("コメントを編集") },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(150.dp)
        )

        // 編集内容の変更を 完了（確定）するボタン
        Button(
            onClick = {
                //　変更された要素をdatabaseに入れる処理を書く
                val updatedComic = Comic(
                    id = comicId,
                    title = inputValue,
                    detail = inputDetail
                )

                comicViewModel.updateComic(updatedComic)
                // ホーム画面へ
                navController.navigate("home")
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF25beb1),
                contentColor = Color.Black
            )
        ) {
            Text(text = "完了")
        }

        // 削除ボタン
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF25beb1),
                contentColor = Color.Black
            )
        ) {
            Text(text = "削除")
        }
    }

    //　削除時ダイアログを出す
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, //
            title = { Text(text = "削除の確認") },
            text = { Text(text = "本当に削除しますか？この操作は元に戻せません。") },
            confirmButton = {
                Button(
                    onClick = {
                        comicViewModel.deleteComic(comicId)

                        // ポップアップで説明がありホーム画面へ
                        navController.navigate("home")
                        showDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF25beb1),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "削除")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF25beb1),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "キャンセル")
                }
            }
        )
    }

}