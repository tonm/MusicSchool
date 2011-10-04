import java.util.List;
import java.util.ArrayList;

public class SongDB extends Persistent {
	private List<Song> songs;

	public SongDB(String filePath) {
		super(filePath);
		songs = new ArrayList<Song>(); // FIX: Wasn't initialized.
        fromFile();
	}

    public void addSong(Song s) {
        songs.add(s);
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

    @Override
	public void fromFile() {
        String stored = getStored();

        // FIXED: This will allow our program to work on windows platforms too.
        stored = stored.replace("\r\n", "\n");
        // Split into lines.
        String[] lines = stored.split("\n");

        // For each line, split into song and level. Then create (?) a
        // new instance of that song and place it in the database.
        for (String s : lines) {
            String[] tmp = s.split(",");
            try {
                String name = tmp[0];
                int level = Integer.parseInt(tmp[1]);
                addSong(new Song(name, level));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Malformed SongDB file.");
                System.exit(1);
            } catch (NumberFormatException e) {
                System.out.println("Malformed SongDB file: song level not a number.");
                System.exit(1);
            }
        }
	}

    @Override
	public void toFile() {
		for (Song s : songs) {
            // TODO: Should Store() be made lowercase?
            Store(s.getName() + "," + s.getLevel() + "\n");
        }
	}
}
