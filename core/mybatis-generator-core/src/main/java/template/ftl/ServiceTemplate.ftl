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