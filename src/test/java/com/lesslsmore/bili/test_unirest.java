package com.lesslsmore.bili;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

public class test_unirest {
    @Test
    public void test() {
//        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://api.bilibili.com/x/space/wbi/arc/search?mid=302417610&pn=13&wts=1703320140&w_rid=d7b0e2a4d09c1a9deba96924e9a3d2c3")
                .header("authority", "api.bilibili.com")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("accept-language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7")
                .header("cache-control", "no-cache")
                .header("cookie", "buvid3=E7B608C4-2607-2805-A7FD-5F95433B189B95297infoc; b_nut=1678886695; _uuid=D41488E4-C964-123C-EF68-CC588C291066588075infoc; DedeUserID=4608012; DedeUserID__ckMd5=2fa848b2c0514372; buvid4=B1868396-F80D-A710-C591-3D54FD85D84597291-023031521-ADgaJkpv35Zj%2BH4loF2sLg%3D%3D; buvid_fp_plain=undefined; rpdid=|(J|)RYlll)m0J'uY~)))Y~km; nostalgia_conf=-1; CURRENT_PID=b7304440-cd68-11ed-9c5e-2bd24ba66c55; i-wanna-go-back=-1; b_ut=5; header_theme_version=CLOSE; FEED_LIVE_VERSION=V8; LIVE_BUVID=AUTO2916815669584155; hit-dyn-v2=1; share_source_origin=COPY; bp_t_offset_4608012=795146086246252700; hit-new-style-dyn=1; dy_spec_agreed=1; CURRENT_BLACKGAP=0; is-2022-channel=1; CURRENT_FNVAL=4048; enable_web_push=DISABLE; bsource=search_baidu; CURRENT_QUALITY=80; fingerprint=1261229bb10b938b99c622ee99f29774; home_feed_column=5; buvid_fp=1261229bb10b938b99c622ee99f29774; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDMzMzMwMjMsImlhdCI6MTcwMzA3Mzc2MywicGx0IjotMX0.EynOoLjLfWy25SERFDPWxhy3MPv37JBrrgBw7NfDWMQ; bili_ticket_expires=1703332963; SESSDATA=5f9680e3%2C1718717353%2C82d24%2Ac1CjAuAHCvysKKJEQxJ9WgMAF6BuDPANYqn0IwnUxdL0rwE-RqBLUjWXvEg_6CreE_KjwSVmptcmRXdlhVZWhGQmlVZzJuWmxscjR2X0ViajJBTk12Z2RkdVlaTnJoS0pXY1FJSHNvT0tObXplUkdudDU1ekJvVVNGSlNhQlVNMGVMaWJvUFR1Q19BIIEC; bili_jct=e094b02d2a322a9c0bddfdad3cdc0b38; sid=7c0uuke9; PVID=3; bp_video_offset_4608012=878142248778203143; b_lsid=7C99A3DF_18C959D98AE; browser_resolution=1536-695")
                .header("pragma", "no-cache")
                .header("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "document")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-site", "none")
                .header("sec-fetch-user", "?1")
                .header("upgrade-insecure-requests", "1")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .asString();

        System.out.println(response);

    }
}
