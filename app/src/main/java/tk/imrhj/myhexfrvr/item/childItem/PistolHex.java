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
 * Created by rhj on 15/11/21.
 *
 * 共八种形态 = =
 0    o
 *   o
 *  o o
 *
 1    o
 * o o
 *  o
 *
 2    o
 *   o o
 *  o
 *
 3  o o
 *   o
 *  o
 *
 4  o
 *   o o
 *    o
 *
 5  o
 *   o
 *  o o
 *
 6  o o
 *   o
 *    o
 *
 7  o
 * o o
 *    o
 *
 */
public class PistolHex extends BaseHexF {

    private static final String TAG = "PostolHex ";

    public PistolHex() {
        super();
        type = (int) (Math.random() * 1000 % 8);
        color = COLOR_PISTOL;
        uncolor = COLOR_PISTOL_UNLIGHT;
        posX = SCREEN_WIDTH / 2;
        posY = SCREEN_HEIGHT * 0.8f;
        hex = new Hex[4];

        for (int i = 0; i < 4; i++) {
            hex[i] = new Hex(0, 0, color);
            setPos(i);
        }
    }

    /**
     * 设三条中间那一点为posx y，其余点根据这个参考点计算得到相对位置
     * @param i
     */
    private void setPos(int i) {
        if (i != 3) {
            if (type < 4) {
                setRightHexPos(i);
            } else {
                setLeftHexPos(i);
            }
        } else { //i==3
            switch (type) {
                case 0:
                    hex[3].setPosX(posX + (HEX_WIDTH + HEX_SPACE) / 2f);
                    hex[3].setPosY(posY + (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                    break;
                case 1:
                case 7:
                    hex[3].setPosX(posX - HEX_WIDTH - HEX_SPACE);
                    hex[3].setPosY(posY);
                    break;
                case 2:
                case 4:
                    hex[3].setPosX(posX + HEX_WIDTH + HEX_SPACE);
                    hex[3].setPosY(posY);
                    break;
                case 3:
                    hex[3].setPosX(posX - (HEX_WIDTH + HEX_SPACE) / 2f);
                    hex[3].setPosY(posY - (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                    break;
                case 5:
                    hex[3].setPosX(posX - (HEX_WIDTH + HEX_SPACE) / 2f);
                    hex[3].setPosY(posY + (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                    break;
                case 6:
                    hex[3].setPosX(posX + (HEX_WIDTH + HEX_SPACE) / 2f);
                    hex[3].setPosY(posY - (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
                    break;
            }

        }


    }

    private void setLeftHexPos(int i) {
        hex[i].setPosX(posX + (i - 1) * (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY + (i - 1) * (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
    }

    private void setRightHexPos(int i) {
        hex[i].setPosX(posX - (i - 1) * (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY + (i - 1) * (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
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
        return COLOR_PISTOL_UNLIGHT;
    }
}











