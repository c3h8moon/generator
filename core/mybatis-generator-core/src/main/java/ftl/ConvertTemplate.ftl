package ${templatePackage};

import com.ayhuli.parent.base.utils.CheckUtil;
import com.ayhuli.service.base.exception.ServiceError;
import com.ayhuli.service.base.service.BaseConvert;
import ${mapperPackage};
import ${dtoPackage};
import org.springframework.beans.factory.annotation.Autowired;
import com.ayhuli.service.top.api.facade.SysIDFacade;
import org.springframework.stereotype.Component;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${boClazz}Convert
 * @Desctiption: 数据转换层，数据转换，以及字段非空判断，在这里进行
 * @Author: chenye
 * @Date: Created by 2017/9/23 15:39
 * @Modified Update By:
 */
@Component
public class ${className} extends BaseConvert {

    @Autowired
    private SysIDFacade sysIDFacade;

    /**
     * 功能描述: <br>
     * 〈保存数据--传入参数转换〉
     *
     * @Author:chenye
     * @Date: 2017/9/24 08:10
     * @Version: 1.0.0
     */
    public ${boClazz} convertEntity(${boClazz}Dto dto) {
        CheckUtil.check(dto == null, ServiceError.ERROR_ENTITY_NULL);
        ${boClazz} ${boClazzLess} = new ${boClazz}();
        ${boClazzLess}.init(dto);
        preInsertOrUpdate(sysIDFacade, ${boClazzLess});
        return ${boClazzLess};
    }
}