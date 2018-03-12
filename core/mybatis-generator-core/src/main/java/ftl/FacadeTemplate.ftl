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
import ${boPackage};

import java.util.List;

/**
 * @company: 备胎好车
 * @fileName: ${className}
 * @desctiption:
 * @author:
 * @date: Created by ${formatDate}
 * @modified Update By:
 */
public interface ${className} {

    /**   方法定义如下： 【方法描述】   **/

    /**
     * 功能描述:〈根据主键获取单条数据〉
     * 应用范围:〈ALL〉
     *
     * @param primaryKey
     * @return ${boClazz}Bo
     * @date: Created by ${formatDate}
     * @author:
     */
    public ${boClazz}Bo selectByPrimaryKey(Integer primaryKey);

    /**
     * 功能描述:〈保存数据〉
     * 应用范围:〈ALL〉
     *
     * @param ${boClazzLess}Bo
     * @return void
     * @date: Created by ${formatDate}
     * @author:
     */
    public void insert(${boClazz}Bo ${boClazzLess}Bo);

    /**
     * 功能描述:〈修改数据〉
     * 应用范围:〈ALL〉
     *
     * @param ${boClazzLess}Bo
     * @return void
     * @date: Created by ${formatDate}
     * @author:
     */
    public void update(${boClazz}Bo ${boClazzLess}Bo);

    /**
     * 功能描述:〈删除数据〉
     * 应用范围:〈ALL〉
     *
     * @param primaryKey
     * @return void
     * @date: Created by ${formatDate}
     * @author:
     */
    public void delete(Integer primaryKey);

    /**
     * 功能描述:〈查询所有数据--默认100条〉
     * 应用范围:〈ALL〉
     *
     * @param ${boClazzLess}Bo
     * @return List<${boClazz}Bo>
     * @date: Created by ${formatDate}
     * @author:
     */
    public List<${boClazz}Bo> findList(${boClazz}Bo ${boClazzLess}Bo);

    /**
    * 功能描述:〈查询分页数据〉
    * 应用范围:〈ALL〉
    *
    * @param ${boClazzLess}Bo
    * @return Page<${boClazz}Bo>
    * @date: Created by ${formatDate}
    * @author:
    */
    public Page<${boClazz}Bo> findPage(${boClazz}Bo ${boClazzLess}Bo, Integer currentPage, Integer pageSize);
}