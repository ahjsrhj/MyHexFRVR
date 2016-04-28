package tk.imrhj.myhexfrvr;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity{

    private Playground mPlayground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPlayground = new Playground(this);
        setContentView(mPlayground);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBakcground));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mPlayground.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mPlayground.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPlayground.onRestart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        mPlayground.onRestart();
    }
}
