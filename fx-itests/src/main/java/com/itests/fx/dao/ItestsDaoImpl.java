/**
 * 
 */
package com.itests.fx.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itests.fx.entities.ITESTS_REF;

/**
 * @author jatinma
 *
 */
@Repository
public class ItestsDaoImpl implements ItestsDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ItestsDaoImpl() {
	}

	@Override
	public void saveItests(ITESTS_REF itestsRef) {
		// create the table
		String sql = "INSERT INTO ITESTS_REF VALUES (SQ_ITESTS.nextval, ?, ?, ?)";
		jdbcTemplate.update(sql, itestsRef.getItests_key(), itestsRef.getItests_value(), new java.util.Date());

	}

	@Override
	public String load(String key) {
		String value = (String) jdbcTemplate.queryForObject("SELECT ITESTS_VALUE FROM ITESTS_REF WHERE ITESTS_KEY=?",
				new Object[] { key }, String.class);
		return value;
	}

	@Override
	public void delete() {
		jdbcTemplate.update("Delete from ITESTS_REF");
	}
}
