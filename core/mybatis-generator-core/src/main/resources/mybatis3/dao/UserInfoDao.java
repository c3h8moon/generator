/**
 *    Copyright 2006-2018 the original author or authors.
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
package dao;

import pojo.UserInfo;

public interface UserInfoDao {
    public int deleteByPrimaryKey(Long id);

    public int insert(UserInfo record);

    public int insertSelective(UserInfo record);

    public UserInfo selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(UserInfo record);

    public int updateByPrimaryKey(UserInfo record);

    public List<T> getQueryCriteria(@Param("queryCriteria") String queryCriteria);

    public int deleteByQueryCriteria(@Param("queryCriteria") String queryCriteria);
}