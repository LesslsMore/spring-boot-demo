package com.lesslsmore.bili.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.entity.video.InfoResp;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

public class API {
    public static SpaceResp getUserSpace(String mid, Integer pn) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Map<String, Object> params = new HashMap<>();
        params.put("mid", mid);
        params.put("pn", pn);
        String finalParam = WBI.get_wbi_sign(params);
        String url = "https://api.bilibili.com/x/space/wbi/arc/search?" + finalParam;
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("cache-control", "max-age=0")
                .addHeader("cookie", "buvid3=DF9EC251-7549-FE46-6845-F4A757B5FC0E16707infoc; b_nut=1705127716; _uuid=1094581059-4785-D599-F8AD-26C10988A9AE118001infoc; buvid4=887FA9BF-0BD8-F1AB-DD07-E994BA8861CD17854-024011306-DUtoXy43xsqc%2BUnkEqXf5w%3D%3D; DedeUserID=4608012; DedeUserID__ckMd5=2fa848b2c0514372; rpdid=|(u))kY|mmlR0J'u~|luk~~Jk; buvid_fp_plain=undefined; enable_web_push=DISABLE; header_theme_version=CLOSE; hit-dyn-v2=1; SESSDATA=c36ba40d%2C1725975220%2C99856%2A31CjAwRkyCDPlLFHgfURROm7uwii-uUvraMeyRJbcpYjdUEdQT6zMinATK-6MTtSMjwlQSVjRMU1RQV2hNblVjZks0bjdQa3pKUnhuTXJMTFQyWEpORmw1ME56N2NHaTNHbnNzekdfd3M5bmZSbk4yZVh1S2JhQkZ1dTFqQlVhcUFRSUd3S3IyS3dnIIEC; bili_jct=c1a604c10db5427ce6e5a0e372a36753; sid=68wvz15r; CURRENT_QUALITY=80; fingerprint=c7cde790db02bfe48cf37218692d0ae6; FEED_LIVE_VERSION=V_WATCHLATER_PIP_WINDOW3; bsource=search_google; home_feed_column=5; buvid_fp=fd6c9e5e2f197ff0d8d5c705a0d45999; PVID=1; browser_resolution=1536-695; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTQzMDA2NzgsImlhdCI6MTcxNDA0MTQxOCwicGx0IjotMX0.tKKwXSZG1R-YG_p8sNOKgpNIywVlAP_UZzGqgHbSmRQ; bili_ticket_expires=1714300618; CURRENT_FNVAL=4048; bp_video_offset_4608012=924292758994157590; b_lsid=698DECE5_18F152D5AED")
                .addHeader("priority", "u=0, i")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"124\", \"Google Chrome\";v=\"124\", \"Not-A.Brand\";v=\"99\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "none")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonData = response.body().string();

                // 使用 FastJSON 解析 JSON 字符串为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonData);

                // 将 JSONObject 转换为 Java 对象
                SpaceResp resp = jsonObject.toJavaObject(SpaceResp.class);
                return resp;

            } else {
                System.out.println("Network request failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SpaceResp();
    }

    public static InfoResp getVideoInfo(String bvid) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
        String url = "https://api.bilibili.com/x/web-interface/view?bvid=" + bvid;
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .header("priority", "u=0, i")
                .header("sec-fetch-user", "?1")
                .header("upgrade-insecure-requests", "1")
                .header("Cookie", "buvid3=DF9EC251-7549-FE46-6845-F4A757B5FC0E16707infoc; b_nut=1705127716; _uuid=1094581059-4785-D599-F8AD-26C10988A9AE118001infoc; buvid4=887FA9BF-0BD8-F1AB-DD07-E994BA8861CD17854-024011306-DUtoXy43xsqc%2BUnkEqXf5w%3D%3D; DedeUserID=4608012; DedeUserID__ckMd5=2fa848b2c0514372; rpdid=|(u))kY|mmlR0J'u~|luk~~Jk; buvid_fp_plain=undefined; enable_web_push=DISABLE; header_theme_version=CLOSE; hit-dyn-v2=1; SESSDATA=c36ba40d%2C1725975220%2C99856%2A31CjAwRkyCDPlLFHgfURROm7uwii-uUvraMeyRJbcpYjdUEdQT6zMinATK-6MTtSMjwlQSVjRMU1RQV2hNblVjZks0bjdQa3pKUnhuTXJMTFQyWEpORmw1ME56N2NHaTNHbnNzekdfd3M5bmZSbk4yZVh1S2JhQkZ1dTFqQlVhcUFRSUd3S3IyS3dnIIEC; bili_jct=c1a604c10db5427ce6e5a0e372a36753; sid=68wvz15r; CURRENT_QUALITY=80; FEED_LIVE_VERSION=V_WATCHLATER_PIP_WINDOW3; bsource=search_google; home_feed_column=5; PVID=1; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTQzMDA2NzgsImlhdCI6MTcxNDA0MTQxOCwicGx0IjotMX0.tKKwXSZG1R-YG_p8sNOKgpNIywVlAP_UZzGqgHbSmRQ; bili_ticket_expires=1714300618; CURRENT_FNVAL=4048; browser_resolution=1522-687; bp_video_offset_4608012=924315599641772041; fingerprint=4d67f4c4fdca80fc3aad187d79edf82b; buvid_fp=e18902549e7a25b9c0d572cbd00095e5; b_lsid=591DF7F7_18F191F371C")
                .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                // 使用 FastJSON 解析 JSON 字符串为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonData);

                // 将 JSONObject 转换为 Java 对象
                InfoResp resp = jsonObject.toJavaObject(InfoResp.class);
                return resp;

            } else {
                System.out.println("Network request failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new InfoResp();
    }
}
