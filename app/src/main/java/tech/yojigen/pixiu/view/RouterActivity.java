package tech.yojigen.pixiu.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tech.yojigen.pixiu.network.PixivForm;
import tech.yojigen.pixiu.network.PixivService;
import tech.yojigen.pixiu.network.PixivServiceCallback;
import tech.yojigen.pixiu.network.dto.OAuthDTO;

public class RouterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PixivForm pixivForm = new PixivForm();
        pixivForm.set("username", "mouyase");
        pixivForm.set("password", "a115324132");
        pixivForm.set("grant_type", "password");
        PixivService.getService().auth(pixivForm, new PixivServiceCallback() {
            @Override
            public <T> void success(T jsonObject) {
                OAuthDTO dto = (OAuthDTO) jsonObject;
                System.out.println(dto.toString());
            }

            @Override
            public void error() {

            }
        });
    }
}
