package ${templatePackage};

import com.ayhuli.parent.base.page.Page;
import com.ayhuli.plugin.asyntransactional.stereotype.AsynTransactional;
import com.ayhuli.parent.base.utils.CheckUtil;
import com.ayhuli.service.base.exception.ServiceError;
import ${mapperPackage};
import ${facadePackage};
import ${servicePackage};
import ${dtoPackage};
import ${convertPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Company: 2017-2017 备胎科技
* @FileName: SysAreaFacadeImpl
* @Desctiption:
* @Author: chenye
* @Date: Created by 2017/9/23 15:34
* @Modified Update By:
*/
@Service("${boClazzLess}Facade")
public class ${className} implements ${boClazz}Facade {

    @Autowired
    private ${serviceClassName} ${serviceLessClassName};
    @Autowired
    private ${boClazz}Convert ${boClazzLess}Convert;

    public ${boClazz}Dto getByID_all_100(Long primaryKey) {
        CheckUtil.check(primaryKey == null, ServiceError.ERROR_ENTITY_NULL);
        return ${serviceLessClassName}.getByDto(primaryKey);
    }

    @AsynTransactional
    public void insert_all_200(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.insertSelective(${boClazzLess});
    }

    @AsynTransactional
    public void update_all_300(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.updateByPrimaryKeySelective(${boClazzLess});
    }

    @AsynTransactional
    public void delete_all_400(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.deleteByPrimaryKey(${boClazzLess});
    }

    public List<${boClazz}Dto> findList_all_500(${boClazz}Dto dto) {
        return ${serviceLessClassName}.findList(dto);
    }

    public Page<${boClazz}Dto> findPage_all_600(Page<${boClazz}Dto> page, ${boClazz}Dto dto) {
        return ${boClazzLess}Service.findPage(page, dto);
    }
}