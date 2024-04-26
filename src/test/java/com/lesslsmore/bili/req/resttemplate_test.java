package com.lesslsmore.bili.req;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class resttemplate_test {
    @Autowired
    public RestTemplate restTemplate;
    @Test
    public void test() {
        String url = "https://api.bilibili.com/x/space/wbi/arc/search?mid=302417610&pn=3&wts=1714048730&w_rid=3dbe2b7d3aebbd7a27ffe39d2542c8b8";
        String object = restTemplate.getForObject(url, String.class);
        System.out.println(object);
    }

    @Test
    public void test2() {
        HttpHeaders headers = new HttpHeaders();//header参数
        List<String> cookies = new ArrayList<>();
        String cookie = "buvid3=DF9EC251-7549-FE46-6845-F4A757B5FC0E16707infoc; b_nut=1705127716; _uuid=1094581059-4785-D599-F8AD-26C10988A9AE118001infoc; buvid4=887FA9BF-0BD8-F1AB-DD07-E994BA8861CD17854-024011306-DUtoXy43xsqc%2BUnkEqXf5w%3D%3D; DedeUserID=4608012; DedeUserID__ckMd5=2fa848b2c0514372; rpdid=|(u))kY|mmlR0J'u~|luk~~Jk; buvid_fp_plain=undefined; enable_web_push=DISABLE; header_theme_version=CLOSE; hit-dyn-v2=1; SESSDATA=c36ba40d%2C1725975220%2C99856%2A31CjAwRkyCDPlLFHgfURROm7uwii-uUvraMeyRJbcpYjdUEdQT6zMinATK-6MTtSMjwlQSVjRMU1RQV2hNblVjZks0bjdQa3pKUnhuTXJMTFQyWEpORmw1ME56N2NHaTNHbnNzekdfd3M5bmZSbk4yZVh1S2JhQkZ1dTFqQlVhcUFRSUd3S3IyS3dnIIEC; bili_jct=c1a604c10db5427ce6e5a0e372a36753; sid=68wvz15r; CURRENT_QUALITY=80; fingerprint=c7cde790db02bfe48cf37218692d0ae6; FEED_LIVE_VERSION=V_WATCHLATER_PIP_WINDOW3; bsource=search_google; home_feed_column=5; buvid_fp=fd6c9e5e2f197ff0d8d5c705a0d45999; PVID=1; browser_resolution=1536-695; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTQzMDA2NzgsImlhdCI6MTcxNDA0MTQxOCwicGx0IjotMX0.tKKwXSZG1R-YG_p8sNOKgpNIywVlAP_UZzGqgHbSmRQ; bili_ticket_expires=1714300618; CURRENT_FNVAL=4048; bp_video_offset_4608012=924292758994157590; b_lsid=698DECE5_18F152D5AED";
        String[] strings = cookie.split("; ");
        for (String string : strings) {
            cookies.add(string);
        }



// header设置
//        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
// cookie设置
//        headers.put(HttpHeaders.COOKIE, cookies);
        headers.put("cookie", Arrays.asList(cookie));
//        System.out.println(headers);

        HttpEntity<String> httpEntity = new HttpEntity(headers);
        String url = "https://api.bilibili.com/x/space/wbi/arc/search?mid=302417610&pn=3&wts=1714048730&w_rid=3dbe2b7d3aebbd7a27ffe39d2542c8b8";

//        String url = String.format(Constant.DOUBAN_HOT_MOVIE_URL, hotCount);
//        log.info("url: {}", url);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);

        System.out.println(responseEntity.getBody().toString());
    }
}
