package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBManager {
	static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:cvdms.db");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return connection;
	}

	static public Optional<Integer> insert(String sql, Object... params) throws SQLException {
		var ps = prepareStatement(sql, params);
		ps.execute();
		
//		ResultSet rs1 = ps.getGeneratedKeys();
//		if(rows == 1) {
			String autoKeySql = "select * from last_insert_rowid()";
			ps = prepareStatement(autoKeySql);
			ResultSet rs =  ps.executeQuery();
			
			if (rs.next()) {
				return Optional.of(rs.getInt(0));
			}
//		}
		
		return Optional.empty();
	}

	static public <T> List<T> getAll(String sql, Result2ModelConverter<T> converter, Object... params)
			throws SQLException {
		var ps = prepareStatement(sql, params);
		
		var rs = ps.executeQuery();

		List<T> list = new ArrayList<>();
		while (rs.next()) {
			list.add(converter.convert(rs));
		}

		return list;
	}

	static public <T> Optional<T> getById(String sql,  Result2ModelConverter<T> converter, Integer id)
			throws SQLException {
		var ps = prepareStatement(sql, id);
		var rs = ps.executeQuery();
		
		T res = null;
		if (rs.next()) {
			res = converter.convert(rs);
		}

		return Optional.ofNullable(res);
	}

	
	/* @
	 * 
	 * */
	static public void execute(String sql, Object... params) throws SQLException {
		var ps = prepareStatement(sql, params);
		ps.executeUpdate(sql);
	}
	
	static private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
		var conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		setParameters(ps, params);

		return ps;
	}

	static private void setParameters(PreparedStatement ps, Object... params) throws SQLException {
		int i = 1;
		for (Object arg : params) {
			if (arg instanceof LocalDate) {
				ps.setTimestamp(i++, Timestamp.valueOf(((LocalDate) arg).atStartOfDay()));
			} else if (arg instanceof Integer) {
				ps.setInt(i++, (Integer) arg);
			} else if (arg instanceof Long) {
				ps.setLong(i++, (Long) arg);
			} else if (arg instanceof Double) {
				ps.setDouble(i++, (Double) arg);
			} else if (arg instanceof Float) {
				ps.setFloat(i++, (Float) arg);
			} else {
				ps.setString(i++, (String) arg);
			}
		}
	}
}
