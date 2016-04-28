package tk.imrhj.myhexfrvr.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import tk.imrhj.myhexfrvr.Utils;

/**
 * Created by rhj on 15/11/27.
 */
public class Score {
    private int size;           //字体大小
    private int score;          //分数
    private float posX;         //x坐标
    private float posY;         //y坐标
    private Paint paint;        //画笔

    /**
     * 构造函数
     */
    public Score() {
        paint = new Paint();
        size = Utils.SCREEN_WIDTH / 8;
        posX = Utils.SCREEN_WIDTH / 2;
        paint.setColor(Color.WHITE);
        posY = Utils.SCREEN_HEIGHT * .1f;
        score = 0;
        paint.setTextSize(size);
    }


    public void drawSelf(Canvas canvas) {
        String scoreStr = String.valueOf(score);
        float textWidth = paint.measureText(scoreStr);
        canvas.drawText(String.valueOf(score), posX - textWidth / 2, posY, paint);
    }

    public void putHexs() {
        score += 80;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
