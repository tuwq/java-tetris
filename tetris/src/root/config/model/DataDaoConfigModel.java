package root.config.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * 数据源配置对象
 * @author tuwq
 */
public class DataDaoConfigModel {
	
	private final String className;
	
	private final Map<String, String> paramMap;
	
	public DataDaoConfigModel(Element daoImpl) {
		this.className = daoImpl.attributeValue("className");
		this.paramMap = new HashMap<String, String>();
		List<Element> params = daoImpl.elements("param");
		for (Element param : params) {
			this.paramMap.put(param.attributeValue("key"), param.attributeValue("value"));
		}
	}

	public String getClassName() {
		return className;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}
	
}
