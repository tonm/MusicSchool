
public class Song {
	protected String name;
	protected int level;
	
	public Song(String name, int level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String toString() {
		return (name + ".L" + Integer.toString(level));
	}
}
