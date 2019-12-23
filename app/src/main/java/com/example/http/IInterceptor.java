package com.example.http;

public interface IInterceptor {
    Responce intercept(RealInterceptorChain chain);
}
