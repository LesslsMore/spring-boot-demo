package com.lesslsmore.bili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lesslsmore.bili.entity.video.InfoPages;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.mapper.InfoPagesExtMapper;
import com.lesslsmore.bili.mapper.InfoPagesMapper;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.InfoPagesService;
import org.springframework.stereotype.Service;

@Service
public class InfoPagesExtServiceImpl extends ServiceImpl<InfoPagesExtMapper, InfoPagesExt> implements InfoPagesExtService {
}

