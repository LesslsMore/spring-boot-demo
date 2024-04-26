package com.lesslsmore.bili.service.video;

import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.user.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lesslsmore.bili.common.API.getVideoInfo;
import static com.lesslsmore.bili.common.Utils.resp2infoPagesExts;

@Service
public class InfoService {
    @Autowired
    public InfoPagesExtService infoPagesExtService;
    @Autowired
    public SpaceService spaceService;

    public int saveVideoInfo(String bvid) {
        InfoResp resp = getVideoInfo(bvid);
        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        boolean saved = infoPagesExtService.saveOrUpdateBatch(infoPagesExts);
        return infoPagesExts.size();
    }

    public int saveVideoInfos() {
        List<String> bvids = spaceService.getBvids();
        for (String bvid: bvids) {
            InfoResp resp = getVideoInfo(bvid);
            List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
            boolean saved = infoPagesExtService.saveOrUpdateBatch(infoPagesExts);
        }
        return bvids.size();
    }
}
