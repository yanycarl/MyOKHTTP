package com.example.http;

import android.util.Log;

public class ServerCallInterceptor implements IInterceptor {
    @Override
    public Responce intercept(RealInterceptorChain chain) {
        if(chain.codeC != null){
            chain.codeC.writeContent(chain.request.getPackage());
            Responce responce = chain.codeC.readContent();
            return responce;
        }
        return null;
    }
}
