package com.wind;

import com.wind.annotation.Valid;
import com.wind.utils.ParamValidUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * Main
 *
 * @author qianchun
 * @date 17/12/11
 **/
public class Main {
    Test test = new Test();

    public static void main(String[] args) {
        try {
            Main mainObj = new Main();
            System.out.println(ParamValidUtil.validate(mainObj.test));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Test {
        private long id;

        @Valid(minLen = 10, maxLen = 100)
        private String name;

//        @Valid(in = {"0","1"})
        @Valid(in = {0,1})
        private int status;

        @Valid(bs = "bidChannel")
        private String bidChannel;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
