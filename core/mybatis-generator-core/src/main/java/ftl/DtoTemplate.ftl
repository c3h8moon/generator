package ${templatePackage};

import com.ayhuli.parent.base.entity.CurrentUser;
import lombok.Getter;
import lombok.Setter;
import com.ayhuli.parent.base.entity.DataEntity;

import java.util.Date;

/**
 * @Company: 2018-2018 哎呦狐狸科技
 * @FileName: ${className}
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