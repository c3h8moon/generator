package ${templatePackage};

import com.ayhuli.parent.base.entity.CurrentUser;
import lombok.Getter;
import lombok.Setter;
import com.ayhuli.parent.base.entity.DataEntity;

import java.util.Date;

/**
* @Company: 2017-2017 备胎科技
* @FileName: Test
* @Desctiption:
* @Author: chenye
* @Date: Created by 2017/9/19 17:15
* @Modified Update By:
*/
@Getter
@Setter
public class ${className} extends DataEntity<${className}>  {
${fields}

    public ${className}(){
        super();
    }

    public ${className}(CurrentUser currentUser){
        if (currentUser != null) {
            this.currentUser = currentUser;
        }
    }
}