package com.example.http;

public interface Callback {
    void onFailed(Exception e);

    void onResult(Responce responce);
}
