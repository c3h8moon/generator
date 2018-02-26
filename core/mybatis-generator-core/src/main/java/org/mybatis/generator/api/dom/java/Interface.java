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
package org.mybatis.generator.api.dom.java;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;

import static org.mybatis.generator.api.dom.OutputUtilities.calculateImports;
import static org.mybatis.generator.api.dom.OutputUtilities.newLine;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class Interface.
 *
 * @author Jeff Butler
 */
public class Interface extends InnerInterface implements CompilationUnit {
    
    private Set<FullyQualifiedJavaType> importedTypes;

    private Set<String> staticImports;

    private List<String> fileCommentLines;

    public Interface(FullyQualifiedJavaType type) {
        super(type);
        importedTypes = new TreeSet<FullyQualifiedJavaType>();
        fileCommentLines = new ArrayList<String>();
        staticImports = new TreeSet<String>();
    }

    public Interface(String type) {
        this(new FullyQualifiedJavaType(type));
    }

    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return importedTypes;
    }

    @Override
    public void addImportedType(FullyQualifiedJavaType importedType) {
        if (importedType.isExplicitlyImported()
                && !importedType.getPackageName().equals(getType().getPackageName())) {
            importedTypes.add(importedType);
        }
    }

    @Override
    public String getFormattedContent() {

        return getFormattedContent(0, this);
    }

    @Override
    public String getFormattedContent(int indentLevel, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

//        System.out.println("123");

        for (String commentLine : fileCommentLines) {
            sb.append(commentLine);
            newLine(sb);
        }

        if (stringHasValue(getType().getPackageName())) {
            sb.append("package "); //$NON-NLS-1$
            sb.append(getType().getPackageName());
            sb.append(';');
            newLine(sb);
            newLine(sb);
        }

        for (String staticImport : staticImports) {
            sb.append("import static "); //$NON-NLS-1$
            sb.append(staticImport);
            sb.append(';');
            newLine(sb);
        }

        if (staticImports.size() > 0) {
            newLine(sb);
        }

        sb.append("import com.ayhuli.plugin.database.mysql.announce.MyBatisDao;");

        newLine(sb);
        String key = this.getType().getShortName();

//        "com.ayhuli.service.comment.dao";
//        "com.ayhuli.service.comment.api.dto.commentannounce.CommentAnnounceDto"
        String dtoPackage = this.getType().getPackageName();
//        String packageName = key.replace("Dao", "").toLowerCase();
        dtoPackage = dtoPackage.replace("dao", "api.dto."+key.replace("Dao", "")+"Dto");


        String val = MyBatisGenerator.dtoPackage.get(key);
        System.out.println("key:" + key + "value : " + val);
        Set<String> importStrings = calculateImports(importedTypes);
        for (String importString : importStrings) {
            sb.append(importString);
            newLine(sb);
        }
        sb.append("import " + dtoPackage + ";");
        newLine(sb);
        newLine(sb);
        if (importStrings.size() > 0) {
            newLine(sb);
        }

        sb.append("@MyBatisDao");
        newLine(sb);
        String call = super.getFormattedContent(0, this);
//        String regix = "com.ayhuli.service.*.api.dto.";
        String regex = compilationUnit.getType().getPackageName();
        regex = regex.replace("dao","api.dto.");
        if (call.contains("com.ayhuli.service.base.persistence.")) {
            call = call.replace("com.ayhuli.service.base.persistence.","");
            call = call.replace(regex,"");
        }




        return sb.toString() + call;
    }

    @Override
    public void addFileCommentLine(String commentLine) {
        fileCommentLines.add(commentLine);
    }

    @Override
    public List<String> getFileCommentLines() {
        return fileCommentLines;
    }

    @Override
    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {
        this.importedTypes.addAll(importedTypes);
    }

    @Override
    public Set<String> getStaticImports() {
        return staticImports;
    }

    @Override
    public void addStaticImport(String staticImport) {
        staticImports.add(staticImport);
    }

    @Override
    public void addStaticImports(Set<String> staticImports) {
        this.staticImports.addAll(staticImports);
    }
}
