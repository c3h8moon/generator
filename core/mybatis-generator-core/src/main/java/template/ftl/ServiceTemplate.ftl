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

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${mapperPackage};

/**
*
* Created by CodeGenerator
*/

@Service
public class ${className} {
@Resource
private ${mapperType} ${mapperName};

public void save(${boClazz} boName){
this.${mapperName}.insertSelective(boName);
}

public void deleteByPrimaryKey(Integer primaryKey){
this.${mapperName}.deleteByPrimaryKey(primaryKey);
}

public ${boClazz} getByPrimaryKey(Integer primaryKey){
return this.${mapperName}.selectByPrimaryKey(primaryKey);
}

public ${boClazz} getNotDeleteByPrimaryKey(Integer primaryKey){
return this.${mapperName}.selectNotDeleteByPrimaryKey(primaryKey);
}

public List<${boClazz}> findAll(){
return this.${mapperName}.selectAll();
}

public List<${boClazz}> findNotDeleteAll(){
return this.${mapperName}.selectNotDeleteAll();
}