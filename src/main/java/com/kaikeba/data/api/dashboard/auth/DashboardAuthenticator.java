package com.kaikeba.data.api.dashboard.auth;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.kaikeba.data.api.dashboard.dao.UserDao;
import com.kaikeba.data.api.dashboard.representations.UserInfo;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class DashboardAuthenticator implements
Authenticator<BasicCredentials, UserInfo>{

	private static final Logger LOGGER =
	           LoggerFactory.getLogger(DashboardAuthenticator.class);
	private UserDao userDao;
	public DashboardAuthenticator(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.userDao = jdbi.onDemand(UserDao.class);
	}
	@Override
	public Optional<UserInfo> authenticate(BasicCredentials bc)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		//boolean validUser = (userDao.countUser(bc.getUsername(), bc.getPassword())==1);
		
//		if(bc.getUsername().equals("admin")&&bc.getPassword().equals("admin"))
//		{
//			return Optional.of(true);
//		}
//		if(validUser)
//		{
//			return Optional.of(true);
//		}
		
		LOGGER.info("get request username "+bc.getUsername()+" get request password "+bc.getPassword());
		UserInfo userInfo = userDao.getUserInfo(bc.getUsername(), bc.getPassword());
		if(userInfo!=	null)
		{
			LOGGER.info("get userinfo  "+ userInfo.toString());
			return Optional.of(userInfo);
		}
		return Optional.absent();
	}

}
