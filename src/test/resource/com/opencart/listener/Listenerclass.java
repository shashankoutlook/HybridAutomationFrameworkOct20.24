package com.opencart.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.opencart.listener.*;

public class Listenerclass implements ITestListener {
	
	 ExtentReports extentReport;
	    ExtentTest extentTest;
	    String testName;
    @Override
    public void onStart(ITestContext context){
    	extentReport=ExtentReport.generateExtentReporter();
    }
    @Override
    public void onTestStart(ITestResult result) {
     testName= result.getName();
         extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO,testName+" Started executing ");
    }
    @Override
    public  void onTestSuccess(ITestResult result) {
        //Already declared testName thats why we are cancelling it in all cases.

         //testName=result.getName();
         extentTest.log(Status.PASS,testName+" got successfully executed ");

    }

    @Override
    public  void onTestFailure(ITestResult result) {
         //testName=result.getName();
        WebDriver driver=null;
        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
      String destinationScreenShotPath= Utilities.captureScreenShot(driver,result.getName());

        extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName+"got failed ");
    }
    @Override
    public void onTestSkipped(ITestResult result){
        testName=result.getName();
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.SKIP,testName+" got skipped ");

    }
    @Override
    public void onFinish(ITestContext context){
        extentReport.flush();
        String pathOfExtentReport=System.getProperty("user.dir")+"\\test-Output\\ExtentReports\\Extentreport.html";
        File extentReport=new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
