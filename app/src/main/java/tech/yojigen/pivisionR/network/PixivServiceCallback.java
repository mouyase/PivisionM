package tech.yojigen.pivisionR.network;

public interface PixivServiceCallback {
    abstract <T> void success(T jsonObject);

    abstract void error();
}