package com.ruoyi.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动酒吧小程序程序后端
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BarAppletApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarAppletApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  酒吧小程序后端启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }

}