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

import ${facadePackage};
import ${boPackage};
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @company: 备胎好车
 * @fileName: ${boClazz}Convert
 * @desctiption:
 * @author:
 * @date: Created by ${formatDate}
 * @modified Update By:
 */
@Service
public class ${className} implements ${boClazz}Domain  {

    @Resource
    private ${boClazz}NativeDomain ${boClazzLess}NativeDomain;

    @Override
    public ${boClazz}Bo selectByPrimaryKey(Integer primaryKey) {
        return ${boClazzLess}NativeDomain.selectByPrimaryKey(primaryKey);
    }

    @Override
    public void insert(${boClazz}Bo ${boClazzLess}Bo) {
        ${boClazzLess}NativeDomain.insert(${boClazzLess}Bo);
    }

    @Override
    public void update(${boClazz}Bo ${boClazzLess}Bo) {
        ${boClazzLess}NativeDomain.update(${boClazzLess}Bo);
    }

    @Override
    public void delete(Integer primaryKey) {
        ${boClazzLess}NativeDomain.delete(primaryKey);
    }

    @Override
    public List<${boClazz}Bo> findList(${boClazz}Bo ${boClazzLess}Bo) {
        return ${boClazzLess}NativeDomain.findList(${boClazzLess}Bo);
    }
}