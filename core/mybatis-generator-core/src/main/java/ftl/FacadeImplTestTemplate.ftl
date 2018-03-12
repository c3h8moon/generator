<#--

       Copyright 2006-2018 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

-->
package ${templatePackage};

import com.btjf.common.page.Page;
import ${facadePackage};
import ${boPackage};
import com.alibaba.fastjson.JSON;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import java.util.List;
import org.junit.Test;

/**
 * @company: 备胎好车
 * @fileName: ${className}
 * @desctiption: domain测试类
 * @author:
 * @date: Created by ${formatDate}
 * @modified Update By:
 */
public class ${className} {

    public <T> T getService(Class<T> clazz) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("${className}");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.100.131:2181");
        ReferenceConfig<T> reference = new ReferenceConfig<T>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能�?�成内存和连接泄�?
        reference.setInterface(clazz);
        reference.setVersion("2.6.9");
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setProtocol("dubbo");
        return reference.get();
    }

    @Test
    public void test_selectByPrimaryKey() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        Integer primaryKey = 1; // 测试数据
        ${boClazz}Bo bo = ${facadeLessClassName}.selectByPrimaryKey(primaryKey);
        System.out.println(JSON.toJSONString(bo));
    }

    @Test
    public void test_insert() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Bo bo = new ${boClazz}Bo();
        /**
        * TODO 添加测试的参数
        * ex: bo.setXxx();
        */
        ${facadeLessClassName}.insert(bo);
    }

    @Test
    public void test_update() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Bo bo = new ${boClazz}Bo();
        /**
        * TODO 添加测试的参数
        * ex: bo.setXxx();
        */
        ${facadeLessClassName}.update(bo);
    }

    @Test
    public void test_delete() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        Integer primaryKey = 1; // 测试数据
        ${facadeLessClassName}.delete(primaryKey);
    }

    @Test
    public void test_findList() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Bo bo = new ${boClazz}Bo();
        /**
        * TODO 添加测试的参数
        * ex: bo.setXxx();
        */
        List<${boClazz}Bo> list = ${facadeLessClassName}.findList(bo);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_findPage() {
        ${facadeClassName} ${facadeLessClassName} = getService(${facadeClassName}.class);
        ${boClazz}Bo bo = new ${boClazz}Bo();
        /**
        * TODO 添加测试的参数
        * ex: bo.setXxx();
        */
        Page<${boClazz}Bo> page = ${facadeLessClassName}.findPage(bo, 1, 10);
        if (page != null && page.getRows() != null && page.getRows().size() > 0) {
            System.out.println(JSON.toJSONString(page.getRows()));
        }
    }
}