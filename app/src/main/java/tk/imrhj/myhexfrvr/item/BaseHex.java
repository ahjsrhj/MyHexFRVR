package tk.imrhj.myhexfrvr.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by rhj on 15/11/18.
 *
 * 之后出现的三个待选块的基类
 */
public class BaseHex {
    protected float posX;
    protected float posY;
    protected int color;
    protected int uncolor;
    protected boolean onTouch = false;
    protected int size;

    protected BaseHex() {}

    public void drewSelf(Canvas canvas, Paint paint) {

    }

    public void setPosXY(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isOnTouch() {
        return onTouch;
    }

    public void setOnTouch(boolean onTouch) {
        this.onTouch = onTouch;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    /**
     * 此方法用来获取当前待选块的各个点坐标，当做数组传递过去
     * @return
     */
     public PointF[] getPositions() {
         return null;
     }

    /**
     * 用来返回当前待选块的暗淡色.需要在每个子类中重写该方法。
     * @return
     */
    public int getUnlightColor() {
        return uncolor;
    }

    public int getColor() {
        return color;
    }

    /**
     * 获取目前待选块的size，需在one和BaseHexF中重写该方法
     * @return
     */
    public int getSize() {
        return 0;
    }
}
