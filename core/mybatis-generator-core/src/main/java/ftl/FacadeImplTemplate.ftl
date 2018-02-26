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

import com.ayhuli.parent.base.page.Page;
import com.ayhuli.parent.base.stereotype.AsynTransactional;
import com.ayhuli.parent.base.utils.CheckUtil;
import com.ayhuli.service.base.exception.ServiceError;
import ${mapperPackage};
import ${facadePackage};
import ${servicePackage};
import ${dtoPackage};
import ${convertPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/23 15:34
 * @Modified Update By:
 */
@Service("${boClazzLess}Facade")
public class ${className} implements ${boClazz}Facade {

    @Autowired
    private ${serviceClassName} ${serviceLessClassName};
    @Autowired
    private ${boClazz}Convert ${boClazzLess}Convert;

    @Override
    public ${boClazz}Dto getByID(Long primaryKey) {
        CheckUtil.check(primaryKey == null, ServiceError.ERROR_ENTITY_NULL);
        return ${serviceLessClassName}.getByDto(primaryKey);
    }

    @Override
    @AsynTransactional
    public void insert(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.insertSelective(${boClazzLess});
    }

    @Override
    @AsynTransactional
    public void update(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.updateByPrimaryKeySelective(${boClazzLess});
    }

    @Override
    @AsynTransactional
    public void delete(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.deleteByPrimaryKey(${boClazzLess});
    }

    @Override
    public List<${boClazz}Dto> findList(${boClazz}Dto dto) {
        return ${serviceLessClassName}.findList(dto);
    }

    @Override
    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page, ${boClazz}Dto dto) {
        return ${boClazzLess}Service.findPage(page, dto);
    }
}