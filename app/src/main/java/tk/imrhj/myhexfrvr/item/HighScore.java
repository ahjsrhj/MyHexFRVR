package tk.imrhj.myhexfrvr.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;

import tk.imrhj.myhexfrvr.Utils;

/**
 * Created by rhj on 15/11/28.
 */
public class HighScore {
    private int size;           //字体大小
    private int highScore;          //分数
    private float posX;         //x坐标
    private float posY;         //y坐标
    private Paint paint;        //画笔
    SharedPreferences.Editor editor;
    SharedPreferences preferences;

    /**
     * 构造函数
     */
    public HighScore(Context context) {
        paint = new Paint();
        size = Utils.SCREEN_WIDTH / 20;
        posX = Utils.SCREEN_WIDTH;
        posY = Utils.SCREEN_HEIGHT * .03f;
        highScore = 0;
        paint.setTextSize(size);
        paint.setColor(Color.GRAY);

        initHighScore(context);
    }


    /**
     * 绘制其本身,需要先计算文本框长度,保证绘制在边缘部分
     * @param canvas
     */
    public void drawSelf(Canvas canvas) {
        String scoreStr = "记录 : " + String.valueOf(highScore);
        float textWidth = paint.measureText(scoreStr);
        canvas.drawText(scoreStr, posX - textWidth, posY, paint);
    }

    /**
     * 更新最高分，需要写入文件
     * @param highScore
     */
    public void updateHighScore(int highScore) {
        if (highScore > this.highScore) {
            this.highScore = highScore;
            editor.putInt("highScore", highScore);
            editor.commit();
        }
    }

    /**
     * 初始化最高分，需要读取文件
     */
    @SuppressLint("CommitPrefEdits")
    public void initHighScore(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        highScore = preferences.getInt("highScore", 0);
        editor = preferences.edit();
    }

    /**
     * 获得当前最高分
     * @return
     */
    public int getHighScore() {
        return highScore;
    }



}
