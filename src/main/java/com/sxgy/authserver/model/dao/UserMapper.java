package com.sxgy.authserver.model.dao;

import java.util.List;

import com.sxgy.authserver.model.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper{
    List<User> getAll();
}
