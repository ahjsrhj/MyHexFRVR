package tk.imrhj.myhexfrvr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

/**
 * Created by rhj on 15/11/1.
 */
public class Playground extends SurfaceView implements SurfaceHolder.Callback{

    private static final String TAG = "onPlayground";
    private Thread mThread;
    private Drawer mDrawer;



    public Playground(Context context) {
        super(context);

        init();
    }


    public Playground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Playground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 执行一些初始化的命令
     */
    private void init() {

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        Utils.initUtils(width, height);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged: ");
        mDrawer = new Drawer(holder, getContext());
        setOnTouchListener(mDrawer);
        mThread = new Thread(mDrawer);
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed: ");

    }


}
