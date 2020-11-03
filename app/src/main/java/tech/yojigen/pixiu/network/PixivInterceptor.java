package tech.yojigen.pixiu.network;

import android.os.Build;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tech.yojigen.pixiu.utils.YMD5;

public class PixivInterceptor implements Interceptor {
    public final String BASE_HASH = "28c1fdd170a5204386cb1313c7077b34f83e4aaf4aa829ce78c231e05b0bae2c";

    public final String APP_VERSION = "5.0.200";
    public final String ACCEPT_LANGUAGE = "zh_CN";
    public final String APP_OS = "Android";
    public final String APP_OS_VERSION = Build.VERSION.RELEASE;
    public final String USER_AGENT = "Android " + Build.VERSION.RELEASE + "; " + Build.MODEL;
    public final String REFERER = "https://www.pixiv.net";

//    public final String CLIENT_ID = "MOBrBDS8blbauoSck0ZfDbtuzpyT";
//    public final String CLIENT_SECRET = "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String X_Client_Time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.CHINA).format(new Date());
        String X_Client_Hash = String.valueOf(YMD5.convert(X_Client_Time + BASE_HASH));
        Request request = chain.request().newBuilder()
                .addHeader("User-Agent", USER_AGENT)
                .addHeader("Accept-Language", ACCEPT_LANGUAGE)
                .addHeader("App-OS", APP_OS)
                .addHeader("App-OS-Version", APP_OS_VERSION)
                .addHeader("App-Version", APP_VERSION)
                .addHeader("Referer", REFERER)
                .addHeader("X-Client-Time", X_Client_Time)
                .addHeader("X-Client-Hash", X_Client_Hash)
                .build();
        return chain.proceed(request);
    }
}
