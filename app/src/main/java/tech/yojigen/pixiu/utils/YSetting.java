package tech.yojigen.pixiu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 处理应用设置的工具类
 */
public class YSetting {
    private static SharedPreferences.Editor mEditor;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 获取设置项
     *
     * @param key 关键字
     * @param <T> 泛型
     * @return 对应设置项的值
     */
    public static <T> T get(String key, T defaultValue) {
        Map<String, ?> settingMap = mContext.getSharedPreferences(mContext.getPackageName() + ".setting", Context.MODE_PRIVATE).getAll();
        Object value = settingMap.get(key);
        if (!settingMap.containsKey(key)) {
            set(key, defaultValue);
            value = defaultValue;
        }
        return (T) value;
    }

    /**
     * 添加设置项
     *
     * @param key   关键字
     * @param value 设置项的值
     */
    public static <T> void set(String key, T value) {
        mEditor = mContext.getSharedPreferences(mContext.getPackageName() + ".setting", Context.MODE_PRIVATE).edit();
        if (value instanceof Integer) {
            mEditor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            mEditor.putString(key, (String) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) value);
        }
        mEditor.apply();
    }

    /**
     * 删除设置项
     *
     * @param key 关键字
     */
    public static void del(String key) {
        mEditor = mContext.getSharedPreferences(mContext.getPackageName() + ".setting", Context.MODE_PRIVATE).edit();
        mEditor.remove(key);
        mEditor.apply();
    }
}