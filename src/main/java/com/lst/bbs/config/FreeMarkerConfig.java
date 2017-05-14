package com.lst.bbs.config;


import freemarker.template.Configuration;
import freemarker.template.Template;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by LiuSitong on 2017/5/8.
 */
public class FreeMarkerConfig  {
    private static Configuration configuration;

    /**
     * 建立FreeMarker的配置对象 ，并且告诉他的目录在哪（templates）
     * @return configuration
     */

    private static  Configuration builtConfiguration(){
        if (configuration == null){
            configuration = new Configuration(Configuration.VERSION_2_3_26);
            String path = FreeMarkerConfig.class.getResource("/").getPath();
            System.out.println(path);

            File file = new File(path+File.separator+"templates");

            configuration.setDefaultEncoding("utf-8");

            try {
                configuration.setDirectoryForTemplateLoading(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return configuration;
    }

    /**
     * 建立模板对象
     * ftlName:FreeMarker模板名字
     * @return
     */
    public static Template getTemplate(String ftlName){

        try {
            return builtConfiguration().getTemplate(ftlName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 建立FreeMarker的传值
     * @param response  响应的对象
     * @param target 想要转向的页面
     * @param vmap 向前台传值的map
     */
    public static void forward(
            HttpServletResponse response,
            String target,
            HashMap vmap
    ){

        Template template = getTemplate(target);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = null;

        try {
            out = response.getWriter();
            template.process(vmap,out);

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }


}
