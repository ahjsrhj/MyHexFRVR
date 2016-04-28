package tk.imrhj.myhexfrvr.item.childItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import tk.imrhj.myhexfrvr.Utils;
import tk.imrhj.myhexfrvr.item.BaseHexF;
import tk.imrhj.myhexfrvr.item.Hex;

import static tk.imrhj.myhexfrvr.Utils.*;

/**
 * Created by rhj on 15/11/18.
 * 形状也是有三种
 * 1
 *  2
 *   3
 *    4
 *
 *    1
 *   2
 *  3
 * 4
 *
 * 1 2 3 4
 */
public class BarHex extends BaseHexF {


    public BarHex() {
        super();
        type = (int) (Math.random() * 1000) % 3;
        color = COLOR_BAR;
        uncolor = COLOR_BAR_UNLIGHT;
        posX = SCREEN_WIDTH / 2;
        posY = SCREEN_HEIGHT * 0.8f;
        hex = new Hex[4];

        for (int i = 0; i < 4; i++) {
            hex[i] = new Hex(0, 0, color);
            setPos(i);
        }

    }

    /**
     * 设置第一个为posX,posY，其它点根据第三个点计算得到相对位置
     * @param i
     */
    private void setPos(int i) {
        switch (type) {
            case 0:
                hex[i].setPosX(posX + (i - 2) * (HEX_WIDTH + HEX_SPACE) / 2f);
                hex[i].setPosY(posY + (i - 2) * (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                break;
            case 1:
                hex[i].setPosX(posX - (i-2) * (HEX_WIDTH + HEX_SPACE) / 2f);
                hex[i].setPosY(posY + (i-2) * (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                break;
            case 2:
                hex[i].setPosX(posX + (i - 1) * (HEX_WIDTH + HEX_SPACE));
                hex[i].setPosY(posY);
                break;
        }
    }

    @Override
    public void drewSelf(Canvas canvas, Paint paint) {
        paint.setColor(color);
        super.drewSelf(canvas, paint);

    }

    @Override
    public void setPosXY(float posX, float posY) {
        super.setPosXY(posX, posY);

        for (int i = 0; i < 4; i++) {
            setPos(i);
        }
    }

    /**
     * 此方法用来获取当前待选块的各个点坐标，当做数组传递过去
     *
     * @return
     */
    @Override
    public PointF[] getPositions() {
        return super.getPositions();
    }

    /**
     * 用来返回当前待选块的暗淡色.需要在每个子类中重写该方法。
     *
     * @return
     */
    @Override
    public int getUnlightColor() {
        return COLOR_BAR_UNLIGHT;
    }
}
