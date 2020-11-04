package tech.yojigen.pixiu.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

public class PixivForm {
    private Map<String, String> mBodyMap = new HashMap<>();

    public <T> void set(String key, T value) {
        mBodyMap.put(key, (String) value);
    }

    public FormBody getBody() {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : mBodyMap.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}
