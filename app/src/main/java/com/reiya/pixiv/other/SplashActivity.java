package com.reiya.pixiv.other;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.reiya.pixiv.base.BaseApplication;
import com.reiya.pixiv.image.ImageLoader;
import com.reiya.pixiv.main.MainActivity;
import com.reiya.pixiv.profile.ProfileActivity;
import com.reiya.pixiv.util.UserData;
import com.reiya.pixiv.work.ViewActivity;

import tech.yojigen.pivisionm.R;

import static com.reiya.pixiv.base.BaseApplication.AUTH_EXPIRE_TIME;

public class SplashActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_LOGIN = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (UserData.isLoggedIn()) {
            enter();
        } else if (BaseApplication.getAuthTime() + AUTH_EXPIRE_TIME > System.currentTimeMillis()) {
            UserData.setBearer(BaseApplication.getToken());
            UserData.user = BaseApplication.getUser();
            UserData.setLoggedInState();
            enter();
        } else {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String account = sharedPreferences.getString(getString(R.string.key_account), null);
            String password = sharedPreferences.getString(getString(R.string.key_password), null);
            if (account == null || password == null) {
                enterLoginPage();
            } else {
                BaseApplication.getInstance().login(account, password, false,
                        user -> enter(),
                        () -> enterLoginPage());
            }
        }

        String url = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_splash_screen_url), "");
        int times = PreferenceManager.getDefaultSharedPreferences(this).getInt(getString(R.string.key_splash_screen_showed_times), 0);
        if (!url.equals("") && times < 3) {
            ImageView iv = findViewById(R.id.imageView);
            ImageLoader.loadImageFromCache(this, url)
                    .centerCrop()
                    .load(iv);
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(getString(R.string.key_splash_screen_showed_times), times + 1).apply();
        }
    }

    private void enterLoginPage() {
        startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (UserData.isLoggedIn()) {
            enter();
        } else {
            finish();
        }
    }

    private void enter() {
        Intent intent;
        String url = getIntent().getDataString();
//        https://www.pixiv.net/member_illust.php?mode=medium&illust_id=76259440
        if (url == null) {
            intent = new Intent(this, MainActivity.class);
        } else {
            //新链接处理
            if (url.contains("artworks")) {
                url = url.replace("https://www.pixiv.net/artworks/", "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=");
            }
            if (url.contains("mode=medium")) {
                intent = new Intent(this, ViewActivity.class);
            } else {
                intent = new Intent(this, ProfileActivity.class);
            }
            intent.setData(Uri.parse(url));
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
