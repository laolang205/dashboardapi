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
public class TrendByDayInfo {

	private final int id;
	private final Date day;
	private final int uv_new_visitors;
	private final int uv_old_visitors;
	private final int enroll_visitors;
	private final int act_enroll_visitors;
	private final int login_visitors;
	private final int view_OpenCourse_visitors;
	private final int view_InstructiveCourse_visitors;
	private final int view_OpenCourse_times;
	private final int view_InstructiveCourse_times;
	private final int choice_InstructiveCourse_visitors;
	private final int choice_InstructiveCourse_times;
	private final int num_of_courses;
	private final Date create_time;

	public static class TrendByDayMapper implements
			ResultSetMapper<TrendByDayInfo> {

		@Override
		public TrendByDayInfo map(int index, ResultSet rs, StatementContext sc)
				throws SQLException {

			return new TrendByDayInfo(rs.getInt("id"), rs.getTimestamp("day"),
					rs.getInt("uv_new_visitors"), rs.getInt("uv_old_visitors"),
					rs.getInt("enroll_visitors"),
					rs.getInt("act_enroll_visitors"),
					rs.getInt("login_visitors"),
					rs.getInt("view_OpenCourse_visitors"),
					rs.getInt("view_InstructiveCourse_visitors"),
					rs.getInt("view_OpenCourse_times"),
					rs.getInt("view_InstructiveCourse_times"),
					rs.getInt("choice_InstructiveCourse_visitors"),
					rs.getInt("choice_InstructiveCourse_times"),
					rs.getInt("num_of_courses"), rs.getTimestamp("create_time"));
		}

	}

}
