package com.example.http;

public class ConnectInterceptor implements IInterceptor {

    @Override
    public Responce intercept(RealInterceptorChain chain) {
        CodeC codeC = new CodeC(chain.request.getIp());
        return chain.proceed(chain.request, codeC);
    }
}
