package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.Iterator;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: SelectByCdtElementGenerator
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 14:18
 * @Modified Update By:
 */
public class SelectByCdtElementGenerator extends AbstractXmlElementGenerator {
    public SelectByCdtElementGenerator() {
        super();
    }
    public static final String name = "columns";
    public static final String joins = "joins";

    public void base(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        List<IntrospectedColumn> columns;
        columns = introspectedTable.getBaseColumns();

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                name));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> iter = introspectedTable
                .getNonBLOBColumns().iterator();
        while (iter.hasNext()) {
            IntrospectedColumn i = iter.next();
            sb.append("a." + i.getActualColumnName());
            sb.append(" AS \"" + i.getJavaProperty() + "\"");
//            sb.append(MyBatis3FormattingUtilities.getSelectListPhrase(iter.next()));
            if (iter.hasNext()) {
                sb.append(", "); //$NON-NLS-1$
            }
            answer.addElement(new TextElement(sb.toString()));
            sb.setLength(0);
//            if (sb.length() > 80) {
//
//            }
        }

        if (sb.length() > 0) {
            answer.addElement(new TextElement(sb.toString()));
        }

        if (context.getPlugins().sqlMapBaseColumnListElementGenerated(
                answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    public void baseJoins(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        List<IntrospectedColumn> columns;
        columns = introspectedTable.getBaseColumns();

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                joins));

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement(""));

        if (context.getPlugins().sqlMapBaseColumnListElementGenerated(
                answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    @Override
    public void addElements(XmlElement parentElement) {
        base(parentElement);
        baseJoins(parentElement);
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", introspectedTable.getSelectByCdtStatementId())); //$NON-NLS-1$
//        if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
//            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
//                    introspectedTable.getResultMapWithBLOBsId()));
//        } else {
//            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
//                    introspectedTable.getBaseResultMapId()));
//        }

        String parameterType = introspectedTable.getRules().calculateAllFieldsClass().getFullyQualifiedName();
        String packageStr = this.context.getJavaDtoGeneratorConfiguration().getTargetPackage();
        String name = introspectedTable.getRules().calculateAllFieldsClass().getShortName();
        answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
                packageStr + "." +  name + "Dto"));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$
        if (stringHasValue(introspectedTable
                .getSelectByPrimaryKeyQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
        }
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement2());
        if (introspectedTable.hasBLOBColumns()) {
            answer.addElement(new TextElement(",")); //$NON-NLS-1$
            answer.addElement(getBlobColumnListElement());
        }

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        sb.append(" a");
        answer.addElement(new TextElement(sb.toString()));

        answer.addElement(getBaseColumnListElement3());

        XmlElement dynamicElement = new XmlElement("where"); //$NON-NLS-1$
        answer.addElement(dynamicElement);
        dynamicElement.addElement(new TextElement(" a.del_flag = #{DEL_FLAG_NORMAL}"));

        for (IntrospectedColumn introspectedColumn : introspectedTable.getBaseColumns()) {
            if ("delFlag".equals(introspectedColumn.getJavaProperty())) continue;
            if ("remarks".equals(introspectedColumn.getJavaProperty())) continue;
            if ("updateDate".equals(introspectedColumn.getJavaProperty())) continue;
            if ("updateBy".equals(introspectedColumn.getJavaProperty())) continue;
            if ("createDate".equals(introspectedColumn.getJavaProperty())) continue;
            if ("createBy".equals(introspectedColumn.getJavaProperty())) continue;
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty()); //$NON-NLS-1$
            sb.append(" != null"); //$NON-NLS-1$
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append("AND a.");
            sb.append(MyBatis3FormattingUtilities
                    .getAliasedEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities.getParameterClause(
                    introspectedColumn)); //$NON-NLS-1$
//            sb.append(',');

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        XmlElement dynamicElement1 = new XmlElement("choose"); //$NON-NLS-1$
        answer.addElement(dynamicElement1);
        XmlElement whenElement = new XmlElement("when"); //$NON-NLS-1$
        sb.setLength(0);
        sb.append("page !=null and page.orderBy != null and page.orderBy != ''"); //$NON-NLS-1$
        whenElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
        dynamicElement1.addElement(whenElement);
        sb.setLength(0);
        sb.append("ORDER BY ${page.orderBy}");
        whenElement.addElement(new TextElement(sb.toString()));

        XmlElement otherwiseElement = new XmlElement("otherwise"); //$NON-NLS-1$
        dynamicElement1.addElement(otherwiseElement);
        sb.setLength(0);
        sb.append("ORDER BY a.update_date DESC");
        otherwiseElement.addElement(new TextElement(sb.toString()));


        if (context.getPlugins()
                .sqlMapSelectByPrimaryKeyElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
            parentElement.addElement(addElement_getByDto(parentElement));
            parentElement.addElement(addElement_query(parentElement));
            parentElement.addElement(addElement_del(parentElement));
        }
    }

    protected XmlElement getBaseColumnListElement2() {
        XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
                name));
        return answer;
    }

    protected XmlElement getBaseColumnListElement3() {
        XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
                joins));
        return answer;
    }

    public XmlElement addElement_getByDto(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "getByDto")); //$NON-NLS-1$

        String parameterType = introspectedTable.getRules().calculateAllFieldsClass().getFullyQualifiedName();
