package com.kaikeba.data.api.dashboard.dao;

import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.RegionInfo;
import com.kaikeba.data.api.dashboard.representations.RegionInfo.RegionMapper;

public interface RegionDao {

	@Mapper(RegionMapper.class)
	@SqlQuery("select min(id) as id,min(day) as day,province,min(city) as city,min(longitude) as longitude,min(latitude) as latitude ,sum(uv_new_visitors) as uv_new_visitors"
			+ ",sum(uv_old_visitors) as uv_old_visitors,sum(enroll_visitors) as enroll_visitors,sum(act_enroll_visitors) as act_enroll_visitors,sum(login_visitors) as login_visitors"
			+ ",sum(view_OpenCourse_visitors) as view_OpenCourse_visitors,sum(view_InstructiveCourse_visitors) as view_InstructiveCourse_visitors,sum(view_OpenCourse_times) as view_OpenCourse_times"
			+ ",sum(view_InstructiveCourse_times) as view_InstructiveCourse_times,min(create_time) as create_time"
			+ " from dashboard.dashboard_regional_distribution where day>=:sTime and day<:eTime group by province")
	List<RegionInfo> getProvinceRegionList(@Bind("sTime") Date sTime,
			@Bind("eTime") Date eTime);

	@Mapper(RegionMapper.class)
	@SqlQuery("select * from dashboard.dashboard_regional_distribution where day>=:sTime and day<:eTime and province =:provinceName")
	List<RegionInfo> getProvinceRegionListByProvinceName(@Bind("sTime") Date sTime,
			@Bind("eTime") Date eTime, @Bind("provinceName") String provinceName);
}