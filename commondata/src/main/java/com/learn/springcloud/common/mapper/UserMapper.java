package com.learn.springcloud.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.springcloud.common.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> getAllUser();

    User getById(Integer id);

    int batchInsert(@Param("users") List<User> users);

}
