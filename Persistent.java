import java.io.*;

import java.io.IOException;

public abstract class Persistent {
	private File file;
	private FileReader file_in;
	private FileWriter file_out;
	private String file_name;
	
	public Persistent(String file_path) {
		file = new File(file_path);
		
		file_name = file_path;
		try {
			file_in = new FileReader(file);
			file_out = new FileWriter(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}		
		
	}
	
	public String getFile_name() {
		return file_name;
	}

	public String getStored() {
		char[] temp = new char[1024];
		
		try {
			file_in.read(temp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return new String(temp);
	}
	
	public void Store(String contents) {
		try {
			file_out.write(contents);
			file_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	abstract public void toFile();
	abstract public void fromFile();
	
	
}
