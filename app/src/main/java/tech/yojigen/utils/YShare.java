package tech.yojigen.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 处理分享的工具类
 */
public class YShare {
    public static void image(Bitmap bitmap) {
        try {
            String time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.CHINA).format(new Date());
            String fileName = String.valueOf(string2md5(time));
            String filePath = YUtil.getApplicationContext().getExternalCacheDir() + "/.YojigenShare/" + fileName + ".png";
            File file = new File(filePath);
            File path = new File(YUtil.getApplicationContext().getExternalCacheDir() + "/.YojigenShare/");
            if (!path.exists()) {
                path.mkdir();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            intent.setType("image/*");
            Intent shareIntent = Intent.createChooser(intent, "分享图片到");
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            YUtil.getApplicationContext().startActivity(shareIntent);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("YShare", "Image create error", e);
            YToast.show("图片分享异常");
        }
    }

    public static void shareText(String string) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, string);
        intent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(intent, "分享图片到");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        YUtil.getApplicationContext().startActivity(shareIntent);
    }

    /**
     * MD5转换方法
     */
    private static String string2md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
