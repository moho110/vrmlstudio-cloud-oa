package com.vrmlstudio.hr;

import com.vrmlstudio.common.security.annotation.EnableCustomConfig;
import com.vrmlstudio.common.security.annotation.EnableRyFeignClients;
import com.vrmlstudio.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成
 * 
 * @author VrmlStudio
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class VrmlStudioHrApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(VrmlStudioHrApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  人力资源模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
