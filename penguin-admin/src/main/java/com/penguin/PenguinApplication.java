package com.penguin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * git@github.com:what232-star/qie-front.git  前端仓库
 * git@github.com:what232-star/qie-behind.git 后端仓库
 *
 *
 * cd D:\Redis\Redis-x64-3.2.100
 * redis-server.exe redis.windows.conf
 * cd D:\404_qie\Penguin
 *
 * @author ruoyi
 */
@SpringBootApplication
@MapperScan("com.penguin.mind.mapper")


public class PenguinApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(PenguinApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  企鹅启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
