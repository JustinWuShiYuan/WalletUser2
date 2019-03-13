package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestOcrIdcard implements Serializable {

    private String api_key;
    private String api_secret;
    private String image_base64;
    private String legality;

    public RequestOcrIdcard() {
    }

    public RequestOcrIdcard(String api_key, String api_secret, String image_base64, String legality) {
        this.api_key = api_key;
        this.api_secret = api_secret;
        this.image_base64 = image_base64;
        this.legality = legality;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
    }

    @Override
    public String toString() {
        return "RequestOcrIdcard{" +
                "api_key='" + api_key + '\'' +
                ", api_secret='" + api_secret + '\'' +
                ", image_base64='" + image_base64 + '\'' +
                ", legality='" + legality + '\'' +
                '}';
    }
}
