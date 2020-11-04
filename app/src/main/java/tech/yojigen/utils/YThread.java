package tech.yojigen.utils;

import android.os.Handler;
import android.os.Looper;

public class YThread {
    private static final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private static Thread mUiThread;

    public static final void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != mUiThread) {
            mUiHandler.post(action);
        } else {
            action.run();
        }
    }
}
