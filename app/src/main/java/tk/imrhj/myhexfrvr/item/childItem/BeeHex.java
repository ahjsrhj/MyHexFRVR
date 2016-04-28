package tk.imrhj.myhexfrvr.item.childItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import tk.imrhj.myhexfrvr.item.BaseHexF;
import tk.imrhj.myhexfrvr.item.Hex;

import static tk.imrhj.myhexfrvr.Utils.*;

/**
 * Created by rhj on 15/11/18.
 * 形状有这三种，具体那种在实例化的时候随机决定
 * 1 2 3
 *  1
 * 2 3
 *  4
 *
 *  1 2
 *   3 4
 *
 *    1 2
 *   3 4
 */
public class BeeHex extends BaseHexF {


    /**
     * 默认构造函数，首先初始化数组，随机确定生成哪个形状的，根据形状确定位置
     */
    public BeeHex() {
        super();
        type = (int) (Math.random() * 1000 % 3);
        color = COLOR_BEE;
        uncolor = COLOR_BEE_UNLIGHT;
//        posX = SCREEN_WIDTH / 4;
        posY = SCREEN_HEIGHT * 0.8f;
        hex = new Hex[4];

        for (int i = 0; i < 4; i++) {
            hex[i] = new Hex(0, 0, color);
            setPos(i);
        }

    }

    /**
     * 设置方块对应位置，默认方块为4号方块，根据传入id以及type类型决定相对位置
     * @param i
     */
    private void setPos(int i) {
        switch (type) {
            case 0:
                switch (i) {
                    case 0:
                        hex[i].setPosX(posX);
                        hex[i].setPosY(posY
                                - HEX_SIDE_LENGTH
                                - HEX_HEIGHT
                                - HEX_SPACE * 2);
                        break;
                    case 1:
                        hex[i].setPosX(posX - HEX_SPACE / 2 - HEX_WIDTH / 2);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 2:
                        hex[i].setPosX(posX + HEX_SPACE / 2 + HEX_WIDTH / 2);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 3:
                        hex[i].setPosX(posX);
                        hex[i].setPosY(posY);
                        break;
                }
                break;
            case 1:
                switch (i) {
                    case 0:
                        hex[i].setPosX(posX - HEX_SPACE * 1.5f  - HEX_WIDTH * 1.5f);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 1:
                        hex[i].setPosX(posX - HEX_SPACE / 2 - HEX_WIDTH / 2);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 2:
                        hex[i].setPosX(posX - HEX_SPACE - HEX_WIDTH);
                        hex[i].setPosY(posY);

                        break;
                    case 3:
                        hex[i].setPosX(posX);
                        hex[i].setPosY(posY);
                        break;
                }
                break;
            case 2:
                switch (i) {
                    case 0:
                        hex[i].setPosX(posX - HEX_SPACE / 2 - HEX_WIDTH / 2);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 1:
                        hex[i].setPosX(posX + HEX_SPACE / 2 + HEX_WIDTH / 2);
                        hex[i].setPosY(posY - HEX_SPACE - 1.5f * HEX_SIDE_LENGTH);
                        break;
                    case 2:
                        hex[i].setPosX(posX - HEX_SPACE - HEX_WIDTH);
                        hex[i].setPosY(posY);
                        break;
                    case 3:
                        hex[i].setPosX(posX);
                        hex[i].setPosY(posY);
                        break;
                }
                break;
        }
    }


    @Override
    public void drewSelf(Canvas canvas, Paint paint) {
        super.drewSelf(canvas, paint);
    }


    @Override
    public void setPosXY(float posX, float posY) {
        super.setPosXY(posX, posY);
        for (int i = 0; i < 4; i++) {
            setPos(i);
        }
    }

    @Override
    public boolean isOnTouch() {
        return super.isOnTouch();
    }

    @Override
    public void setOnTouch(boolean onTouch) {
        super.setOnTouch(onTouch);
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
        return COLOR_BEE_UNLIGHT;
    }
}
