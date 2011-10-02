import java.util.List;
import java.util.ArrayList;

public class SongDB extends Persistent {
	private List<Song> songs;

	public SongDB(String filePath) {
		super(filePath);
	}

    public List<Song> getSongs() {
        return songs;
    }

    public List<Song> getSongsByName(String name) {
        List<Song> tmp = new ArrayList<Song>();
        for (Song s : songs) {
            if (s.getName() == name) {
                tmp.add(s);
            }
        }
        return tmp;
    }

    public List<Song> getSongsByLevel(int level) {
        List<Song> tmp = new ArrayList<Song>();
        for (Song s : songs) {
            if (s.getLevel() == level) {
                tmp.add(s);
            }
        }
        return tmp;
    }


	public void fromFile() {
		
	}

	public void toFile() {
		
	}
}
