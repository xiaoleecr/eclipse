package ShootingGame;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ImageInfo {
	private String name;//图片名称
	private int x1;// 图片X起始点
	private int x2;// 图片X结束点
	private int y1;// 图片Y起始点
	private int y2;// 图片Y结束点
	private int imagewidth;// 图片宽度
	private int imageheight;// 图片高度
	

	public ImageInfo(String name) {
		this.name = name;
		Map<String, String> config = null;
		try {
			config = loadConfig(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		readXML(config);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getImagewidth() {
		return imagewidth;
	}

	public void setImagewidth(int imagewidth) {
		this.imagewidth = imagewidth;
	}

	public int getImageheight() {
		return imageheight;
	}

	public void setImageheight(int imageheight) {
		this.imageheight = imageheight;
	}

	private void readXML(Map<String, String> config) {
		x1 = Integer.parseInt(config.get("x1"));
		x2 = Integer.parseInt(config.get("x2"));
		y1 = Integer.parseInt(config.get("y1"));
		y2 = Integer.parseInt(config.get("y2"));
		imagewidth = Integer.parseInt(config.get("width"));
		imageheight = Integer.parseInt(config.get("width"));
	}

	private Map<String, String> loadConfig(String name) throws Exception {
		try {
			Map<String, String> config = new HashMap<String, String>();
			SAXReader reader = new SAXReader();

			Document doc = reader.read(new File("image.xml"));

			Element root = doc.getRootElement();
			
			Element tag=root.element(name);
			// 获取<config>标签下的所有子元素
			List<Element> list = tag.elements();

			for (Element e : list) {
				String key = e.getName();
				String value = e.getTextTrim();
				config.put(key, value);
			}

			return config;
		} catch (Exception e) {
			throw e;
		}
	}
}
