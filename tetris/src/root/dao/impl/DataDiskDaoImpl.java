package root.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import root.dao.DataDao;
import root.dto.PlayerDto;

/**
 * 数据访问-本地磁盘
 * @author tuwq
 */
public class DataDiskDaoImpl implements DataDao {

	private static final String FILE_PATH = "data/diskRecode.dat"; 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PlayerDto> loadData() {
		ObjectInputStream ois = null;
		List<PlayerDto> players = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(FILE_PATH)));
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
		return players;
	}

	@Override
	public void saveData(List<PlayerDto> players) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH)));
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

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataDiskDaoImpl dataDiskDao = new DataDiskDaoImpl();
		List<PlayerDto> players = new ArrayList<PlayerDto>();
		/*players.add(new PlayerDto("汤姆", 213));
		players.add(new PlayerDto("杰瑞", 221));
		players.add(new PlayerDto("李华", 112));
		players.add(new PlayerDto("韩梅梅", 121));*/
		players.add(new PlayerDto("作者", 1203));
		dataDiskDao.saveData(players);
		System.out.println("保存完毕");
		List<PlayerDto> dataFromDisk = dataDiskDao.loadData();
		for (PlayerDto playerDto : dataFromDisk) {
			System.out.println(playerDto.getName() + "::" + playerDto.getPoint());
		}
	}
}
