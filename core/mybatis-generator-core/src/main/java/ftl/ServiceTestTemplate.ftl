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

import com.ayhuli.service.base.test.DefaultTest;
import com.ayhuli.parent.base.page.Page;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ${mapperPackage};
import ${dtoPackage};
import ${servicePackage};

import java.util.Date;
import java.util.List;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 17:15
 * @Modified Update By:
 */
public class ${className} extends DefaultTest  {

    @Autowired
    private ${serviceClassName} ${serviceLessClassName};

    @Test
    public void testGet(){
        ${boClazz} ${boClazzLess} = ${serviceLessClassName}.get(1L);
        System.out.println(JSON.toJSONString(${boClazzLess}));
    }

    @Test
    public void testInsertSelective() {
        ${boClazz} ${boClazzLess} = new ${boClazz}();
        ${boClazzLess}.preInsert(1L, new Date().getTime());
        ${boClazzLess} = ${serviceLessClassName}.insertSelective(${boClazzLess});
        System.out.println(JSON.toJSONString(${boClazzLess}));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        ${boClazz} ${boClazzLess} = new ${boClazz}();
        ${boClazzLess}.setId(1L);
        ${boClazzLess}.preUpdate(new Date().getTime());
        ${serviceLessClassName}.updateByPrimaryKeySelective(${boClazzLess});
    }

    @Test
    public void testDeleteByPrimaryKey() {
        ${boClazz} ${boClazzLess} = new ${boClazz}();
        ${boClazzLess}.setId(1L);
        ${boClazzLess}.preUpdate(new Date().getTime());
        ${serviceLessClassName}.deleteByPrimaryKey(${boClazzLess});
    }

    @Test
    public void testFindList(){
        ${boClazz}Dto dto = new ${boClazz}Dto();
        List<${boClazz}Dto> list = ${serviceLessClassName}.findList(dto);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testFindByEntity() {
        ${boClazz}Dto dto = new ${boClazz}Dto();
        Page<${boClazz}Dto> page = new Page<${boClazz}Dto>();
        Page<${boClazz}Dto> ${boClazzLess}Page = ${serviceLessClassName}.findPage(page, dto);
        System.out.println(JSON.toJSONString(${boClazzLess}Page));
    }
}