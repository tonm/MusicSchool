import java.util.*;

public class PlayList {
	private List<SongItem> songItems;
	
	public PlayList(Song[] to_play) {
		for(Song s : to_play) {
			addSong(s);
		}
	}
	
	public List<SongItem> getPlayList() {
		return songItems;
	}
	
	public PlayList() {
		songItems = new ArrayList<SongItem>();
	}
	
	public void addSong(Song newSong) {
		songItems.add(new SongItem(newSong.getName(), newSong.getLevel()));
	}
	
	public void addSong(ArrayList<Song> newSongs) {
		for(Song s : newSongs) {
			addSong(s);
		}
	}

	public String toString() {
		String retContents = new String();
		for(SongItem s : songItems) {
			retContents += s.getName() + "^" + s.getLevel() + "~";
		}
		
		return retContents;
	}
	
	public int toPlay() {
		int toPlay = 0;
		for (SongItem s : songItems) {
			if(!s.isPlayed()) {
				toPlay++;
			}
		}
		return toPlay;
	}
	
	public void reset() {
		for (SongItem s : songItems) {
			s.reset();
		}
	}
}
