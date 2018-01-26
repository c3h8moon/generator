package template;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: ServiceTemplateEntity
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 15:18
 * @Modified Update By:
 */
public class DtoTemplateEntity {
    private String templatePackage;//包名
    private String boPackage;//所需要导入的model包
    private String mapperPackage;//所需要导入的mapper包
    private String className;//生成的Service类名
    private String classNameLess; //生成的Service类名首字母小写
    private String mapperType;//对应的Mapper类
    private String mapperName;//Mapper类的实例对象名
    private String boClazz;//对应的model类
    private String boClazzLess;//对应的model类首字母小写
    private String projectTargetPackage;//Service生成的目标工程包
    private String lessClass;//Service生成的目标工程包
    private String fields;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
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
