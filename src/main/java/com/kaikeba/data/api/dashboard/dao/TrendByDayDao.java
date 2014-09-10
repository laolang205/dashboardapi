package com.kaikeba.data.api.dashboard.dao;

import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.TrendByDayInfo;
import com.kaikeba.data.api.dashboard.representations.TrendByDayInfo.TrendByDayMapper;

public interface TrendByDayDao {

@Mapper(TrendByDayMapper.class)
@SqlQuery("select * from dashboard.dashboard_time_trend_by_day where day>=:sTime and day<:eTime")
 List<TrendByDayInfo>  getTrendbyDay(@Bind("sTime") Date sTime ,@Bind("eTime") Date eTime);
}
