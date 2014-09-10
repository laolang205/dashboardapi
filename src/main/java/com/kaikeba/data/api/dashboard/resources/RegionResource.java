package com.kaikeba.data.api.dashboard.resources;

import io.dropwizard.auth.Auth;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kaikeba.data.api.dashboard.dao.RegionDao;
import com.kaikeba.data.api.dashboard.representations.RegionInfo;
import com.kaikeba.data.api.dashboard.representations.TrendByDayInfo;
import com.kaikeba.data.api.dashboard.representations.UserInfo;


@Path("/region")
@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
public class RegionResource {

	private static final Logger LOGGER =
	           LoggerFactory.getLogger(RegionResource.class);
	private final RegionDao regionDao;
	public RegionResource(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.regionDao = jdbi.onDemand(RegionDao.class);
	}
	
	@GET
	public Response getProvinceRegionList(@QueryParam("stime") Date sTime ,@QueryParam("etime") Date eTime,@QueryParam("callback") String callback)
	{
		LOGGER.info("stime is "+ sTime +" etime is "+eTime);
		List<RegionInfo> ret = regionDao.getProvinceRegionList(sTime,new Date(eTime.getTime()+1000*60*60*24));
		//return Response.ok(ret).build();
		return Response.ok(new JSONPObject(callback,ret)).build();
	}
	@GET
	@Path("/{provincename}")
	public Response getRegionListByProvincename(@QueryParam("stime") Date sTime ,@QueryParam("etime") Date eTime,@QueryParam("callback") String callback,@PathParam("provincename") String provincename)
	{
		List<RegionInfo> ret = regionDao.getProvinceRegionListByProvinceName(sTime,new Date(eTime.getTime()+1000*60*60*24),provincename);
		return Response.ok(new JSONPObject(callback,ret)).build();
		
	}
}
