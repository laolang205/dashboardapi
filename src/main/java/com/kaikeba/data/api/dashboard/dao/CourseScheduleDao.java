package com.kaikeba.data.api.dashboard.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.CourseScheduleInfo;
import com.kaikeba.data.api.dashboard.representations.CourseScheduleInfo.CourseScheduleMapper;

public interface CourseScheduleDao {

	@Mapper(CourseScheduleMapper.class)
	@SqlQuery("select * from dwsuperclass.dw_course_table where www_id is not NULL")
	List<CourseScheduleInfo> getCourseScheduleInfoTree();
}
