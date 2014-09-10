package com.kaikeba.data.api.dashboard.dao;



import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.TrendByHourInfo;
import com.kaikeba.data.api.dashboard.representations.TrendByHourInfo.TrendByHourMapper;


public interface TrendByHourDao {

	@Mapper(TrendByHourMapper.class)
	@SqlQuery("select * from dashboard.dashboard_time_trend_by_hour where date_hour>=:sTime and date_hour<:eTime")
	List<TrendByHourInfo>  getTrendByHour(@Bind("sTime") Date sTime ,@Bind("eTime") Date eTime);
}
