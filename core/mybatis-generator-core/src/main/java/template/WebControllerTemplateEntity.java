/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package template;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: ServiceTemplateEntity
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 15:18
 * @Modified Update By:
 */
public class WebControllerTemplateEntity {
    private String templatePackage;//包名
    private String urlPrefix;
    private String lastPackageName;
    private String viewPrefix;
    private String permissionPrefix;
    private String boClazz;//对应的model类
    private String boClazzLess;//对应的model类首字母小写
    private String boPackage;//所需要导入的model包
    private String dtoPackage;
    private String className;//生成的Service类名
    private String lessClass;//Service生成的目标工程包
    private String classNameLess; //生成的Service类名首字母小写
    private String projectTargetPackage;//Service生成的目标工程包

    private String servicePackage;

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getProjectTargetPackage() {
        return projectTargetPackage;
    }

    public void setProjectTargetPackage(String projectTargetPackage) {
        this.projectTargetPackage = projectTargetPackage;
    }

    public String getClassNameLess() {
        return classNameLess;
    }

    public void setClassNameLess(String classNameLess) {
        this.classNameLess = classNameLess;
    }

    public String getLessClass() {
        return lessClass;
    }

    public void setLessClass(String lessClass) {
        this.lessClass = lessClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTemplatePackage() {
        return templatePackage;
    }

    public void setTemplatePackage(String templatePackage) {
        this.templatePackage = templatePackage;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getLastPackageName() {
        return lastPackageName;
    }

    public void setLastPackageName(String lastPackageName) {
        this.lastPackageName = lastPackageName;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    public void setViewPrefix(String viewPrefix) {
        this.viewPrefix = viewPrefix;
    }

    public String getPermissionPrefix() {
        return permissionPrefix;
    }

    public void setPermissionPrefix(String permissionPrefix) {
        this.permissionPrefix = permissionPrefix;
    }

    public String getBoClazz() {
        return boClazz;
    }

    public void setBoClazz(String boClazz) {
        this.boClazz = boClazz;
    }

    public String getBoClazzLess() {
        return boClazzLess;
    }

    public void setBoClazzLess(String boClazzLess) {
        this.boClazzLess = boClazzLess;
    }

    public String getBoPackage() {
        return boPackage;
    }

    public void setBoPackage(String boPackage) {
        this.boPackage = boPackage;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public void setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }
}
