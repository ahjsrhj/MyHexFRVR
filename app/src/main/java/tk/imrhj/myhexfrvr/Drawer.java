package tk.imrhj.myhexfrvr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tk.imrhj.myhexfrvr.item.BaseHex;
import tk.imrhj.myhexfrvr.item.Hex;
import tk.imrhj.myhexfrvr.item.HighScore;
import tk.imrhj.myhexfrvr.item.Score;
import tk.imrhj.myhexfrvr.item.childItem.ArchHex;
import tk.imrhj.myhexfrvr.item.childItem.BarHex;
import tk.imrhj.myhexfrvr.item.childItem.BeeHex;
import tk.imrhj.myhexfrvr.item.childItem.OneHex;
import tk.imrhj.myhexfrvr.item.childItem.PistolHex;

/**
 * Created by rhj on 15/11/2.
 */
public class Drawer implements Runnable, View.OnTouchListener {
    private static final String TAG = "测试Drawer";
    private SurfaceHolder mHolder;
    private Paint mHexBGPaint; //画笔
    private Canvas mCanvas;

    private Hex[][] mMatrix;                //用来存放所有点的矩阵 大小9*9
    private List<Hex> mUnlightPoint;        //用来存放移动待选块时背景亮起的点
    private List<List<Hex>> mHexList;       //用来存放每一列hex的，方便判断该列是否满 大小9 * 3

    private BaseHex[] baseHex;              //两个待选块的父类
    private BaseHex activityHex;            //当前触摸的待选块
    private int mAcitivityHexPos = -1;      //当前激活的待选块是第几个 取值 0 - 1

    private Score mScore;                   //得分
    private HighScore mHighScore;           //最高分


    public Drawer(SurfaceHolder holder, Context context) {
        super();
        mHolder = holder;

        initPaint();
        initHex();
        initMatrixList();

        mScore = new Score();
        mHighScore = new HighScore(context);

    }


