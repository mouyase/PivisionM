package tech.yojigen.utils;

import android.widget.Toast;

public class YToast {
    public static void show(String text) {
        Toast.makeText(YUtil.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
