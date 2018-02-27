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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: ServiceTemplateEntity
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 15:18
 * @Modified Update By:
 */
public class FacadeTemplateEntity {
    private String templatePackage;//包名
//    private String boPackage;//所需要导入的model包
    private String mapperPackage;//所需要导入的mapper包
    private String className;//生成的Service类名
    private String classNameLess; //生成的Service类名首字母小写
    private String mapperType;//对应的Mapper类
    private String mapperName;//Mapper类的实例对象名
    private String boClazz;//对应的model类
    private String boClazzLess;//对应的model类首字母小写
    private String projectTargetPackage;//Service生成的目标工程包
    private String lessClass;//Service生成的目标工程包

    private String boClassName;
    private String boLessClassName;
    private String boPackage;

    private String formatDate;

    public String getFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        formatDate = sdf.format(new Date());
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getBoClassName() {
        return boClassName;
    }

    public void setBoClassName(String boClassName) {
        this.boClassName = boClassName;
    }

    public String getBoLessClassName() {
        return boLessClassName;
    }

    public void setBoLessClassName(String boLessClassName) {
        this.boLessClassName = boLessClassName;
    }

    public String getLessClass() {
        return lessClass;
    }

    public void setLessClass(String lessClass) {
        this.lessClass = lessClass;
    }

    public String getBoClazzLess() {
        return boClazzLess;
    }

    public String getClassNameLess() {
        return classNameLess;
    }

    public void setClassNameLess(String classNameLess) {
        this.classNameLess = classNameLess;
    }

    public void setBoClazzLess(String boClazzLess) {
        this.boClazzLess = boClazzLess;
    }

    public String getTemplatePackage() {
        return templatePackage;
    }

    public void setTemplatePackage(String templatePackage) {
        this.templatePackage = templatePackage;
    }

    public String getBoPackage() {
        return boPackage;
    }

    public void setBoPackage(String boPackage) {
        this.boPackage = boPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMapperType() {
        return mapperType;
    }

    public void setMapperType(String mapperType) {
        this.mapperType = mapperType;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getBoClazz() {
        return boClazz;
    }

    public void setBoClazz(String boClazz) {
        this.boClazz = boClazz;
    }

    public String getProjectTargetPackage() {
        return projectTargetPackage;
    }

    public void setProjectTargetPackage(String projectTargetPackage) {
        this.projectTargetPackage = projectTargetPackage;
    }
}
