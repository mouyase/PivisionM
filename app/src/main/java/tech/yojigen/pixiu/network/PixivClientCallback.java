package tech.yojigen.pixiu.network;

import okhttp3.Response;

public interface PixivClientCallback {
    abstract void success(String bodyString);

    abstract void error();
}