package com.tanmoy.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.tanmoy.dao.SimpleDAO;

@Repository
public class SimpleDAOImpl implements SimpleDAO {

	@Qualifier("jdbcTemplateforQuartz")
	@Autowired(required = true)
	private NamedParameterJdbcOperations jdbcTemplateforQuartz;

	@Autowired(required = true) /* Find the SQL in SQLContext.xml as a bean*/
	private String insertIntoQuartzTab ;

	@Override
	public int createJob() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("note", "I AM THE BEST");
		int a= jdbcTemplateforQuartz.update(insertIntoQuartzTab, namedParameters);
		return a;
	}
}