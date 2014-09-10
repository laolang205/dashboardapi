package com.kaikeba.data.api.dashboard;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaikeba.data.api.dashboard.auth.DashboardAuthenticator;
import com.kaikeba.data.api.dashboard.config.MyConfig;
import com.kaikeba.data.api.dashboard.representations.UserInfo;
import com.kaikeba.data.api.dashboard.resources.CourseResource;
import com.kaikeba.data.api.dashboard.resources.CourseScheduleResource;
import com.kaikeba.data.api.dashboard.resources.RegionResource;
import com.kaikeba.data.api.dashboard.resources.TrendByDayResource;
import com.kaikeba.data.api.dashboard.resources.TrendByHourSource;
import com.kaikeba.data.api.dashboard.resources.UvSumResource;

import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<MyConfig> {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new App().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(Bootstrap<MyConfig> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(MyConfig c, Environment e) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info(c.getMessage());
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");

		e.jersey().register(new CourseResource(jdbi));
		e.jersey().register(new TrendByHourSource(jdbi));
		e.jersey().register(new TrendByDayResource(jdbi));
		e.jersey().register(new RegionResource(jdbi));
		e.jersey().register(new CourseScheduleResource(jdbi));
		e.jersey().register(new UvSumResource(jdbi));
		e.jersey().register(new BasicAuthProvider<UserInfo>(new DashboardAuthenticator(jdbi), "Web Service Realm"));
	}

}
