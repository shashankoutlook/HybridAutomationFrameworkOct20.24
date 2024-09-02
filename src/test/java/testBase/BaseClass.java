package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencart.listener.Utilities;

public class BaseClass {

	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	//log4j
	public Logger logger;
	
	public BaseClass() {
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\Config\\Config.properties");
		
		dataProp=new Properties();
		File datapropfile= new File(System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\testdata.properties");
		try {
		FileInputStream datafis=new FileInputStream(datapropfile);
		dataProp.load(datafis);
		}catch(Throwable e){
			e.printStackTrace();
		}
		try {
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
		

		public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName) 
		{
				logger=LogManager.getLogger(this.getClass());
				
				
				if(browserName.equals("chrome")) {
					driver=new ChromeDriver();
					
				}else if(browserName.equals("firefox")) {
					driver=new FirefoxDriver();
					
				}else if(browserName.equals("edge"))
				{
					driver=new EdgeDriver();
				}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
				driver.get(prop.getProperty("url"));
				return driver;
			}
		}
	

