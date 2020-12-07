package tech.yojigen.pivisionR.network;

public interface PixivClientCallback {
    abstract void success(String bodyString);

    abstract void error();
}