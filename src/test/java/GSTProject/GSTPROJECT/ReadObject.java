package GSTProject.GSTPROJECT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadObject {
	Properties p = new Properties();
  @Test
  public Properties getObjectRepository() throws IOException {
	  InputStream stream = new FileInputStream(new File("C:\\Users\\Sarath Nithyananthan\\eclipse-workspace\\GSTPROJECT\\src\\test\\java\\GSTProject\\GSTPROJECT\\Object.properties"));
      //load all objects
      p.load(stream);
       return p;
  }
}
