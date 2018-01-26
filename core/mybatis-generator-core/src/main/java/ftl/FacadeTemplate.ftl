package ${templatePackage};

import com.ayhuli.parent.base.page.Page;
import ${mapperPackage};

import java.util.List;

/**
* Name: com.ayhuli.app.server.api.sys.facade
* Description: 方法定义如下： 【方法描述】_【所属范围】_【序号】
* Copyright: copyright@2017
*
* @Author: chenye
* @Version: ayhuli 1.0.0
* @Date: 2017/1/16 16:56
*/
public interface ${className} {

    /**
    * 功能描述: <br>
    * 〈根据主键获取单条数据〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:00
    * @Version: 1.0.0
    */
    public ${boClazz}Dto getByID_all_100(Long primaryKey);

    /**
    * 功能描述: <br>
    * 〈保存数据〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:01
    * @Version: 1.0.0
    */
    public void insert_all_200(${boClazz}Dto dto);

    /**
    * 功能描述: <br>
    * 〈修改数据〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:01
    * @Version: 1.0.0
    */
    public void update_all_300(${boClazz}Dto dto);

    /**
    * 功能描述: <br>
    * 〈删除数据〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:01
    * @Version: 1.0.0
    */
    public void delete_all_400(${boClazz}Dto dto);

    /**
    * 功能描述: <br>
    * 〈查询所有数据--默认100条〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:01
    * @Version: 1.0.0
    */
    public List<${boClazz}Dto> findList_all_500(${boClazz}Dto dto);

    /**
    * 功能描述: <br>
    * 〈查询分页数据〉
    *
    * @Author:chenye
    * @Date: 2017/9/23 15:02
    * @Version: 1.0.0
    */
    public Page<${boClazz}Dto> findPage_all_600(Page<${boClazz}Dto> page, ${boClazz}Dto dto);
}