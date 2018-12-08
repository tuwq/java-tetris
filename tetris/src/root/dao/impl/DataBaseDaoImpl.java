package root.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import root.dao.DataDao;
import root.dto.PlayerDto;

/**
 * 数据访问-数据库
 * @author tuwq
 */
public class DataBaseDaoImpl implements DataDao {
	
	// mysql5.7
	// private static String driverClassName = "com.mysql.jdbc.Driver";
	// private static String url = "jdbc:mysql://127.0.0.1:3306/tetris?characterEncoding=UTF-8&useSSL=false";
	// mysql8.0
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/tetris?characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
	private String username = "root";
	private String password = "1234";
	private final String LOAD_DATA_SQL = "SELECT user_name, point FROM user_point ORDER BY point desc LIMIT 5";
	private final String SAVE_DATA_SQL = "INSERT INTO user_point(user_name,point) values(?,?)";
	
	public DataBaseDaoImpl() {
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取前五玩家记录
	 * @return
	 */
	@Override
	public List<PlayerDto> loadData() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PlayerDto> players = new ArrayList<PlayerDto>();
		try {
			connection = DriverManager.getConnection(url, username, password);
			ps = connection.prepareStatement(LOAD_DATA_SQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				players.add(new PlayerDto(rs.getString(1), rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (ps != null) ps.close();
				if (rs != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return players;
	}

	/**
	 * 保存玩家记录
	 * @param playerDto
	 */
	@Override
	public void saveData(PlayerDto playerDto) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			ps = connection.prepareStatement(SAVE_DATA_SQL);
			ps.setObject(1, playerDto.getName());
			ps.setObject(2, playerDto.getPoint());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (ps != null) ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}