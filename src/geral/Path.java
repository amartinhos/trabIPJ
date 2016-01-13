package geral;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Path {
	private static ObjectOutputStream write;
	private static String path;
	private static ObjectInputStream read;

	
	public static void setPath(String path) {
		Path.path = path;
	}
	
	

	public static String getPath() {
		return path;
	}



	public static void write(Object b)  {
		try {
			write = new ObjectOutputStream(new FileOutputStream(path));
			write.writeObject(b);
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Object read()  {
		Object b;
		
		try {
			read = new ObjectInputStream(new FileInputStream(path));
			b = read.readObject();
			read.close();
			return b;
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Nao foi possivel ler do ficheiro");
			return null;
		}				
	}

}
