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
import ${mapperPackage};

import java.util.List;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/23 15:34
 * @Modified Update By:
 */
public interface ${className} {

    /**   方法定义如下： 【方法描述】   **/

    /**
     * 功能描述:〈根据主键获取单条数据〉
     * 应用范围:〈ALL〉
     *
     * @Param primaryKey
     * @Return ${boClazz}Dto
     * @Date: 2017/9/23 15:00
     * @Author:chenye
     */
    public ${boClazz}Dto getByID(Long primaryKey);

    /**
     * 功能描述:〈保存数据〉
     * 应用范围:〈ALL〉
     *
     * @Param dto
     * @Return void
     * @Date: 2017/9/23 15:01
     * @Author:chenye
     */
    @AsynTransactional
    public void insert(${boClazz}Dto dto);

    /**
     * 功能描述:〈修改数据〉
     * 应用范围:〈ALL〉
     *
     * @Param dto
     * @Return void
     * @Date: 2017/9/23 15:01
     * @Author:chenye
     */
    @AsynTransactional
    public void update(${boClazz}Dto dto);

    /**
     * 功能描述:〈删除数据〉
     * 应用范围:〈ALL〉
     *
     * @Param dto
     * @Return void
     * @Date: 2017/9/23 15:01
     * @Author:chenye
     */
    @AsynTransactional
    public void delete(${boClazz}Dto dto);

    /**
     * 功能描述:〈查询所有数据--默认100条〉
     * 应用范围:〈ALL〉
     *
     * @Param dto
     * @Return List<${boClazz}Dto>
     * @Date: 2017/9/23 15:01
     * @Author:chenye
     */
    public List<${boClazz}Dto> findList(${boClazz}Dto dto);

    /**
    * 功能描述:〈查询分页数据〉
    * 应用范围:〈ALL〉
    *
    * @Param page
    * @Param dto
    * @Return Page<${boClazz}Dto>
    * @Date: 2017/9/23 15:02
    * @Author:chenye
    */
    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page, ${boClazz}Dto dto);
}