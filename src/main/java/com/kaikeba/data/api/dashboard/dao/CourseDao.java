package com.kaikeba.data.api.dashboard.dao;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.CourseInfo;
import com.kaikeba.data.api.dashboard.representations.CourseInfo.CourseInfoMapper;

public interface CourseDao {

	@Mapper(CourseInfoMapper.class)
	@SqlQuery("select * from dashboard.dashboard_courses_by_course where id=4128")
	CourseInfo getCoureseInfo();

}
