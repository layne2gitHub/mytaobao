package com.layne.my.taobao.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HttpClient工具类
 */
public class HttpClientUtils {

    public static final String GET="get";
    public static final String POST="post";

    public static final String REQUEST_HEADER_CONNECTION="keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT="ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36";

    /**
     * GET请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){
        return  createRequest(GET,url,null);
    }

    /**
     * GET请求
     * @param url
     * @param cookie
     * @return
     */
    public static String doGet(String url,String cookie){
        return  createRequest(GET,url,cookie);
    }

    /**
     * POST请求
     * @param url 请求地址
     * @param params 请求参数
     * @return
     */
    public static String doPost( String url,BasicNameValuePair... params){
        return  createRequest(POST,url,null,params);
    }

    /**
     * POST请求
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    public static String doPost( String url,String cookie,BasicNameValuePair... params){
        return  createRequest(POST,url,cookie,params);
    }



    /**
     * 创建请求
     * @param requestMethod
     * @param url 请求地址
     * @param cookie cookie
     * @param params 请求参数
     * @return
     *
     * Ctrl+Alt+T 局部Try{}catch{}
     */
    private static String createRequest(String requestMethod, String url, String cookie,BasicNameValuePair... params){
        // 创建 HttpClient 客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //请求结果
        String result=null;
        //请求方式
        HttpGet httpGet=null;
        HttpPost httpPost=null;
        //响应
        CloseableHttpResponse httpResponse=null;
        try {
            if(GET.equals(requestMethod)){
                httpGet=new HttpGet(url);
                httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie",cookie);
                httpResponse = client.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
            }else if(POST.equals(requestMethod)){
                httpPost=new HttpPost(url);
                httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie",cookie);
                //请求参数
                if(params !=null && params.length > 0){
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params),"UTF-8"));
                    httpResponse = client.execute(httpPost);
                }
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(client!=null){
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
