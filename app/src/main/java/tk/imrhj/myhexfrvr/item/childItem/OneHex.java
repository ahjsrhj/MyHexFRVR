package tk.imrhj.myhexfrvr.item.childItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import tk.imrhj.myhexfrvr.Utils;
import tk.imrhj.myhexfrvr.item.BaseHex;
import tk.imrhj.myhexfrvr.item.Hex;

/**
 * Created by rhj on 15/11/5.
 * 单个方块的情况
 */
public class OneHex extends BaseHex {
    private Hex hex;

    /**
     * 默认构造函数，设置颜色，实例化hex
     */
    public OneHex() {
        color = Utils.COLOR_ONE;
        uncolor = Utils.COLOR_ONE_UNLIGHT;
        hex = new Hex(Utils.SCREEN_WIDTH / 2, Utils.SCREEN_HEIGHT * 0.8f, color);
    }

    /**
     * 绘制其本身
     * @param canvas
     * @param paint
     */
    @Override
    public void drewSelf(Canvas canvas, Paint paint) {
        paint.setColor(color);
        hex.drawSelf(canvas, paint);
    }

    /**
     * 设置位置，方便在onTouch中调用
     * @param posX
     * @param posY
     */

    @Override
    public void setPosXY(float posX, float posY) {
        super.setPosXY(posX, posY);
        hex.setPosX(posX);
        hex.setPosY(posY);
    }

    /**
     * 此方法用来获取当前待选块的各个点坐标，当做数组传递过去
     *
     * @return
     */
    @Override
    public PointF[] getPositions() {
        PointF[] pointF = new PointF[1];
        pointF[0] = new PointF(hex.getPosX(), hex.getPosY());
        return pointF;
    }

    /**
     * 用来返回当前待选块的暗淡色.需要在每个子类中重写该方法。
     *
     * @return
     */
    @Override
    public int getUnlightColor() {
        return Utils.COLOR_ONE_UNLIGHT;
    }


    /**
     * 获取目前待选块的size，需在one和BaseHexF中重写该方法
     *
     * @return
     */
    @Override
    public int getSize() {
        return 1;
    }
}