//        String packageStr = this.context.getJavaDtoGeneratorConfiguration().getTargetPackage();
//        String name = introspectedTable.getRules().calculateAllFieldsClass().getShortName();
        String packageStr = this.context.getJavaDtoGeneratorConfiguration().getTargetPackage();
        String name = introspectedTable.getRules().calculateAllFieldsClass().getShortName();
        answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
                packageStr + "." +  name + "Dto"));
        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$
        if (stringHasValue(introspectedTable
                .getSelectByPrimaryKeyQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
        }
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement2());
//        if (introspectedTable.hasBLOBColumns()) {
//            answer.addElement(new TextElement(",")); //$NON-NLS-1$
//            answer.addElement(getBlobColumnListElement());
//        }

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        sb.append(" a");
        answer.addElement(new TextElement(sb.toString()));

        answer.addElement(getBaseColumnListElement3());

//        XmlElement dynamicElement = new XmlElement("where"); //$NON-NLS-1$
//        dynamicElement.addElement(new TextElement("where id = #{id,jdbcType=BIGINT}"));
        answer.addElement(new TextElement("where a.id = #{id,jdbcType=BIGINT} AND a.del_flag = '0'"));

//        for (IntrospectedColumn introspectedColumn : introspectedTable.getBaseColumns()) {
//            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
//            sb.setLength(0);
//            sb.append(introspectedColumn.getJavaProperty()); //$NON-NLS-1$
//            sb.append(" != null"); //$NON-NLS-1$
//            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
//            dynamicElement.addElement(isNotNullElement);
//
//            sb.setLength(0);
//            sb.append("and ");
//            sb.append(MyBatis3FormattingUtilities
//                    .getAliasedEscapedColumnName(introspectedColumn));
//            sb.append(" = "); //$NON-NLS-1$
//            sb.append(MyBatis3FormattingUtilities.getParameterClause(
//                    introspectedColumn)); //$NON-NLS-1$
////            sb.append(',');
//
//            isNotNullElement.addElement(new TextElement(sb.toString()));
//        }

//        if (context.getPlugins()
//                .sqlMapSelectByPrimaryKeyElementGenerated(answer,
//                        introspectedTable)) {
//            parentElement.addElement(answer);
//        }
        return answer;
    }

    public XmlElement addElement_query(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "getQueryCriteria")); //$NON-NLS-1$
        if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                    introspectedTable.getResultMapWithBLOBsId()));
        } else {
            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                    introspectedTable.getBaseResultMapId()));
        }

        String parameterType = introspectedTable.getRules().calculateAllFieldsClass().getFullyQualifiedName();
