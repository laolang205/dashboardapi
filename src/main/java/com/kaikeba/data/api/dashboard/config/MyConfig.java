package com.kaikeba.data.api.dashboard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class MyConfig extends Configuration {

	@JsonProperty
	private String message;

	public String getMessage() {
		return message;
	}

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

}
