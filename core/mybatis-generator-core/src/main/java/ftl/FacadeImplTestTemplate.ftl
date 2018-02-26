package ${templatePackage};

import com.ayhuli.parent.base.page.Page;
import ${facadePackage};
import ${dtoPackage};
import com.alibaba.fastjson.JSON;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import java.util.List;
import org.junit.Test;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
 * @Desctiption: facade测试类
 * @Author: chenye
 * @Date: Created by 2017/9/23 15:34
 * @Modified Update By:
 */
public class ${className} {

    public <T> T getService(Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("${className}");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://59.110.241.105:2185");
        ReferenceConfig<T> reference = new ReferenceConfig<T>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能�?�成内存和连接泄�?
        reference.setInterface(clazz);
        reference.setVersion("1.0.0");
        reference.setGroup("chenye");
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setProtocol("dubbo");
        return reference.get();
    }

    @Test
    public void testGetByID_all_100() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = ${facadeLessClassName}.getByID(1L);
        System.out.println(JSON.toJSONString(dto));
    }

    @Test
    public void testInsert_all_200() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = new ${boClazz}Dto();
        ${facadeLessClassName}.insert(dto);
    }

    @Test
    public void testUpdate_all_300() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = new ${boClazz}Dto();
        ${facadeLessClassName}.update(dto);
    }

    @Test
    public void testDelete_all_400() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = new ${boClazz}Dto();
        ${facadeLessClassName}.delete(dto);
    }

    @Test
    public void testFindByAll_all_500() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = new ${boClazz}Dto();
        List<${boClazz}Dto> list = ${facadeLessClassName}.findList(dto);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testFindByEntity_all_600() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Dto dto = new ${boClazz}Dto();
        Page<${boClazz}Dto> page = new Page<${boClazz}Dto>();
        Page<${boClazz}Dto> ${boClazzLess}DtoPage = ${facadeLessClassName}.findPage(page, dto);
        System.out.println(JSON.toJSONString(${boClazzLess}DtoPage));
    }
}