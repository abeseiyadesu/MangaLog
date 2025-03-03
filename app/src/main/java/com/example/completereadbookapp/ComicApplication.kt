package com.example.completereadbookapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.completereadbookapp.ui.screen.AddScreen
import com.example.completereadbookapp.ui.screen.EditScreen
import com.example.completereadbookapp.ui.Home.HomeScreen
import com.example.completereadbookapp.ui.screen.DetailScreen

@Composable
fun ComicApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // 画面遷移用コントローラー
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home") {
            // ホーム画面へ
            composable("home") {
                HomeScreen(
                    navController = navController
                )
            }

            // 漫画追加画面へ
            composable("add") {
                AddScreen(
                    navController = navController,
                    onBackClick = { navController.popBackStack() } // ラムダを期待するため、{}で渡す
                )
            }

            //　編集画面へ
            // どの漫画かを　特定　するため　Id　を渡す必要がある
            composable(
                route = "edit/{comicId}",
                // Idは　Int型のため  Int  に変換する必要がある
                arguments = listOf(
                    androidx.navigation.navArgument("comicId") {
                        type = androidx.navigation.NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val comicId = backStackEntry.arguments?.getInt("comicId")
                // nullチェック
                if (comicId != null) {
                    EditScreen(
                        navController = navController,
                        onBackClick = { navController.popBackStack() },
                        comicId = comicId
                    )
                }
            }


            composable(
                route = "detail/{comicId}",
                // Idは　Int型のため  Int  に変換する必要がある
                arguments = listOf(
                    androidx.navigation.navArgument("comicId") {
                        type = androidx.navigation.NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val comicId = backStackEntry.arguments?.getInt("comicId")
                // nullチェック
                if (comicId != null) {
                    // 詳細画面へ移動
                    DetailScreen(
                        navController = navController,
                        onBackClick = { navController.popBackStack() },
                        comicId = comicId
                    )
                }
            }
        }
    }
}