package com.kaikeba.data.api.dashboard.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.UserInfo;
import com.kaikeba.data.api.dashboard.representations.UserInfo.UserMapper;

public interface UserDao {

	@SqlQuery("select count(*)  from dashboard.user where pause=0 and username=:username and password=:password ")
	int countUser(@Bind("username") String username,@Bind("password") String password);
	
	@SqlQuery("select *  from dashboard.user where pause=0 and username=:username and password=:password ")
	@Mapper(UserMapper.class)
	UserInfo getUserInfo(@Bind("username") String username,@Bind("password") String password);
}
