package com.qf;


import com.qf.utils.FileUntil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: sin
 * @Date: 2023/6/4 - 06 - 04 - 16:18
 * @Description: com.qf
 * @version: 1.0
 */
@Slf4j
public class Test {

    @org.junit.Test
    public void A() {
        log.info("A");
    }
    @org.junit.Test
    public void B() {
        String txt = "放 炮,计 算 机";
        String s = FileUntil.filterSensitivityWord(txt, '*');
        log.info(s);
    }

}
