package root.config;

import java.util.List;

import org.dom4j.Element;

import root.config.model.DataDaoConfigModel;

/**
 * 读取数据配置文件
 * @author tuwq
 *
 */
public class DataConfig {
	
	private final boolean openDataBase;
	private final int maxRow;
	private final DataDaoConfigModel dbDaoImpl;
	private final DataDaoConfigModel diskDaoImpl;
	private final DataDaoConfigModel defaultDaoImpl;
	
	public DataConfig(Element data) {
		this.openDataBase = Boolean.valueOf(data.attributeValue("openDataBase"));
		this.maxRow = Integer.parseInt(data.attributeValue("maxRow"));
		dbDaoImpl = new DataDaoConfigModel(data.element("dbDaoImpl"));
		diskDaoImpl = new DataDaoConfigModel(data.element("diskDaoImpl"));
		defaultDaoImpl = new DataDaoConfigModel(data.element("defaultDaoImpl"));
	}

	public boolean isOpenDataBase() {
		return openDataBase;
	}
	
	public int getMaxRow() {
		return maxRow;
	}

	public DataDaoConfigModel getDbDaoImpl() {
		return dbDaoImpl;
	}

	public DataDaoConfigModel getDiskDaoImpl() {
		return diskDaoImpl;
	}

	public DataDaoConfigModel getDefaultDaoImpl() {
		return defaultDaoImpl;
	}
	
}
