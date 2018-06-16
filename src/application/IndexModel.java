package application;

import java.io.File;
import java.io.PrintWriter;

import net.sourceforge.tess4j.*;



public class IndexModel {
	public String textify(File image) {
		String directory = image.getParent();
		String input_path  = image.getAbsolutePath();
		File output_temp = new File(directory+"\\temp.txt");
		String output_temp_file=output_temp+"temp";
		String tesseract_install_path="C:\\Program Files (x86)\\Tesseract-OCR\\tesseract";
		String result = null;
		String[] command =
			    {
			        "cmd",
			    };
		Process p;
		try {
			 p = Runtime.getRuntime().exec(command);
			        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			        new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			        PrintWriter stdin = new PrintWriter(p.getOutputStream());
			        stdin.println("\""+tesseract_install_path+"\""+" \""+input_path+"\""+ " \""+output_temp_file+"\" -l eng");
			        stdin.close();
			        p.waitFor();
			        System.out.println();
			        System.out.println();
			        System.out.println();
			        System.out.println();
			        result = Read_File.read_a_file(output_temp_file+".txt");
			        output_temp.delete();
			    } catch (Exception e) {
			 e.printStackTrace();
			    }
		return result;
	}
}
