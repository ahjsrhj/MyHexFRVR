package tk.imrhj.myhexfrvr.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

import tk.imrhj.myhexfrvr.Utils;

/**
 * Created by rhj on 15/11/1.
 */
public class Hex {
    public static final int EMPTY = 0;
    public static final int FULL = 1;
    public static final int UNLIGHT = -1;


    private int color;
    private int status;
    private int x;
    private int y;
    private float posX;
    private float posY;
    
    public Hex(int x, int y) {
        super();
        color = Utils.COLOR_DEFAULT;
        status = EMPTY;
        setXY(x, y);
    }

    public Hex(float posX, float posY, int color) {
        super();
        this.color = color;
        status = EMPTY;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
//        paint.setColor(color);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if (status == EMPTY) {
            color = Utils.COLOR_DEFAULT;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;

        PointF pointF = Utils.clacPos(x, y);
        posX = pointF.x;
        posY = pointF.y;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     *  绘制Hex自身
     *
     * @param canvas 使用传递进来的canvas对象进行绘制
     * @param paint 每次绘制之前都先改变其颜色为Hex.color
     */
    public void drawSelf(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawPath(Utils.mkHexFromPos(posX, posY), paint);
    }




    /**
     *  绘制一个稍微小点的自身,方便看到下面的格子
     *
     * @param canvas
     * @param paint
     */
    public void drawSelfSmall(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawPath(Utils.mkSmallHexFromPos(posX, posY), paint);
    }

    /**
     * 检测一个坐标是否在该点内。
     * @param pointF
     */
    public boolean checkPoint(PointF pointF) {
        float x = pointF.x;
        float y = pointF.y;
        if (    x > posX - Utils.HEX_SIDE_LENGTH / 1.2f &&
                x < posX + Utils.HEX_SIDE_LENGTH / 1.2f &&
                y > posY - Utils.HEX_SIDE_LENGTH / 1.2f &&
                y < posY + Utils.HEX_SIDE_LENGTH / 1.2f) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (status != FULL) {
            return true;
        }
        return false;
    }

}
