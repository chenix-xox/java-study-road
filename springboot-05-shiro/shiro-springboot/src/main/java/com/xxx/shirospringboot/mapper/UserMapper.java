package com.xxx.shirospringboot.mapper;

import com.xxx.shirospringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Chenix
 * @create 2024-03-06 23:31
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 通过name查询用户信息
     * @param name
     * @return
     */
    User queryUserByName(@Param("name") String name);
}
