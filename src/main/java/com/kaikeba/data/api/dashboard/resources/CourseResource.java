package com.kaikeba.data.api.dashboard.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Preconditions;
import com.kaikeba.data.api.dashboard.dao.CourseDao;
import com.kaikeba.data.api.dashboard.representations.CourseInfo;


@Path("/courses")
@Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8" } )
public class CourseResource {

	
	
	private final CourseDao courseDao;
	
	public CourseResource(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.courseDao = jdbi.onDemand(CourseDao.class);
		
	}

	@GET
	public Response getConfigMessage(@QueryParam("callback") String callback)
	{
		Preconditions.checkNotNull(callback,"claback is null");
		//CourseInfo c = courseDao.getCoureseInfo();
		CourseInfo c = new CourseInfo(1, new Date(),1, 1, 1, 1, 1, "type", "course_name", 1, 1, 1, 1, 0,  0, new Date(), new Date(), new Date());
		return Response.ok(new JSONPObject("alert", c)).build();
		//return Response.ok(c).build();
	}
	
}
