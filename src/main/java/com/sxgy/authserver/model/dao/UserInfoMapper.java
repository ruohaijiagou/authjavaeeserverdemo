package com.sxgy.authserver.model.dao;

import com.sxgy.authserver.model.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserInfoMapper {
    List<UserInfo> getAll();
    UserInfo getUserByUserID(Long userId);
    void saveUserInfo(UserInfo userInfo);

}
