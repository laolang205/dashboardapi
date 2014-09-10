package com.kaikeba.data.api.dashboard.dao;

import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.kaikeba.data.api.dashboard.representations.UvSumInfo;
import com.kaikeba.data.api.dashboard.representations.UvSumInfo.UvInfoMapper;

public interface UvDao {

	 @Mapper(UvInfoMapper.class)
	 @SqlQuery("select count(1) as count ,is_paid, referer_name,min(visit_first_action_time) as date  from dwsuperclass.dw_piwik_visit where visit_first_action_time>=:sTime and visit_first_action_time<:eTime group by  referer_name")
	 List<UvSumInfo> getUvSumList(@Bind("sTime") Date sTime,
	 @Bind("eTime") Date eTime);

	@Mapper(UvInfoMapper.class)
	@SqlQuery("select count(1) as count ,is_paid, referer_name,DATE_FORMAT(`visit_first_action_time`,'%Y-%m-%d') as date from dwsuperclass.dw_piwik_visit where visit_first_action_time>=:sTime and visit_first_action_time<:eTime group by DATE_FORMAT(`visit_first_action_time`,'%Y-%m-%d'), referer_name")
	List<UvSumInfo> getUvDaySumList(@Bind("sTime") Date sTime,
			@Bind("eTime") Date eTime);
}
