package ${templatePackage};

import com.ayhuli.parent.base.page.Page;
import ${dtoPackage};
import ${facadePackage}.${boClazz}Facade;
import com.ayhuli.web.manage.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${boClazz}Service
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 17:15
 * @Modified Update By:
 */
@Service
public class ${boClazz}Service {

    @Autowired
    private ${boClazz}Facade ${boClazzLess}Facade;
    
    public ${boClazz}Dto get(Long id) {
        return ${boClazzLess}Facade.getByID(id);
    }
    
    public List<${boClazz}Dto> findList(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        return ${boClazzLess}Facade.findList(${boClazzLess}Dto);
    }

    public Page<${boClazz}Dto> findPage(Page<${boClazz}Dto> page, ${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        return ${boClazzLess}Facade.findPage(page, ${boClazzLess}Dto);
    }

    public void save(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        if (${boClazzLess}Dto.getId() == null) {
            ${boClazzLess}Facade.insert(${boClazzLess}Dto);
        } else {
            ${boClazzLess}Facade.update(${boClazzLess}Dto);
        }
    }

    public void delete(${boClazz}Dto ${boClazzLess}Dto) {
        ${boClazzLess}Dto.setCurrentUser(UserUtils.getCurrentUserDto());
        ${boClazzLess}Facade.delete(${boClazzLess}Dto);
    }
}