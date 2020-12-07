package tech.yojigen.pivisionR.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tech.yojigen.pivisionR.network.PixivForm;
import tech.yojigen.pivisionR.network.PixivService;
import tech.yojigen.pivisionR.network.PixivServiceCallback;
import tech.yojigen.pivisionR.network.dto.OAuthDTO;

public class RouterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PixivForm pixivForm = new PixivForm();

        PixivService.getService().auth(pixivForm, new PixivServiceCallback() {
            @Override
            public <T> void success(T jsonObject) {
                OAuthDTO dto = (OAuthDTO) jsonObject;
//                System.out.println(dto.toString());
                RouterActivity.this.startActivity(new Intent(RouterActivity.this, MainActivity.class));
            }

            @Override
            public void error() {

            }
        });
    }
}
