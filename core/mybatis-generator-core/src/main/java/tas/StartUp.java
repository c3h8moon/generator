/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package tas;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: StartUp
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/1 14:12
 * @Modified Update By:
 */
public class StartUp {

    public static final boolean isFlag = false; //是否单条s

    public static void main(String[] args) throws Exception {
        if (isFlag) {
            List<String> warnings = new ArrayList<String>();
            File configFile = new File(StartUp.class.getResource("/generatorConfig_Table_comment.xml").toURI());
            if (configFile != null) {
                ConfigurationParser cp = new ConfigurationParser(warnings);
                Configuration config = cp.parseConfiguration(configFile);
                DefaultShellCallback shellCallback = new DefaultShellCallback(true);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
                myBatisGenerator.generate(null);
                System.out.println(warnings);
            }
        } else {
            for (int i = 0; i < 20; i++) {
                List<String> warnings = new ArrayList<String>();
                File configFile = null;
                switch (i) {
                    case 0 :
                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_wallet.xml").toURI());
                        break;
                    case 1 :
//                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_balance.xml").toURI());
                        break;
                    case 2 :
                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_comment.xml").toURI());
                        break;
                    case 3 :
                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_order.xml").toURI());
                        break;
                    case 4 :
                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_pro.xml").toURI());
                        break;
                    case 5 :
//                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_sys.xml").toURI());
                        break;
                    case 6 :
//                        configFile = new File(StartUp.class.getResource("/generatorConfig_Table_user.xml").toURI());
                        break;
                    default:
                        break;
                }
                if (configFile != null) {
                    ConfigurationParser cp = new ConfigurationParser(warnings);
                    Configuration config = cp.parseConfiguration(configFile);
                    DefaultShellCallback shellCallback = new DefaultShellCallback(true);
                    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
                    myBatisGenerator.generate(null);
                    System.out.println(warnings);
                }
            }
        }
    }
}
