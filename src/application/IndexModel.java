package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import net.sourceforge.tess4j.*;



public class IndexModel {
	static String directory;
	String input_path;
	static File output_temp;
	String output_temp_file;
	String tesseract_install_path;
	static String result = null;
	static File image;
	public String textify(File image) {
		this.image=image;
		this.directory = image.getParent();
		input_path  = image.getAbsolutePath();
		output_temp = new File(directory+"\\temp.txt");
		output_temp_file=output_temp+"temp";
		tesseract_install_path="C:\\Program Files (x86)\\Tesseract-OCR\\tesseract";
		result = null;
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
			    } catch (Exception e) {
			 e.printStackTrace();
			    }
		return result;
		
		
		//doOCR for tesseract
/*      
 * 		File image = new File("C:/bin/Tess4J/eurotext.tif");
        Tesseract tessInst = new Tesseract();
        tessInst.setDatapath("C://bin//Tess4J");
        try {
            String result= tessInst.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }*/
	}
	
	public void saveModel(String directory) throws IOException {
		FileWriter writer = null;
		try {
			output_temp = new File(directory+"\\"+image.getName()+".txt");
			writer = new FileWriter(directory+"\\"+image.getName()+".txt", true);
          
            
            writer.write(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			writer.close();
		}
	}
	public void saveAsPdfModel(String directory) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(directory+"\\"+image.getName()+".pdf"));
		document.open();
		
		Anchor anchorTarget = new Anchor("Following is the Text from Image"+image.getName());
	    anchorTarget.setName("BackToTop");
	    Paragraph paragraph1 = new Paragraph();
	    paragraph1.setSpacingBefore(50);
	    paragraph1.add(anchorTarget);
	    document.add(paragraph1);
	    document.add(new Paragraph(result,FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));
	    document.close();
	}
}
