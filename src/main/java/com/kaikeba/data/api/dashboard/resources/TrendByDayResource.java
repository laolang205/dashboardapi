package com.kaikeba.data.api.dashboard.resources;

import io.dropwizard.auth.Auth;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Preconditions;
import com.kaikeba.data.api.dashboard.dao.TrendByDayDao;
import com.kaikeba.data.api.dashboard.representations.TrendByDayInfo;
import com.kaikeba.data.api.dashboard.representations.TrendByHourInfo;
import com.kaikeba.data.api.dashboard.representations.UserInfo;




@Path("/trendbyday")
@Produces(MediaType.APPLICATION_JSON)
public class TrendByDayResource {

	private static final Logger LOGGER =
	           LoggerFactory.getLogger(TrendByDayResource.class);
	
	private final TrendByDayDao trendByDayDao;
	public TrendByDayResource(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.trendByDayDao = jdbi.onDemand(TrendByDayDao.class);
		
	}
	
	

	@GET
	public Response getTrendByDayByDate(@QueryParam("stime") Date sTime ,@QueryParam("etime") Date eTime,
		@QueryParam("callback") String callback)
	{

		LOGGER.info("stime is "+ sTime +" etime is "+eTime);
		List<TrendByDayInfo> ret = trendByDayDao.getTrendbyDay(sTime,new Date(eTime.getTime()+1000*60*60*24));
		//return Response.ok(ret).build();
		return Response.ok(new JSONPObject(callback,ret)).build();
	}
}
