package root.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import root.dao.DataDao;
import root.dto.PlayerDto;

/**
 * 不连接数据库的数据
 * @author tuwq
 */
public class DataDefaultDaoImpl implements DataDao {

	
	public DataDefaultDaoImpl(HashMap<String, String> paramMap) {
		
	}
	
	@Override
	public List<PlayerDto> loadData() {
		List<PlayerDto> players = new ArrayList<PlayerDto>();
		players.add(new PlayerDto("NoData", 0));
		players.add(new PlayerDto("NoData", 0));
		players.add(new PlayerDto("NoData", 0));
		players.add(new PlayerDto("NoData", 0));
		players.add(new PlayerDto("NoData", 0));
		return players;
	}

	@Override
	public void saveData(PlayerDto playerDto) {
		
	}

}