//        String packageStr = this.context.getJavaDtoGeneratorConfiguration().getTargetPackage();
//        String name = introspectedTable.getRules().calculateAllFieldsClass().getShortName();
        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$
        if (stringHasValue(introspectedTable
                .getSelectByPrimaryKeyQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
        }
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());
//        if (introspectedTable.hasBLOBColumns()) {
//            answer.addElement(new TextElement(",")); //$NON-NLS-1$
//            answer.addElement(getBlobColumnListElement());
//        }

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("where"); //$NON-NLS-1$
        dynamicElement.addElement(new TextElement("#{queryCriteria}"));
        answer.addElement(dynamicElement);

//        for (IntrospectedColumn introspectedColumn : introspectedTable.getBaseColumns()) {
//            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
//            sb.setLength(0);
//            sb.append(introspectedColumn.getJavaProperty()); //$NON-NLS-1$
//            sb.append(" != null"); //$NON-NLS-1$
//            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
//            dynamicElement.addElement(isNotNullElement);
//
//            sb.setLength(0);
//            sb.append("and ");
//            sb.append(MyBatis3FormattingUtilities
//                    .getAliasedEscapedColumnName(introspectedColumn));
//            sb.append(" = "); //$NON-NLS-1$
//            sb.append(MyBatis3FormattingUtilities.getParameterClause(
//                    introspectedColumn)); //$NON-NLS-1$
////            sb.append(',');
//
//            isNotNullElement.addElement(new TextElement(sb.toString()));
//        }

//        if (context.getPlugins()
//                .sqlMapSelectByPrimaryKeyElementGenerated(answer,
//                        introspectedTable)) {
//            parentElement.addElement(answer);
//        }
        return answer;
    }

    public XmlElement addElement_del(XmlElement parentElement) {
        XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "deleteByQueryCriteria")); //$NON-NLS-1$
//        if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
//            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
//                    introspectedTable.getResultMapWithBLOBsId()));
//        } else {
//            answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
//                    introspectedTable.getBaseResultMapId()));
//        }

//        String parameterType = introspectedTable.getRules().calculateAllFieldsClass().getFullyQualifiedName();
////        String packageStr = this.context.getJavaDtoGeneratorConfiguration().getTargetPackage();
////        String name = introspectedTable.getRules().calculateAllFieldsClass().getShortName();
//        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
//                parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("delete "); //$NON-NLS-1$
//        if (stringHasValue(introspectedTable
//                .getSelectByPrimaryKeyQueryId())) {
//            sb.append('\'');
//            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
//            sb.append("' as QUERYID,"); //$NON-NLS-1$
//        }
//        answer.addElement(new TextElement(sb.toString()));
//        answer.addElement(getBaseColumnListElement());
////        if (introspectedTable.hasBLOBColumns()) {
////            answer.addElement(new TextElement(",")); //$NON-NLS-1$
////            answer.addElement(getBlobColumnListElement());
////        }

//        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("where"); //$NON-NLS-1$
        dynamicElement.addElement(new TextElement("#{queryCriteria}"));
        answer.addElement(dynamicElement);

//        for (IntrospectedColumn introspectedColumn : introspectedTable.getBaseColumns()) {
//            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
//            sb.setLength(0);
//            sb.append(introspectedColumn.getJavaProperty()); //$NON-NLS-1$
//            sb.append(" != null"); //$NON-NLS-1$
//            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
//            dynamicElement.addElement(isNotNullElement);
//
//            sb.setLength(0);
//            sb.append("and ");
//            sb.append(MyBatis3FormattingUtilities
//                    .getAliasedEscapedColumnName(introspectedColumn));
//            sb.append(" = "); //$NON-NLS-1$
//            sb.append(MyBatis3FormattingUtilities.getParameterClause(
//                    introspectedColumn)); //$NON-NLS-1$
////            sb.append(',');
//
//            isNotNullElement.addElement(new TextElement(sb.toString()));
//        }

//        if (context.getPlugins()
//                .sqlMapSelectByPrimaryKeyElementGenerated(answer,
//                        introspectedTable)) {
//            parentElement.addElement(answer);
//        }
        return answer;
    }
}
