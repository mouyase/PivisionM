package tech.yojigen.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class YFile {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static void savePicture(Bitmap bitmap, String imageName) {
        try {
            OutputStream outputStream;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = mContext.getContentResolver();
                //设置文件参数到ContentValues中
                ContentValues values = new ContentValues();
                //设置文件名
                values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
                //设置文件描述，这里以文件名代替
                values.put(MediaStore.Images.Media.DESCRIPTION, imageName);
                //设置文件类型为image/*
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
                //注意：MediaStore.Images.Media.RELATIVE_PATH需要targetSdkVersion=29,
                //故该方法只可在Android10的手机上执行
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Pixiu");
                //EXTERNAL_CONTENT_URI代表外部存储器
                Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                //insertUri表示文件保存的uri路径
                Uri insertUri = resolver.insert(external, values);
                outputStream = resolver.openOutputStream(insertUri);
            } else {
                File file = new File(Environment.getExternalStorageDirectory() +
                        File.separator +
                        Environment.DIRECTORY_PICTURES +
                        File.separator +
                        "Pixiu" +
                        File.separator + imageName);
                file.getParentFile().mkdirs();
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(mContext, "图片已保存到 " + "Pictures/Pixiu/" + imageName, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "图片保存失败", Toast.LENGTH_LONG).show();
        }
    }
}