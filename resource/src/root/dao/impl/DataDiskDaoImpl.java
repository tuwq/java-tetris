package root.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import root.dao.DataDao;
import root.dto.PlayerDto;

/**
 * 数据访问-本地磁盘
 * @author tuwq
 */
public class DataDiskDaoImpl implements DataDao {

	private final String filePath; 
	
	public DataDiskDaoImpl(HashMap<String, String> paramMap) {
		this.filePath = paramMap.get("filePath");
	}
	/**
	 * 读取玩家记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlayerDto> loadData() {
		ObjectInputStream ois = null;
		List<PlayerDto> players = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(filePath)));
			players = (List<PlayerDto>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players == null ? new ArrayList() : players;
	}

	/**
	 * 保存玩家记录
	 */
	@Override
	public void saveData(PlayerDto playerDto) {
		List<PlayerDto> players = new ArrayList();
		// List<PlayerDto> players = this.loadData();
		players.add(playerDto);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
			oos.writeObject(players);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
