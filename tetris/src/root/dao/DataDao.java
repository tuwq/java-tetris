package root.dao;

import java.util.List;

import root.dto.PlayerDto;

/**
 * 数据持久化接口
 * @author tuwq
 *
 */
public interface DataDao {
	/**
	 * 读取数据
	 * @return
	 */
	List<PlayerDto> loadData();
	/**
	 * 存储数据
	 * @param players
	 */
	void saveData(PlayerDto playerDto);
}
