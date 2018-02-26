package ${templatePackage};

import com.ayhuli.parent.base.page.Page;
import com.ayhuli.parent.base.stereotype.AsynTransactional;
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
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
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

    @Override
    public ${boClazz}Dto getByID(Long primaryKey) {
        CheckUtil.check(primaryKey == null, ServiceError.ERROR_ENTITY_NULL);
        return ${serviceLessClassName}.getByDto(primaryKey);
    }

    @Override
    @AsynTransactional
    public void insert(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.insertSelective(${boClazzLess});
    }

    @Override
    @AsynTransactional
    public void update(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.updateByPrimaryKeySelective(${boClazzLess});
    }

    @Override
    @AsynTransactional
    public void delete(${boClazz}Dto dto) {
        ${boClazz} ${boClazzLess} = ${boClazzLess}Convert.convertEntity(dto);
        ${serviceLessClassName}.deleteByPrimaryKey(${boClazzLess});
    }

    @Override
    public List<${boClazz}Dto> findList(${boClazz}Dto dto) {
        return ${serviceLessClassName}.findList(dto);
    }

    @Override
    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page, ${boClazz}Dto dto) {
        return ${boClazzLess}Service.findPage(page, dto);
    }
}