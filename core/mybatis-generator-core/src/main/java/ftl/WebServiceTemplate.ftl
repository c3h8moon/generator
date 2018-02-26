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
import ${dtoPackage};
import ${facadePackage}.${boClazz}Facade;
import com.ayhuli.web.manage.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${boClazz}Service
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 17:15
 * @Modified Update By:
 */
@Service
public class ${boClazz}Service {

    @Autowired
    private ${boClazz}Facade ${boClazzLess}Facade;
    
    public ${boClazz}Dto get(Long id) {
        return ${boClazzLess}Facade.getByID(id);
    }
    
    public List<${boClazz}Dto> findList(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        return ${boClazzLess}Facade.findList(${boClazzLess}Dto);
    }

    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page, ${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        return ${boClazzLess}Facade.findPage(page, ${boClazzLess}Dto);
    }

    public void save(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        if (${boClazzLess}Dto.getId() == null) {
            ${boClazzLess}Facade.insert(${boClazzLess}Dto);
        } else {
            ${boClazzLess}Facade.update(${boClazzLess}Dto);
        }
    }

    public void delete(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        ${boClazzLess}Facade.delete(${boClazzLess}Dto);
    }
}