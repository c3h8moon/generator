package template;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: Test
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 16:53
 * @Modified Update By:
 */
public class Test {

    private Configuration cfg;            //模版配置对象

    public void init() throws Exception {
        //初始化FreeMarker配置
        //创建一个Configuration实例
        cfg = new Configuration();
        //设置FreeMarker的模版文件夹位置
        cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
    }

    public void process() throws Exception {
        //构造填充数据的Map
        Map map = new HashMap();
        map.put("user", "lavasoft");
        map.put("url", "http://www.baidu.com/");
        map.put("name", "百度");
        //创建模版对象
        Template t = cfg.getTemplate("ServiceTemplate.ftl");
        //在模版上执行插值操作，并输出到制定的输出流中
        t.process(map, new OutputStreamWriter(System.out));
    }

    public static void main(String[] args) throws Exception {
        Test hf = new Test();
        hf.init();
        hf.process();
    }

}
