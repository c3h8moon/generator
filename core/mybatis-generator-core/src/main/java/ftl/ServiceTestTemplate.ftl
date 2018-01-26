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
* @Company: 2017-2017 备胎科技
* @FileName: Test
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