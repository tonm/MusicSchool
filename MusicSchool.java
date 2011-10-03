import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MusicSchool {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Music School");
		
		Classroom paddedWalls = new Classroom(null); // CLASSROOM EXPECTS LIST OF STUDENTS!
		SongDB songData = new SongDB("songs.txt");
		Instructor teacher = new Instructor("Glenn Holland", paddedWalls);

		
		boolean contSim = true;
		
		do {
			System.out.println("How many weeks should the simulation run?: ");
			int duration = getStdInput();
			
			Clock timer = Clock.getInstance();
			timer.start(duration);
			
			System.out.println("Would you like to repeat the simulation? (y/n): ");
			char repeat = (char) System.in.read();
			if(repeat == 'n') {
				contSim = false;
			}
		} while(contSim);
		
		
		
		
		
	}
	
	public static int getStdInput() {
		int duration = 0;
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			duration = Integer.parseInt(is.readLine());
		} catch (NumberFormatException ex) {
			System.err.println("Invalid number. You fail at life.");
		} catch (IOException e) {
			System.err.println("Input failure.  You failer.");
		}
		return duration;
	}
}