    /**
     *  初始化Hex数组
     */
    private void initHex() {
        mMatrix = new Hex[9][9];
        //因为这里的背景块并不是规则的,所以在循环里嵌套了条件编译
        Log.d(this.toString(), "--------------------");
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < (y < 5 ? y + 5 : 13 - y); x++) {
                mMatrix[y][x] = new Hex(x, y);
                Log.d(this.toString(), "y" + y + " : x" + x);
            }
        }
        mUnlightPoint = new ArrayList<>();

        baseHex = new BaseHex[2];

        addRandomBaseHex(0);
        addRandomBaseHex(1);
    }

    /**
     * 随机添加块 共五种，0-4分别是
     * ArchHex
     * BarHex
     * BeeHex
     * OneHex
     * PistolHex
     * @param i
     */
    private void addRandomBaseHex(int i) {
        int type = (int) (Math.random() * 1000 % 5);
        switch (type) {
            case 0:
                baseHex[i] = new ArchHex();
                break;
            case 1:
                baseHex[i] = new BarHex();
                break;
            case 2:
                baseHex[i] = new BeeHex();
                break;
            case 3:
                baseHex[i] = new OneHex();
                break;
            case 4:
                baseHex[i] = new PistolHex();
                break;
        }
        if (i == 0) {
            baseHex[0].setPosXY(Utils.SCREEN_WIDTH / 4f, Utils.SCREEN_HEIGHT * 0.8f);
        } else {
            baseHex[1].setPosXY(Utils.SCREEN_WIDTH / 4f * 3f, Utils.SCREEN_HEIGHT * .8f);
        }
    }


    /**
     *  初始化Paint画笔
     *
     */
    private void initPaint() {
        mHexBGPaint = new Paint();
        mHexBGPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mHexBGPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mHexBGPaint.setStrokeJoin(Paint.Join.ROUND);
        mHexBGPaint.setStrokeCap(Paint.Cap.ROUND);
        mHexBGPaint.setStrokeWidth(0);
        mHexBGPaint.setColor(Utils.COLOR_DEFAULT);
    }

    /**
     * 初始化矩阵列表，用来存储每一行的hex,以判断当前行是否充满。
     */
    private void initMatrixList() {
        mHexList = new ArrayList<>();
        List<Hex> hexes;

        //每一行
        for (int y = 0; y < 9; y++) {
            hexes = new ArrayList<>();
            for (int x = 0; x < (y < 5 ? y + 5 : 13 - y); x++) {
                hexes.add(mMatrix[y][x]);
            }
            mHexList.add(hexes);
        }

        /**
         * 每一左斜阵列
         *
         * 第一行                      第二行
         * x = 0    y = 0 1 2 3 4       x = 1   y = 0 1 2 3 4
         *                              x = 0   y = 5
         *
         * 第三行                      第四行
         * x = 2    y = 0 1 2 3 4       x = 3   y = 0 1 2 3 4
         * x = 1    y = 5               x = 2   y = 5
         * x = 0    y = 6               x = 1   y = 6
         *                              x = 0   y = 7
         *
         * 第五行                      第六行
         * x = 4    y = 0 1 2 3 4       x = 5   y = 1 2 3 4
         * x = 3    y = 5               x = 4   y = 5
         * x = 2    y = 6               x = 3   y = 6
         * x = 1    y = 7               x = 2   y = 7
         * x = 0    y = 8               x = 1   y = 8
         *
         * 第七行                      第八行
         * x = 6    y = 2 3 4           x = 7   y = 3 4
         * x = 5    y = 5               x = 6   y = 5
         * x = 4    y = 6               x = 5   y = 6
         * x = 3    y = 7               x = 4   y = 7
         * x = 2    y = 8               x = 3   y = 8
         *
         * 第九行
         * x = 8    y = 4
         * x = 7    y = 5
         * x = 6    y = 6
         * x = 5    y = 7
         * x = 4    y = 8
         */
        for (int x = 0; x < 9; x++) {
            hexes = new ArrayList<>();
            for (int y = (x < 4 ? 0 : x - 4); y < (x + 5 < 9 ? x + 5 : 9); y++) {
                hexes.add(mMatrix[y][(y < 5 ? x : x - y + 4)]);
            }
            mHexList.add(hexes);
        }

        /**
         * 每一右斜阵列
         *
         * 第一行 x = 0
         * x = 0    y = 4 5 6 7 8
         *
         * 第二行 x = 1
         * x = 0    y = 3
         * x = 1    y = 4 5 6 7 8
         *
         * 第三行 x = 2
         * x = 0    y = 2
         * x = 1    y = 3
         * x = 2    y = 4 5 6 7 8
         *
         * 第四行 x = 3
         * x = 0    y = 1
         * x = 1    y = 2
         * x = 2    y = 3
         * x = 3    y = 4 5 6 7 8
         *
         * 第五行 x = 4
         * x = 0    y = 0
         * x = 1    y = 1
         * x = 2    y = 2
         * x = 3    y = 3
         * x = 4    y = 4 5 6 7 8
         *
         * 第六行 x = 5
         * x = 1    y = 0
         * x = 2    y = 1
         * x = 3    y = 2
         * x = 4    y = 3
         * x = 5    y = 4 5 6 7
         *
         * 第七行 x = 6
         * x = 2    y = 0
         * x = 3    y = 1
         * x = 4    y = 2
         * x = 5    y = 3
         * x = 6    y = 4 5 6
         *
         * 第八行 x = 7
         * x = 3    y = 0
         * x = 4    y = 1
         * x = 5    y = 2
         * x = 6    y = 3
         * x = 7    y = 4 5
         *
         * 第九行 x = 8
         * x = 4    y = 0
         * x = 5    y = 1
         * x = 6    y = 2
         * x = 7    y = 3
         * x = 8    y = 4
         *
         */

        for (int x = 0; x < 9; x++) {
            hexes = new ArrayList<>();
            for (int i = (x > 4 ? x - 4 : 0); i < x; i++) {
                hexes.add(mMatrix[4 - x + i][i]);
            }
            for (int y = 4; y < (13 - x > 9 ? 9 : 13 - x); y++) {
                hexes.add(mMatrix[y][x]);
            }
            mHexList.add(hexes);
        }


    }


    @Override
    public void run() {
        while (true) {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {

                mCanvas.drawColor(Color.BLACK);

                //绘制分数
                mScore.drawSelf(mCanvas);
                mHighScore.drawSelf(mCanvas);
                // 绘制主体背景
                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < (y < 5 ? y + 5 : 13 - y); x++) {
                        mMatrix[y][x].drawSelf(mCanvas, mHexBGPaint);
                    }
                }


                // 绘制三个待选块
                for (int i = 0; i < 2; i++) {
                    baseHex[i].drewSelf(mCanvas, mHexBGPaint);
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null && mHolder != null) {
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }

    /**
     * 检测移动方块是否和当前方块重合
     */
    private void checkOver() {
        for (Hex h : mUnlightPoint) {
            h.setStatus(Hex.EMPTY);
            h.setColor(Utils.COLOR_DEFAULT);
        }
        mUnlightPoint.clear();

        PointF[] points = activityHex.getPositions();

        for (PointF pointF : points) {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < (y < 5 ? y + 5 : 13 - y); x++) {
                    Hex hex = mMatrix[y][x];
                    if (hex.getStatus() == Hex.EMPTY) {
                        if (hex.checkPoint(pointF)) {
                            mUnlightPoint.add(hex);
                        }
                    }
                }
            }
        }

        if (mUnlightPoint.size() != activityHex.getSize()) {
            mUnlightPoint.clear();
        } else {
            for (Hex hex : mUnlightPoint) {
                hex.setStatus(Hex.UNLIGHT);
                hex.setColor(activityHex.getUnlightColor());
            }
        }
    }

    private void cleanFull() {
        boolean thisIsEmpty;
        List<Integer> fullHex = new ArrayList<>();
        List<Hex> hexes;
        for (int i = 0; i < mHexList.size(); i++) {
            hexes = mHexList.get(i);
            thisIsEmpty = true;

            for (Hex hex : hexes) {
                if (hex.isEmpty()) {
                    thisIsEmpty = false;
                    break;
                }
            }

            if (thisIsEmpty) {
                fullHex.add(i);
            }
        }

        int i = 0;
        for (Integer integer : fullHex) {
            i++;
            int score = 0;
            score += (120 + 20 * ((mHexList.get(integer)).size() - 4)) * i;
            mScore.addScore(score);
            mHighScore.updateHighScore(mScore.getScore());


            for (Hex hex : mHexList.get(integer)) {
                hex.setStatus(Hex.EMPTY);
                hex.setColor(Utils.COLOR_DEFAULT);
            }
        }
    }


    /**
     * 触摸事件的回调函数
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                float posX, posY;

                for (int i = 0; i < 2; i++) {
                    posX = baseHex[i].getPosX();
                    posY = baseHex[i].getPosY();
                    if (x < posX + Utils.HEX_WIDTH * 2f
                            && x > posX - Utils.HEX_WIDTH * 2f
                            && y < posY + Utils.HEX_HEIGHT * 2f
                            && y > posY - Utils.HEX_HEIGHT * 2f) {

                        activityHex = baseHex[i];
                        mAcitivityHexPos = i;
                        activityHex.setOnTouch(true);
                        activityHex.setPosXY(x, y - Utils.HEX_HEIGHT * 2f);
                        break;
                    }
                }
                break;
            /**
             * 一个点未实现功能
             */
            case MotionEvent.ACTION_MOVE:
                if (activityHex != null && activityHex.isOnTouch()) {
                    activityHex.setPosXY(event.getX(), event.getY() - Utils.HEX_HEIGHT * 2f);
                    checkOver();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (activityHex != null) {
                    checkOver();
                    activityHex.setOnTouch(false);
                }


                if (mUnlightPoint.size() != 0) { //放下一个块
                    for (Hex h : mUnlightPoint) {
                        h.setStatus(Hex.FULL);
                        h.setColor(activityHex.getColor());
                    }
                    addRandomBaseHex(mAcitivityHexPos);
                    mUnlightPoint.clear();

                    mScore.putHexs();
                    mHighScore.updateHighScore(mScore.getScore());
                } else {
                    baseHex[0].setPosXY(Utils.SCREEN_WIDTH / 4f, Utils.SCREEN_HEIGHT * 0.8f);
                    baseHex[1].setPosXY(Utils.SCREEN_WIDTH / 4f * 3f, Utils.SCREEN_HEIGHT * .8f);
                }

                cleanFull();

                break;
        }
        return true;
    }


}
