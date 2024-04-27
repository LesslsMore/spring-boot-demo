package com.lesslsmore.bili;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.lesslsmore.bili.entity.video.InfoPagesExt;

import static com.lesslsmore.bili.common.Utils.getNames;

public class test {
    @Test
    public void test() throws IOException {

        Field[] field = InfoPagesExt.class.getDeclaredFields();
        // 遍历所有属性
        for (int j = 0; j < field.length; j++) {
            // 获取属性的名字
            String name = field[j].getName();
            System.out.println(name);
        }
    }
    @Test
    public void test2() throws IOException {
        List<String> names = getNames(InfoPagesExt.class);
        System.out.println(names);
    }

    @Test
    public void test3() throws IOException {

    }

}
