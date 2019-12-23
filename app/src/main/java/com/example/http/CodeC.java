package com.example.http;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CodeC {
    private Socket socket;
    private InetSocketAddress mAddress;
    boolean isInited = false;

    public CodeC(String ip){
        if(ip == null){
            throw new IllegalArgumentException("DNS Error");
        }
        createSocket();
        buildConnection(ip);
    }

    private void createSocket(){
        Socket client = new Socket();
        socket = client;
    }

    private void buildConnection(String ip){
        mAddress = new InetSocketAddress(ip, 80);
        try {
            socket.connect(mAddress, 1000);
            isInited = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeContent(String request){
        if( mAddress == null){
            throw new IllegalArgumentException("Empty Address");
        }
        PrintWriter pWriter;
        try {
            pWriter = new PrintWriter(socket.getOutputStream(),true);
            pWriter.println(request);
            pWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Responce readContent(){
        boolean htmlStarted = false;
        Responce responce = new Responce();
        try {
            String lineContent;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            responce.parseFirstLine(bufferedReader.readLine());

            if(!responce.getStatusCode().equals("200")){
                return responce;
            }

            ResponseHeader header = new ResponseHeader();
            StringBuffer stringBuffer = new StringBuffer();

            lineContent = bufferedReader.readLine();
            while(lineContent != null) {
                if(lineContent.startsWith("{")){
                    responce.setJson(lineContent);
                }
                else if(htmlStarted || lineContent.startsWith("<")){
                    htmlStarted = true;
                    stringBuffer.append(lineContent);
                }
                else{
                    header.parseHeader(lineContent);
                }
                lineContent = bufferedReader.readLine();
            }
            responce.setHtml(stringBuffer.toString());
            responce.setmResponseHeader(header);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responce;
    }
}
