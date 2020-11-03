package tech.yojigen.pixiu.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import static android.view.View.NO_ID;

public class YViewSize {
    private static final String NAVIGATION = "navigationBarBackground";

    // 该方法需要在View完全被绘制出来之后调用，否则判断不了
    //在比如 onWindowFocusChanged（）方法中可以得到正确的结果
    public static boolean isNavigationBarExist(@NonNull Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).getContext().getPackageName();
            if (viewGroup.getChildAt(i).getId() != NO_ID && NAVIGATION.equals(activity.getResources().getResourceEntryName(viewGroup.getChildAt(i).getId()))) {
                return true;
            }
        }
        return false;
    }

    public static int getHeightNoStatusBar(Activity activity) {
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int navigationBarHeight = 0;
        int resourceId_navigation = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId_navigation > 0) {
            navigationBarHeight = activity.getResources().getDimensionPixelSize(resourceId_navigation);
        }
        if (!YViewSize.isNavigationBarExist(activity)) {
            navigationBarHeight = 0;
        }

        //获取屏幕实际高度
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealSize(point);
        }
        return point.y - navigationBarHeight;
    }

    public static int getScreenWidth(Activity activity) {
        return activity.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getProportionallyHeight(Activity activity, int width, int height, int sourceWidth) {
        return (int) (width * (Float.parseFloat(String.valueOf(height)) / Float.parseFloat(String.valueOf(width))));
    }

    public static int getStatusBarHeight(Activity activity) {
        int statusBarHeight = 0;
        int resourceId_status = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId_status > 0) {
            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId_status);
        }
        return statusBarHeight;
    }
}
