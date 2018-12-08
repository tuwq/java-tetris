package root.dto;

import java.io.Serializable;
import java.util.Comparator;

public class PlayerDto implements Comparable<PlayerDto>, Serializable {
	
	private String name;
	
	private int point;

	public PlayerDto(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(PlayerDto o) {
		return o.point - this.point;
	}
	
}
