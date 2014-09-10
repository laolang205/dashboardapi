package com.kaikeba.data.api.dashboard.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kaikeba.data.api.dashboard.dao.CourseScheduleDao;
import com.kaikeba.data.api.dashboard.representations.CourseScheduleInfo;

@Path("/courseschedule")
@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
public class CourseScheduleResource {

	private final CourseScheduleDao courseScheduleDao;
	public CourseScheduleResource(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.courseScheduleDao =jdbi.onDemand(CourseScheduleDao.class);
	}
	
	@GET
	public Response getCourseScheduleInfoTree(@QueryParam("callback") String callback)
	{
		Map<Integer,List<CourseScheduleInfo>> ret = new HashMap<Integer,List<CourseScheduleInfo>>();
		List<CourseScheduleInfo> courseSchedInfolist = courseScheduleDao.getCourseScheduleInfoTree();
		
		List<CourseScheduleInfo> cslist = null;
		for(CourseScheduleInfo cs:courseSchedInfolist)
		{
			if(!ret.containsKey(cs.getWww_id()))
			{
				cslist = new ArrayList<CourseScheduleInfo>();
				cslist.add(cs);
				ret.put(cs.getWww_id(), cslist);			
			}
			else
			{
				ret.get(cs.getWww_id()).add(cs);
				
			}		
		}
				
		return Response.ok(new JSONPObject(callback,ret)).build();
		
	}
}
