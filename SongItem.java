
public class SongItem extends Song {
	private boolean played;
	public SongItem(String title, int level) {
		super(title, level);
		played = false;
	}
	
	public SongItem(Song me) {
		super(me.getName(), me.getLevel());
		played = false;
	}
	
	public boolean isPlayed() {
		return played;
	}
	
	public void play() {
		played = true;
	}
	
	public void reset() {
		played = false;
	}

}
