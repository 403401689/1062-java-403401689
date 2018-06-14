## 期末project之第一次進度報告

### A. 想做的題目
撞球小遊戲

### B. 組員及分工說明
1.  403410946 李介禾 撞球碰撞、撞球製作 
2.  403411902 葉柏吟 撞球碰撞、撞球桿製作
3.  403411183 藍渝雯 分數顯示、球桌製作 
4.  403401689 蔡冠廷 拉桿動作、聲音製作 

### C. 針對題目，請列出希望完成的項目

+ 藉由這學期學的java製作撞球小遊戲，來讓我們更靈活應用已學的知識，並對所遇到的困進行解決與學習。<br>
+ 針對著個題目希望達成的目標：
    + 1. 撞球桌介面設計 
    + 2. 符合撞球碰撞運動的滾動
    + 3. 顯示目前進球數 

### D. 程式列表說明
+ 主要分成5個Class
    + 1. 主程式 
    + 2. 球桿 
    + 3. 球洞 
    + 4. 撞球 
    + 5. 分數 

----------------------------------------------------------------

## 期末project之第二次進度報告

### A. PROJECT LIST
+ Start_page
    + Start.java
+ Game_page
    + Billiard.java
    + CueStick
    + Pocket.java
    + Table.java
    + Task.java
    + Ball.java
+ Score_page
    + BestScore_list.java
    + End_score.java
+ Connectmysql
    + Get_score.java
    + UpdatePlayer_NameTime.java
    + UpdatePlayer_score.java

### B. 遊戲畫面

![](https://i.imgur.com/5PAIKZz.jpg)

-----------------------------------------------------------------------
1. 在遊戲開始畫面中，輸入玩家名字並按下START按鈕進到遊戲主畫面，同時資料庫會記錄玩家的姓名及遊戲開始時間
![](https://i.imgur.com/UB4ZxM4.jpg)
![](https://i.imgur.com/5pP8Wce.jpg)
-----------------------------------------------------------------------
2. 按下EXIT按鈕離開遊戲
![](https://i.imgur.com/GHqPIHO.jpg)
-----------------------------------------------------------------------
3. 按下SCORE按鈕進入分數排行畫面
4. 在此畫面中，按下BACK按鈕返回遊戲起始畫面，按下EXIT離開遊戲
![](https://i.imgur.com/Xj4oJr3.jpg)
-----------------------------------------------------------------------
5. 完成所有進球後顯示最後分數
6. 按鈕與上述對應按鈕功能相同
![](https://i.imgur.com/VtoV9gK.jpg)

### C. 尚未完成部分

+ 遊戲主體尚未完成
    +  球的碰撞
    +  入袋判斷
    +  遊戲暫停與繼續
    
+ 第三次上機考尚未開始製作

----------------------------------------------------------------

## 期末project報告

+ 程式執行：
    + 將src、libs、resources匯入
    + 將g14.sql匯入資料庫，資料庫名稱為g14，密碼為0000
    + 執行src中Start_page裡的Start.java便可執行

+ jar檔：
    + jar檔的音效會沒有出現，但從Start.java執行的會有

+ Project 編寫：
    + Game_page不是自行編寫
    + Game_page裡的球繪圖為重新編寫過
    + 資料夾ball_test的Part1為球桿拉撞擊球編寫
    + 資料夾ball_test的Part2為球碰撞編寫

