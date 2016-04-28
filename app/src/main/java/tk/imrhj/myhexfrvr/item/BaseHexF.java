package tk.imrhj.myhexfrvr.item;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by rhj on 15/11/19.
 */
public class BaseHexF extends BaseHex {
    protected Hex[] hex;
    protected int type;

    /**
     * 默认构造函数 进行一系列的初始化操作，需要子类去复写，比如设置颜色，初始化hex，设置type
     */
    protected BaseHexF() {
        super();

    }

    /**
     * 绘制自身，用此函数绘制方块，在绘图线程中让其调用该方法。
     * @param canvas
     * @param paint
     */
    @Override
    public void drewSelf(Canvas canvas, Paint paint) {

        paint.setColor(color);

        for (Hex h : hex) {
            if (onTouch) {
                h.drawSelfSmall(canvas, paint);

            } else {
                h.drawSelf(canvas, paint);
            }
        }

    }

    /**
     * 设置xy坐标位置，一般需要使用循环处理多个点坐标，须在子类中实现
     * @param posX
     * @param posY
     */
    @Override
    public void setPosXY(float posX, float posY) {
        super.setPosXY(posX, posY);
    }

    /**
     * 此方法用来获取当前待选块的各个点坐标，当做数组传递过去
     *
     * @return
     */
    @Override
    public PointF[] getPositions() {
        PointF[] pointFs = new PointF[4];
        for (int i = 0; i < 4; i++) {
            pointFs[i] = new PointF(hex[i].getPosX(), hex[i].getPosY());
        }
        return pointFs;
    }

    /**
     * 用来返回当前待选块的暗淡色.需要在每个子类中重写该方法。
     *
     * @return
     */
    @Override
    public int getUnlightColor() {
        return super.getUnlightColor();
    }

    /**
     * 获取目前待选块的size，需在one和BaseHexF中重写该方法
     *
     * @return
     */
    @Override
    public int getSize() {
        return 4;
    }
}
