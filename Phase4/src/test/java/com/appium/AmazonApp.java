package com.appium;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AmazonApp {

	static AndroidDriver<MobileElement> driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Create driver instance");
		//    URL
		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		//    Desired Capability
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		//      AndroidDriver
		driver = new AndroidDriver<MobileElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		SessionId sessionId = driver.getSessionId();
		System.out.println(sessionId);
		Thread.sleep(1000);
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {

		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("panasonic earphones");
		Thread.sleep(2000);

		List<MobileElement> searchDd = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text"));
		System.out.println(searchDd.size());
		searchDd.get(1).click();

		MobileElement parentSearch = driver.findElement(By.xpath("//android.view.View[@resource-id='search']"));

		List<MobileElement> searchProds = parentSearch.findElements(By.xpath(".//android.view.View"));
		System.out.println(searchProds.size());

		searchProds = driver .findElements(By.xpath("//android.view.View[@resource-id='search']//android.view.View"));
		System.out.println(searchProds.size());

		List<MobileElement> lstViews = driver.findElements(By.xpath("//android.widget.ToggleButton[@resource-id='s-all-filters']//parent::android.view.View/following-sibling::android.view.View"));

		lstViews = driver.findElements(By.xpath("//android.widget.ToggleButton[@resource-id='s-all-filters']//parent::android.view.View//following-sibling::android.view.View"));
		for (MobileElement view : lstViews) {
			List<MobileElement> lstChild = view.findElements(By.xpath(".//android.view.View"));
			if (lstChild.size()>2) {
				String strText = lstChild.get(2).getAttribute("content-desc");
				System.out.println(strText);
//				view.click();
//				break;

			}
		}

	}
}


