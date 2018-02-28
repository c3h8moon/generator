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

import com.btjf.application.util.XaResult;
import com.btjf.business.common.exception.BusinessException;
import ${dtoPackage};
import ${boPackage};
import ${servicePackage}.${boClazz}Domain;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Company: 备胎好车
 * @FileName: ${boClazz}Controller
 * @Desctiption:
 * @Author:
 * @Date: Created by ${formatDate}
 * @Modified Update By:
 */
@Api(value = "${boClazz}", description = "")
@RestController("${boClazzLess}Controller")
@RequestMapping("/m/business/${lessClass}/")
public class ${boClazz}Controller extends PersonalBaseController {

    @Resource
    private ${boClazz}Domain ${boClazzLess}Domain;

    @ApiOperation(value = "20101:查询接口", notes = "根据主键查询单条数据")
    @RequestMapping(value = "select/key/1_0_0_0", method = RequestMethod.GET)
    public XaResult<${boClazz}Vo> selectByPrimaryKey(@ApiParam("主键,字段名:primaryKey") Integer primaryKey) throws BusinessException {
        ${boClazz}Bo ${boClazzLess}Bo = ${boClazzLess}Domain.selectByPrimaryKey(primaryKey);
        return XaResult.success(new ${boClazz}Vo(${boClazzLess}Bo));
    }

    @ApiOperation(value = "20102:新增接口", notes = "新增记录")
    @RequestMapping(value = "operation/insert/1_0_0_0", method = RequestMethod.POST)
    public XaResult<Integer> insert(
            @ApiParam("主键,字段名:primaryKey") Integer primaryKey
    ) throws BusinessException {
        ${boClazz}Bo ${boClazzLess}Bo = new ${boClazz}Bo();
        /**
        * TODO 组装新增数据
        * ex: ${boClazzLess}Bo.setXxx
        */
        ${boClazzLess}Domain.insert(${boClazzLess}Bo);
        return XaResult.success();
    }

    @ApiOperation(value = "20103:修改接口", notes = "修改记录")
    @RequestMapping(value = "operation/update/1_0_0_0", method = RequestMethod.POST)
    public XaResult<Integer> update(
        @ApiParam("主键,字段名:primaryKey") Integer primaryKey
    ) throws BusinessException {
        ${boClazz}Bo ${boClazzLess}Bo = new ${boClazz}Bo();
        /**
        * TODO 组装修改数据
        * ex: ${boClazzLess}Bo.setXxx
        */
        ${boClazzLess}Domain.update(${boClazzLess}Bo);
        return XaResult.success();
    }

    @ApiOperation(value = "20104:删除接口", notes = "删除记录")
    @RequestMapping(value = "operation/delete/1_0_0_0", method = RequestMethod.POST)
    public XaResult<Integer> delete(@ApiParam("主键,字段名:primaryKey") Integer primaryKey) throws BusinessException {
        ${boClazzLess}Domain.delete(primaryKey);
        return XaResult.success();
    }

    @ApiOperation(value = "20105:查询列表接口", notes = "根据输入条件查询列表数据")
    @RequestMapping(value = "find/params/1_0_0_0", method = RequestMethod.GET)
    public XaResult<${boClazz}Vo> selectByPrimaryKey(
                    @ApiParam("主键,字段名:primaryKey") Integer primaryKey
    ) throws BusinessException {
        ${boClazz}Bo ${boClazzLess}Bo = new ${boClazz}Bo();
        /**
        * TODO 组装修改数据
        * ex: ${boClazzLess}Bo.setXxx
        */
        List<${boClazz}Bo> list = ${boClazzLess}Domain.findList(${boClazzLess}Bo);
        return XaResult.success(${boClazz}Vo.convertBo(list));
    }
}