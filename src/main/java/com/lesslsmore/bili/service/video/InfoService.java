package com.lesslsmore.bili.service.video;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.mapper.InfoPagesExtMapper;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.user.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static com.lesslsmore.bili.common.API.getVideoInfo;
import static com.lesslsmore.bili.common.Utils.resp2infoPagesExts;

@Service
public class InfoService {
    @Autowired
    private InfoPagesExtService infoPagesExtService;
    @Autowired
    private InfoPagesExtMapper infoPagesExtMapper;
    @Autowired
    private SpaceService spaceService;
    @Autowired
    private ExecutorService executorService;


    public int saveVideoInfo(String bvid) {
        InfoResp resp = getVideoInfo(bvid);
        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        boolean saved = infoPagesExtService.saveOrUpdateBatch(infoPagesExts);
        return infoPagesExts.size();
    }

    public int saveVideoInfos(List<String> bvids) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Integer>> allSize = new ArrayList<>();
        for (String bvid : bvids) {
            CompletableFuture<Integer> futureVideoInfoSize = CompletableFuture.supplyAsync(() -> saveVideoInfo(bvid), executorService);
            allSize.add(futureVideoInfoSize);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(allSize.toArray(new CompletableFuture[allSize.size()]));
        allOf.get();
        int size = 0;
        for (CompletableFuture<Integer> futureVideoInfoSize : allSize) {
            Integer videoInfoSize = futureVideoInfoSize.get();
            size += videoInfoSize;
        }
        return size;
    }

    public com.github.pagehelper.Page<InfoPagesExt> getVideoInfos(String param, Integer pageNum, Integer pageSize) {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);

        com.github.pagehelper.Page<InfoPagesExt> page = PageHelper.startPage(1, 10);
        List<InfoPagesExt> list = infoPagesExtService.list(queryWrapper);
//        int page = 1;
//        int size = 10;
//
//        Page pageSize = new Page(page, size);
//
//        pageSize.setRecords(list);
//        pageSize.getRecords();
        return page;
    }

    public Page list(Page page, ListReq req) {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        if (!req.getName().equals("")) {
            queryWrapper.eq("name", req.getName());
        }
        if (!req.getMid().equals("")) {
            queryWrapper.eq("mid", req.getMid());
        }
        if (!req.getBvid().equals("")) {
            queryWrapper.eq("bvid", req.getBvid());
        }
        if (!req.getPart().equals("")) {
            queryWrapper.like("part", req.getPart());
        }
//        Page page1 = infoPagesExtMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        return infoPagesExtService.page(page, queryWrapper);
    }

    public List<InfoPagesExt> getVideoInfos() {
        List<InfoPagesExt> list = infoPagesExtService.list();
        return list;
    }
}
