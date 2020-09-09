package ru.geekbrains.atm.notify;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.aspectj.apache.bcel.classfile.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpNotifier implements EventListener {

    private String url;

    public HttpNotifier(String url) {
        this.url = url;
    }

    public HttpNotifier() {

    }

    @Override
    public void notify(String msg, String category) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("msg", msg));
        params.add(new BasicNameValuePair("category", category));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
