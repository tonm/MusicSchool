import java.io.*;

import java.io.IOException;

public abstract class Persistent {
	private FileReader file_in;
	private FileWriter file_out;
	private String file_name;
	
	public Persistent(String file_path, boolean append) {
		
		file_name = file_path;
		try {
			file_out = new FileWriter(file_path, append);
			file_in = new FileReader(file_path);
			
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
		char[] temp = new char[2500];
		int len = 0;
		
				
		try {
			len = file_in.read(temp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return new String(temp,0,len);
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
