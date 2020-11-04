package tech.yojigen.pixiu.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import tech.yojigen.pixiu.BuildConfig;
import tech.yojigen.utils.YThread;

public class PixivClient {
    private static PixivClient mPixivClient = new PixivClient();
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

    public static void post(String url, PixivForm form, PixivClientCallback callback) {
        Request.Builder builder = new Request.Builder();
        builder.post(form.getBody());
        builder.url(url);
        Request request = builder.build();
        Call call = PixivClient.getClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                YThread.runOnUiThread(() -> callback.error());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bodyString = response.body().string();
                YThread.runOnUiThread(() -> callback.success(bodyString));
            }
        });
    }
}
