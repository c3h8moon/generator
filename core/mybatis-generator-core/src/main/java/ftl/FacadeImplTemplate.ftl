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

import com.btjf.common.utils.BeanUtil;

import ${mapperPackage};
import ${facadePackage};
import ${servicePackage};
import ${dtoPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @company: 备胎好车
 * @fileName: ${className}
 * @desctiption:
 * @author:
 * @date: Created by ${formatDate}
 * @modified Update By:
 */
@Service("${boClazzLess}Domain")
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class ${className} implements ${boClazz}Domain {

    @Autowired
    private ${serviceClassName} ${serviceLessClassName};

    @Override
    public ${boClazz}Bo selectByPrimaryKey(Integer primaryKey) {
        ${boClazz} ${boClazzLess} = ${serviceLessClassName}.selectByPrimaryKey(primaryKey);
        if (${boClazzLess} != null) {
            return BeanUtil.convert(${boClazzLess}, ${boClazz}Bo.class);
        }
        return null;
    }

    @Override
    public void insert(${boClazz}Bo ${boClazzLess}Bo) {
        if (${boClazzLess}Bo != null) {
            ${boClazzLess}Service.insertSelective(BeanUtil.convert(${boClazzLess}Bo, ${boClazz}.class));
        }
    }

    @Override
    public void update(${boClazz}Bo ${boClazzLess}Bo) {
        if (${boClazzLess}Bo != null) {
            ${boClazzLess}Service.updateByPrimaryKeySelective(BeanUtil.convert(${boClazzLess}Bo, ${boClazz}.class));
        }
    }

    @Override
    public void delete(Integer primaryKey) {
        if (primaryKey != null) {
            ${boClazz} ${boClazzLess} = new ${boClazz}();
            ${boClazzLess}.setId(primaryKey);
            ${boClazzLess}Service.deleteByPrimaryKey(${boClazzLess});
        }
    }

    @Override
    public List<${boClazz}Bo> findList(${boClazz}Bo ${boClazzLess}Bo) {
        if (${boClazzLess}Bo != null) {
            List<${boClazz}> list = ${boClazzLess}Service.findList(BeanUtil.convert(${boClazzLess}Bo, ${boClazz}.class));
            if (list != null && list.size() > 0) {
                return BeanUtil.convertList(list, ${boClazz}Bo.class);
            } else {
                return null;
            }
        }
        return null;
    }
}