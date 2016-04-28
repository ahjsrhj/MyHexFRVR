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
 *     0 5
 *    1   4
 *     2 3
 *
 0    o   o
 *     o o
 *
 1       o
 *        o
 *     o o
 *
 2     o o
 *        o
 *       o
 *
 3     o o
 *    o   o
 *
 4     o o
 *    o
 *     o
 *
 5     o
 *    o
 *     o o
 *
 */
public class ArchHex extends BaseHexF {
    /**
     * 默认构造函数 进行一系列的初始化操作，需要子类去复写，比如设置颜色，初始化hex，设置type
     */
    public ArchHex() {
        super();
        type = (int) (Math.random() * 1000) % 6;
        color = COLOR_ARCH;
        uncolor = COLOR_ARCH_UNLIGHT;
        posX = SCREEN_WIDTH / 2;
        posY = SCREEN_HEIGHT * 0.8f;
        hex = new Hex[4];

        for (int i = 0; i < 4; i++) {
            hex[i] = new Hex(0, 0, color);
//            setPos(i);
        }
        for (int i = 0; i < 4; i++) {
            setPos(i);
        }

    }


    /**
     * type共有6种，需要相应处理
     * 以中心点为posx y ,以左上角为0，顺时针旋转一周，构建6个方法，在此方法中调用相应的方法
     * 如下
     *     0 5
     *    1   4
     *     2 3
     * 之后在此方法中使用switch选择结构来判断
     * @param i
     */
    private void setPos(int i) {
        switch (type) {
            case 0:         //1 2 3 4
                set1Pos(0);
                set2Pos(1);
                set3Pos(2);
                set4Pos(3);
                break;
            case 1:         //2 3 4 5
                set2Pos(0);
                set3Pos(1);
                set4Pos(2);
                set5Pos(3);
                break;
            case 2:         //3 4 5 0
                set3Pos(0);
                set4Pos(1);
                set5Pos(2);
                set0Pos(3);
                break;
            case 3:         //4 5 0 1
                set4Pos(0);
                set5Pos(1);
                set0Pos(2);
                set1Pos(3);
                break;
            case 4:         //5 0 1 2
                set5Pos(0);
                set0Pos(1);
                set1Pos(2);
                set2Pos(3);
                break;
            case 5:         //0 1 2 3
                set0Pos(0);
                set1Pos(1);
                set2Pos(2);
                set3Pos(3);
                break;
        }

    }

    /**
     * 这个方法群用来设置对应块的位置，相对坐标为中间那个块。
     * @param i
     */

    private void set5Pos(int i) {
        hex[i].setPosX(posX + (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY - (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
    }

    private void set4Pos(int i) {
        hex[i].setPosX(posX + (HEX_WIDTH + HEX_SPACE));
        hex[i].setPosY(posY);
    }

    private void set3Pos(int i) {
        hex[i].setPosX(posX + (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY + (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
    }

    private void set2Pos(int i) {
        hex[i].setPosX(posX - (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY + (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
    }

    private void set1Pos(int i) {
        hex[i].setPosX(posX - (HEX_WIDTH + HEX_SPACE));
        hex[i].setPosY(posY);
    }

    private void set0Pos(int i) {
        hex[i].setPosX(posX - (HEX_WIDTH + HEX_SPACE) / 2f);
        hex[i].setPosY(posY - (HEX_SIDE_LENGTH * 1.5f + HEX_SPACE));
    }

    /**
     * 绘制自身，用此函数绘制方块，在绘图线程中让其调用该方法。
     *
     * @param canvas
     * @param paint
     */
    @Override
    public void drewSelf(Canvas canvas, Paint paint) {
        super.drewSelf(canvas, paint);
    }

    /**
     * 设置xy坐标位置，一般需要使用循环处理多个点坐标，须在子类中实现
     *
     * @param posX
     * @param posY
     */
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
        return COLOR_ARCH_UNLIGHT;
    }
}
