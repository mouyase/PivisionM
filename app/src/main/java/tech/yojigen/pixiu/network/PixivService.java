package tech.yojigen.pixiu.network;

import com.google.gson.Gson;

import tech.yojigen.pixiu.network.dto.OAuthDTO;

public class PixivService {
    private final String HOST_API = "https://app-api.128512,xyz";
    private final String HOST_AUTH = "https://oauth.128512.xyz";
    private final String HOST_ACCOUNTS = "https://accounts.128512.xyz";
    private final String HOST_SEARCH = "https://search.128512.xyz";
    private static PixivService mPixivService = new PixivService();

    private Gson gson = new Gson();

    public static PixivService getService() {
        return mPixivService;
    }

    public void auth(PixivForm pixivForm, PixivServiceCallback callback) {
        String api = HOST_AUTH + "/auth/token";
        pixivForm.set("client_id", "MOBrBDS8blbauoSck0ZfDbtuzpyT");
        pixivForm.set("client_secret", "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj");
        pixivForm.set("device_token", "pixiv");
        pixivForm.set("get_secure_url", "true");
        pixivForm.set("include_policy", "true");
        PixivClient.post(api, pixivForm, new PixivClientCallback() {
            @Override
            public void success(String bodyString) {
                OAuthDTO dto = gson.fromJson(bodyString, OAuthDTO.class);
                callback.success(dto);
            }

            @Override
            public void error() {

            }
        });
    }
}
