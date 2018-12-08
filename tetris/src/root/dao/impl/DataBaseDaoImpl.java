package root.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import root.dao.DataDao;
import root.dto.PlayerDto;

/**
 * 数据访问-数据库
 * @author tuwq
 */
public class DataBaseDaoImpl implements DataDao {
	
	private final String driver ;
	private final String url;
	private final String username;
	private final String password;
	private static final String LOAD_DATA_SQL = "SELECT user_name, point FROM user_point ORDER BY point desc LIMIT 5";
	private static final String SAVE_DATA_SQL = "INSERT INTO user_point(user_name,point) values(?,?)";

	
	public DataBaseDaoImpl(HashMap<String, String> paramMap) {
		this.driver = paramMap.get("driver");
		this.url = paramMap.get("url");
		this.username = paramMap.get("username");
		this.password = paramMap.get("password");
		try {
			Class.forName(driver);
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