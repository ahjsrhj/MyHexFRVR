package tk.imrhj.myhexfrvr;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * Created by rhj on 11/3/15.
 */
public class Utils {
    // 用到的一些颜色
    public static final int COLOR_ONE       = 0xFFCEF380;
    public static final int COLOR_BAR       = 0xFF79E095;
    public static final int COLOR_BEE       = 0xFF888CFE;
    public static final int COLOR_PISTOL    = 0xFFFE89B4;
    public static final int COLOR_ARCH      = 0xFFFEDC88;
    public static final int COLOR_DEFAULT   = 0xFF4D4D4B;
    public static final int COLOR_ONE_UNLIGHT       = 0xFF8C9C5A;
    public static final int COLOR_BAR_UNLIGHT       = 0xFF4e9067;
    public static final int COLOR_BEE_UNLIGHT       = 0xFF5967a4;
    public static final int COLOR_PISTOL_UNLIGHT    = 0xFFa86376;
    public static final int COLOR_ARCH_UNLIGHT      = 0xFFa98f5e;

    public static int SCREEN_HEIGHT;       //屏幕高度
    public static int SCREEN_WIDTH;        //屏幕宽度
    public static float HEX_WIDTH;         //六边形宽度
    public static float HEX_HEIGHT;        //六边形高度
    public static float HEX_SIDE_LENGTH;   //六边形边长
    public static float HEX_SPACE;         //间距
    public static float SIGN_THREE = 1.732050807f;        //根号3的值

    public static void initUtils(int width, int height) {
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;

        HEX_SIDE_LENGTH = SCREEN_WIDTH / 12f / SIGN_THREE; //边的长度
        HEX_WIDTH = HEX_SIDE_LENGTH * SIGN_THREE; //Hex宽度, 每个Hex间隔为HEX_WIDTH/8, 共有8个,屏幕两侧各留出一个的距离


        HEX_HEIGHT = HEX_SIDE_LENGTH * 2f;

        HEX_SPACE = HEX_WIDTH / 8f;
    }




    /**
     * 根据传入坐标,构造六边形路径.
     * @param posX 顶点x坐标
     * @param posY 顶点y坐标
     * @return 返回构建的Hex
     */
    public static Path mkHexFromPos(float posX, float posY) {
        Path path = new Path();
        path.moveTo(posX, posY);
        path.lineTo(posX + SIGN_THREE / 2 * HEX_SIDE_LENGTH, posY + HEX_SIDE_LENGTH / 2);
        path.lineTo(posX + SIGN_THREE / 2 * HEX_SIDE_LENGTH, (float) (posY + 1.5 * HEX_SIDE_LENGTH));
        path.lineTo(posX, posY + 2 * HEX_SIDE_LENGTH);
        path.lineTo(posX - SIGN_THREE / 2 * HEX_SIDE_LENGTH, (float) (posY + 1.5 * HEX_SIDE_LENGTH));
        path.lineTo(posX - SIGN_THREE / 2 * HEX_SIDE_LENGTH, posY + HEX_SIDE_LENGTH / 2);
        path.close();
        return path;
    }

    public static Path mkSmallHexFromPos(float posX, float posY) {
        Path path = new Path();
        float length = HEX_SIDE_LENGTH * 0.8f;
        path.moveTo(posX, posY);
        path.lineTo(posX + SIGN_THREE / 2 * length, posY + length / 2);
        path.lineTo(posX + SIGN_THREE / 2 * length, (float) (posY + 1.5 * length));
        path.lineTo(posX, posY + 2 * length);
        path.lineTo(posX - SIGN_THREE / 2 * length, (float) (posY + 1.5 * length));
        path.lineTo(posX - SIGN_THREE / 2 * length, posY + length / 2);
        path.close();
        return path;


    }





    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static PointF clacPos(int x, int y) {
        float posX = HEX_WIDTH +
                (HEX_WIDTH + HEX_SPACE) * x +
                HEX_WIDTH / 2f +
                Math.abs(y - 4) * (HEX_WIDTH + HEX_SPACE) / 2f;
        float posY = SCREEN_HEIGHT / 2.5f +
                (y - 4) * ((HEX_SIDE_LENGTH + HEX_HEIGHT) / 2f + HEX_SPACE);

        return new PointF(posX, posY);
    }
}
