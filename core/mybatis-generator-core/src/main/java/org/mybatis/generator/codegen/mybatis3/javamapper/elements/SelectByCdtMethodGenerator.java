package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: SelectByCdtMethodGenerator
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 13:34
 * @Modified Update By:
 */
public class SelectByCdtMethodGenerator extends AbstractJavaMapperMethodGenerator {

    private boolean isSimple;

    public SelectByCdtMethodGenerator(boolean isSimple) {
        super();
        this.isSimple = isSimple;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance().getNewListInstance();
        returnType.addTypeArgument(parameterType);
        method.setReturnType(FullyQualifiedJavaType.getNewListInstance());
        importedTypes.add(returnType);
        importedTypes.add(parameterType);

        method.setName(introspectedTable.getSelectByCdtStatementId());

        method.addParameter(new Parameter(parameterType, "record")); //$NON-NLS-1$

        addMapperAnnotations(interfaze, method);

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(
                method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }
}
