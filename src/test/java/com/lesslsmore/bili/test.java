package com.lesslsmore.bili;

import com.lesslsmore.bili.entity.bili.Page;
import com.lesslsmore.bili.entity.bili.PageDO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    @Test
    public void test() throws IOException {
        List<String> ls = new ArrayList<>();
        Page page = new Page();
        if (page instanceof PageDO) {
            System.out.println("true");
        }
    }
}
