# MangaLog

## アプリ説明
MangaLogは読み終わった漫画を記録し、感想などを記録できるアプリです。  
 [「Compose を用いた Android アプリ開発の基礎」](https://developer.android.com/courses/android-basics-compose/course?hl=ja)のユニット６の学習内容を主に使用しました。(Room)
<br>  

## 使用言語・技術
kotlin / Android Studio / Jetpack Compose <br>
*** 他のライブラリ**
 - Room : 追加したタスクをローカルストレージに保存するため。
<br>  

## アプリを作った目的
 - 前作のToDoアプリを応用し、個人的にメモに記録していた読了記録や感想をアプリで管理するために作成しました。
<br>  

## 機能一覧
 - 右下の追加ボタンで 読破済み漫画 を追加
 - 編集画面で内容を編集
 - 読破漫画の一覧表示
 - 読破漫画を押すと編集可能
 - 削除ボタンを押すと
 - 無限スクロール機能
 - navControllerによる画面遷移
 - タスクをローカル保存（Roomを使用）
<br>  

## 工夫した点
### **画面遷移に詳細画面を追加**
詳細画面を実装し、必要なときだけ編集操作をできるようにすることで、ユーザー体験がよりよいものになると考えました。

### **コードをリファクタリング**
前作のコードをベースに、可動性や保守性を上げることを目指してコード全体を見直し、リファクタリングをしながら実装しました。(細かなところの関数化 削除時ダイアログ表示など)
ViewModelを活用することで、状態管理の明確化とコンポーネント間の依存性低減に努めました。<br>  


## 苦労した点
### **UI設計のシンプル**
Jetpack Composeを用いたレイアウト設計において、シンプルで直感的なUI実現のために、レイアウト構築やデザイン調整に工夫を重ねました。
<br>  

### **学んだこと**
 - コードリファクタリング
 - 

<br>  
ホーム画面

![MangaLog1](https://github.com/user-attachments/assets/c4f95a05-a371-476f-bdbe-c68b1801b11b)

詳細画面

![MangaLog2](https://github.com/user-attachments/assets/81afb4c8-55d2-4ffa-9f19-d0ae54ef0db8)

編集画面

![MangaLog3](https://github.com/user-attachments/assets/8bcc7415-7ef1-4e7f-8618-82d018247fbb)

