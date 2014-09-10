package com.kaikeba.data.api.dashboard.resources;

import java.util.ArrayList;
import java.util.Date;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kaikeba.data.api.dashboard.dao.UvDao;
import com.kaikeba.data.api.dashboard.representations.UvSumInfo;


@Path("/uvsum")
@Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
public class UvSumResource {

	private static final Logger LOGGER =
	           LoggerFactory.getLogger(UvSumResource.class);
	private final UvDao uvDao;
	public UvSumResource(DBI jdbi) {
		// TODO Auto-generated constructor stub
		this.uvDao = jdbi.onDemand(UvDao.class);
	}
	
	@GET
	public Response getUvSumList(@QueryParam("stime") Date sTime ,@QueryParam("etime") Date eTime,@QueryParam("callback") String callback)
	{

		List<UvSumInfo> list = uvDao.getUvSumList(sTime, new Date(eTime.getTime()+1000*60*60*24));
		Map<Integer,List<UvSumInfo>> ret = new HashMap<Integer,List<UvSumInfo>>();
	    ret.put(0, new ArrayList<UvSumInfo>());
	    ret.put(1, new ArrayList<UvSumInfo>());
	    int[] sumArray = new int[2];
	    
		for(UvSumInfo uv : list)
		{
			ret.get(uv.getIs_paid()).add(uv);
			sumArray[uv.getIs_paid()]+=uv.getCount();
		}
		int j=0;
		for(j=0;j<2;j++)
		{
			ret.get(j).add(new UvSumInfo("", j, sumArray[j],new Date()));
		}
		
		return Response.ok(new JSONPObject(callback,ret)).build();
	}
	
	
	
	@GET
	@Path("/daysum")
	public Response getUvDaySumList(@QueryParam("stime") Date sTime ,@QueryParam("etime") Date eTime,@QueryParam("callback") String callback)
	{

		List<UvSumInfo> list = uvDao.getUvDaySumList(sTime, new Date(eTime.getTime()+1000*60*60*24));
		
		Map<Date,ArrayList<UvSumInfo>> payMap = new HashMap<Date,ArrayList<UvSumInfo>>();
		Map<Date,ArrayList<UvSumInfo>> unpayMap = new HashMap<Date,ArrayList<UvSumInfo>>();
	    
		for(UvSumInfo uv : list)
		{
               if(uv.getIs_paid()==0)
               {
            	   if(unpayMap.containsKey(uv.getDate()))
            	   {
            		   unpayMap.get(uv.getDate()).add(uv);
            	   }
            	   else
            	   {
            		   ArrayList<UvSumInfo> newlist = new ArrayList<UvSumInfo>();
            		   newlist.add(uv);
            		   unpayMap.put(uv.getDate(), newlist);
            	   }
               }
               else
               {
            	   if(payMap.containsKey(uv.getDate()))
            	   {
            		   payMap.get(uv.getDate()).add(uv);
            	   }
            	   else
            	   {
            		   ArrayList<UvSumInfo> newlist = new ArrayList<UvSumInfo>();
            		   newlist.add(uv);
            		   payMap.put(uv.getDate(), newlist);
            	   }
            	   
               }
		
		}
		
		list.clear();
	   Map <Integer,List<ArrayList<UvSumInfo>>> ret = new HashMap<Integer, List<ArrayList<UvSumInfo>>>();

	   ret.put(0, new ArrayList< ArrayList<UvSumInfo>>());
	   ret.put(1, new ArrayList< ArrayList<UvSumInfo>>());
	
	   for(Map.Entry<Date,ArrayList<UvSumInfo>> me :unpayMap.entrySet())
	   {
		   ret.get(0).add(me.getValue());
	   }
	   unpayMap.clear();
	   for(Map.Entry<Date,ArrayList<UvSumInfo>> me :payMap.entrySet())
	   {
		   ret.get(1).add(me.getValue());
	   }
	   
	   payMap.clear();
		return Response.ok(new JSONPObject(callback,ret)).build();
	}
}
