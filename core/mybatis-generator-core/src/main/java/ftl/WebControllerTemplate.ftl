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
import com.ayhuli.web.manage.common.config.Global;
import com.ayhuli.web.manage.common.utils.StringUtils;
import com.ayhuli.web.manage.common.web.BaseController;
import ${servicePackage}.${boClazz}Service;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${boClazz}Controller
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 17:15
 * @Modified Update By:
 */
@Controller
@RequestMapping(value = "${r"${adminPath}"}/${urlPrefix}")
public class ${boClazz}Controller extends BaseController {

    @Autowired
    private ${boClazz}Service ${boClazzLess}Service;
    
    @ModelAttribute
    public ${boClazz}Dto get(@RequestParam(required=false) String id) {
        ${boClazz}Dto entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = ${boClazzLess}Service.get(Long.valueOf(id));
        }
        if (entity == null){
            entity = new ${boClazz}Dto();
        }
        return entity;
    }
    
    @RequiresPermissions("${permissionPrefix}:view")
    @RequestMapping(value = {"list", ""})
    public String list (${boClazz}Dto ${boClazzLess}Dto, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<${boClazz}Dto> page = ${boClazzLess}Service.findPage(new Page<>(request, response), ${boClazzLess}Dto);
        model.addAttribute("page", page);
        return "${lastPackageName}/${viewPrefix}List";
    }
    
    @RequiresPermissions("${permissionPrefix}:view")
    @RequestMapping(value = "form")
    public String form (${boClazz}Dto ${boClazzLess}Dto, Model model) {
        model.addAttribute("${boClazzLess}Dto", ${boClazzLess}Dto);
        return "${lastPackageName}/${viewPrefix}Form";
    }
    
    @RequiresPermissions("${permissionPrefix}:edit")
    @RequestMapping(value = "save")
    public String save (${boClazz}Dto ${boClazzLess}Dto, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, ${boClazzLess}Dto)){
            return form(${boClazzLess}Dto, model);
        }
        ${boClazzLess}Service.save(${boClazzLess}Dto);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:"+Global.getAdminPath()+"/${viewPrefix}/?repage";
    }
    
    @RequiresPermissions("${permissionPrefix}:edit")
    @RequestMapping(value = "delete")
    public String delete (${boClazz}Dto ${boClazzLess}Dto, RedirectAttributes redirectAttributes) {
        ${boClazzLess}Service.delete(${boClazzLess}Dto);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:"+Global.getAdminPath()+"/${viewPrefix}/?repage";
    }
}