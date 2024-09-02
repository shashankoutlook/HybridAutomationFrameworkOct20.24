package com.opencart.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ExtentReport {
	
	public static ExtentReports generateExtentReporter(){
		
        ExtentReports extentRepo=new ExtentReports();
        
        /*https://extentreports.com/docs/versions/5/java/index.html
         * official site for extent report
         * doc-java-getting started-reporters
         * Using extent spark reporter
         * extent report folder not created it should be created automatically.
         * for theme selection for complete example.
         * Set Theme==spark.config().setTheme(Theme.DARK);
         *  Set ReportName=        spark.config().setReportName("OpenCart2024 ");
         *  Set Documenttype=        spark.config().setDocumentTitle("Hybrid Automation Opencart report");
         *  Setting in timestampformat==        spark.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
         *  	public static void generateExtentReport()===>to return in place of void need write ExtentReports
         *  
         */

        File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\Extentreport.html");
       
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("OpenCart2024");
        sparkReporter.config().setDocumentTitle("Opencart report");
        sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
        //Spark report is attaching to extent report
        
        extentRepo.attachReporter(sparkReporter);
        
        //Using Config properties for url
        
        Properties configProp=new Properties();
        
        File configPropfile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\Config\\Config.properties");
        try {
            FileInputStream fisConfigprop = new FileInputStream(configPropfile);
            configProp.load(fisConfigprop);
        }catch (Throwable e){
            e.printStackTrace();
        }
        extentRepo.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentRepo.setSystemInfo("BrowserName",configProp.getProperty("browserName"));
        extentRepo.setSystemInfo("Email",configProp.getProperty("validEmail"));
        extentRepo.setSystemInfo("Password",configProp.getProperty("validPassword"));
        extentRepo.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentRepo.setSystemInfo("Username",System.getProperty("user.name"));
        extentRepo.setSystemInfo("Java Version",System.getProperty("java.version"));

        return extentRepo;
    }
}
