package tech.yojigen.pixiu.network;

public interface PixivServiceCallback {
    abstract <T> void success(T jsonObject);

    abstract void error();
}