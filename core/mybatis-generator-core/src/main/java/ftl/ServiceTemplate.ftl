package ${templatePackage};

import java.util.List;

import org.springframework.stereotype.Service;
import com.ayhuli.parent.base.utils.CheckUtil;
import com.ayhuli.plugin.asyntransactional.stereotype.AsynInsert;
import com.ayhuli.plugin.asyntransactional.stereotype.AsynSelect;
import com.ayhuli.plugin.asyntransactional.stereotype.AsynUpdate;
import com.ayhuli.service.base.exception.ServiceError;
import com.ayhuli.service.base.service.CrudService;
import com.ayhuli.parent.base.page.Page;
import ${mapperPackage};
import ${dtoPackage};
import ${boPackage};

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 17:15
 * @Modified Update By:
 */
@Service
public class ${className} extends CrudService<${mapperType}, ${boClazz}>  {

    @AsynInsert(primarykeyValues = {"#${boClazzLess}.id"})
    public ${boClazz} insertSelective(${boClazz} ${boClazzLess}){
        CheckUtil.check(${boClazzLess} == null, ServiceError.ERROR_ENTITY_NULL);
        dao.insertSelective(${boClazzLess});
        return ${boClazzLess};
    }

    @AsynUpdate(primarykey = "#${boClazzLess}.id")
    public ${boClazz} updateByPrimaryKeySelective(${boClazz} ${boClazzLess}) {
        CheckUtil.check(${boClazzLess} == null, ServiceError.ERROR_ENTITY_NULL);
        dao.updateByPrimaryKeySelective(${boClazzLess});
        return ${boClazzLess};
    }

    @AsynUpdate(primarykey = "#${boClazzLess}.id")
    public void deleteByPrimaryKey(${boClazz} ${boClazzLess}) {
        CheckUtil.check(${boClazzLess} == null, ServiceError.ERROR_ENTITY_NULL);
        ${boClazzLess}.setDelFlag(${boClazz}.DEL_FLAG_DELETE);
        dao.updateByPrimaryKeySelective(${boClazzLess});
    }

    public List<${boClazz}Dto> findList(${boClazz}Dto dto){
        return dao.findList(dto);
    }

    @AsynSelect
    public ${boClazz}Dto getByDto(Long id){
        return dao.getByDto(id);
    }

    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page,  ${boClazz}Dto dto) {
        dto.setPage(page);
        page.setList(dao.findList(dto));
        return page;
    }
}