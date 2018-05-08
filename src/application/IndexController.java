package application;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import application.IndexModel;

public class IndexController {
	@FXML
	private TextField imagePath;
	
	@FXML
	private ImageView ImageChooser;
	
	@FXML
	private ImageView selectedImageView;
	
	@FXML
	private TextArea resultText; 
	
	@FXML
	public void chooseImage(MouseEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("JPG files","*.jpg"));
		File selectedFile = fc.showOpenDialog(null);
		imagePath.setText(selectedFile.getAbsolutePath());
	
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream(selectedFile.getAbsolutePath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		Image image = new Image(inputstream);
		selectedImageView.setImage(image);
		IndexModel im  = new IndexModel(); 
		resultText.setText(im.textify(selectedFile));
		
	}
	
}
