package tech.yojigen.pixiu.network;

import com.xuexiang.xui.BuildConfig;

import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class PixivClient {
    private static OkHttpClient mClient;

    private PixivClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(new PixivInterceptor());
        mClient = builder.build();
    }

    public static OkHttpClient getClient() {
        return mClient;
    }
}
