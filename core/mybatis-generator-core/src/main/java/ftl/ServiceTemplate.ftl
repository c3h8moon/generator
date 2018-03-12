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

import java.util.List;

import com.btjf.common.page.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${mapperPackage};
import ${dtoPackage};
import ${boPackage};
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @company: 备胎好车
 * @fileName: ${className}
 * @desctiption:
 * @author:
 * @date: Created by ${formatDate}
 * @modified Update By:
 */
@Service
public class ${className} {

    @Resource
    private ${boClazz}Mapper ${boClazzLess}Mapper;

    public void insertSelective(${boClazz} ${boClazzLess}){
        ${boClazzLess}Mapper.insertSelective(${boClazzLess});
    }

    public void updateByPrimaryKeySelective(${boClazz} ${boClazzLess}) {
        ${boClazzLess}Mapper.updateByPrimaryKeySelective(${boClazzLess});
    }

    public void deleteByPrimaryKey(${boClazz} ${boClazzLess}) {
        ${boClazzLess}.setIsDeleted(true);
        ${boClazzLess}Mapper.updateByPrimaryKeySelective(${boClazzLess});
    }

    public ${boClazz} selectByPrimaryKey(Integer primaryKey){
        return ${boClazzLess}Mapper.selectByPrimaryKey(primaryKey);
    }

    public List<${boClazz}> findList(${boClazz} ${boClazzLess}){
        ${boClazz}Example example = new ${boClazz}Example();
        ${boClazz}Example.Criteria criteria = example.createCriteria();
        /**
        * TODO 添加查询的参数
        * ex: criteria.addxxx
        */
        return ${boClazzLess}Mapper.selectByExample(example);
    }

    public Page<${boClazz}> findPage(${boClazz} ${boClazzLess}, Integer currentPage, Integer pageSize){
        PageHelper.startPage(currentPage, pageSize);
        ${boClazz}Example example = new ${boClazz}Example();
        ${boClazz}Example.Criteria criteria = example.createCriteria();
        /**
        * TODO 添加查询的参数
        * ex: criteria.addxxx
        */
        example.setOrderByClause("FID desc");
        List<${boClazz}> list = ${boClazzLess}Mapper.selectByExample(example);
        PageInfo<${boClazz}> rs = new PageInfo<>(list);
        return new Page<>(rs);
    }
}