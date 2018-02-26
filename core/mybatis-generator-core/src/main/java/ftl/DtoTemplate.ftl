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