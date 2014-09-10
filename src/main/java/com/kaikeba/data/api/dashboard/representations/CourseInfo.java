package com.kaikeba.data.api.dashboard.representations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

@Data
@AllArgsConstructor
public class CourseInfo {

	private final int id;
	private final Date day;
	private final int www_id;
	private final int superclass_id;
	private final int lms_id;
	private final int cms_id;
	private final int category_id;
	private final String type;
	private final String course_name;
	private final int choice_visitors;
	private final int view_visitors;
	private final int view_times;
	private final int video_view_count;
	private final int num_of_finished_final_exam;
	private final int num_of_obtain_certificate;
	private final Date course_started_time;
	private final Date course_complete_time;
	private final Date create_time;

	public static class CourseInfoMapper implements ResultSetMapper<CourseInfo> {

		@Override
		public CourseInfo map(int index, ResultSet rs, StatementContext ctx)
				throws SQLException {
			// TODO Auto-generated method stub
			return new CourseInfo(rs.getInt("id"), rs.getTimestamp("day"),
					rs.getInt("www_id"), rs.getInt("superclass_id"),
					rs.getInt("lms_id"), rs.getInt("cms_id"),
					rs.getInt("category_id"), rs.getString("type"),
					rs.getString("course_name"), rs.getInt("choice_visitors"),
					rs.getInt("view_visitors"), rs.getInt("view_times"),
					rs.getInt("video_view_count"),
					rs.getInt("num_of_finished_final_exam"),
					rs.getInt("num_of_obtain_certificate"),
					rs.getTimestamp("course_started_time"),
					rs.getTimestamp("course_complete_time"),
					rs.getTimestamp("create_time"));

		}

	}

}
