package com.kaikeba.data.api.dashboard.representations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseScheduleInfo {

	private final int lms_id;
	private final String name;
	private final int superclass_id;
	private final int www_id;
	private final String type;
	private final int category_id;
	private final int institution_id;
	private final int weeks;
	private final int recommend;
	private final String level;
	private final Date created_at;
	private final Date updated_at;
	private final Date start_at;
	private final Date conclude_at;
	private final String promotional_video_url;
	private final String certificate_type;
	private final int position;
	private final String status;

	public static class CourseScheduleMapper implements
			ResultSetMapper<CourseScheduleInfo> {

		@Override
	public CourseScheduleInfo map(int index, ResultSet rs,
			StatementContext sc) throws SQLException {
		// TODO Auto-generated method stub
		return new CourseScheduleInfo(rs.getInt("lms_id"),rs.getString("name"),rs.getInt("superclass_id")
				, rs.getInt("www_id"), rs.getString("type"), rs.getInt("category_id"),rs.getInt("institution_id")
				, rs.getInt("weeks"), rs.getInt("recommend"), rs.getString("level"), rs.getTimestamp("created_at"),
				rs.getTimestamp("updated_at"), rs.getTimestamp("start_at"), rs.getTimestamp("conclude_at")
				,rs.getString("promotional_video_url"), rs.getString("certificate_type"), rs.getInt("position"),rs.getString("status"));
	}

	}

}
