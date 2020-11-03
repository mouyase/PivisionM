package tech.yojigen.pixiu.network;

public class PixivService {
    private final String HOST_API = "https://app-api.128512,xyz";
    private final String HOST_AUTH = "https://oauth.128512,xyz";
    private final String HOST_ACCOUNTS = "https://accounts.128512,xyz";
    private final String HOST_SEARCH = "https://search.128512,xyz";
    private static PixivService mPixivService = new PixivService();

    public static PixivService getService() {
        return mPixivService;
    }

    public void auth() {
    }
}
