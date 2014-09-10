package com.kaikeba.data.api.dashboard.representations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserInfo {

	private final int id;
	private final String username;
	private final String password;
	private final int userid;
	private final int pause;
	private final Date createtime;
	private final Date updatetime;

	public static class UserMapper implements ResultSetMapper<UserInfo> {

		@Override
		public UserInfo map(int index, ResultSet rs, StatementContext sc)
				throws SQLException {
			// TODO Auto-generated method stub
			return new UserInfo(rs.getInt("id"), rs.getString("username"),
					rs.getString("password"), rs.getInt("userid"),
					rs.getInt("pause"), rs.getTimestamp("createtime"),
					rs.getTimestamp("updatetime"));
		}

	}

}
