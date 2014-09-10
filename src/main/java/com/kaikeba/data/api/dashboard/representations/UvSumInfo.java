package com.kaikeba.data.api.dashboard.representations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/*
 * not cotains all field
 */
public class UvSumInfo {

	private final String referer_name;
	private final int is_paid;
	private final int count;
	private final Date date;
	
	public static class UvInfoMapper implements ResultSetMapper<UvSumInfo> {

		@Override
		public UvSumInfo map(int index, ResultSet rs, StatementContext sc)
				throws SQLException {
			// TODO Auto-generated method stub
			return new UvSumInfo(rs.getString("referer_name"),
					rs.getInt("is_paid"), rs.getInt("count"),rs.getTimestamp("date"));
		}
	}
}
