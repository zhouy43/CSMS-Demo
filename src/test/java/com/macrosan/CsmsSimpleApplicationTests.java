package com.macrosan;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsmsSimpleApplicationTests {
	@Autowired
	private DataSource datasource;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testMySQL() {
		try {
			Connection connection = datasource.getConnection();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
