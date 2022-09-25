package project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Testcases {
	private AndroidDriver driver;
	TouchAction touchAction;


	@BeforeMethod

	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "11");
		desiredCapabilities.setCapability("appium:deviceName ", "Android SDK built for x86");
		desiredCapabilities.setCapability("appium:app", "C:\\Appium-Android\\ApiDemos-debug.apk");

		desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		desiredCapabilities.setCapability("appium:newCommandTimeout", 0);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		touchAction = new TouchAction(driver);

		//driver.manage().timeouts().implicitlyWait(, TimeUnit.SECONDS);
	}
	@Test
	public void AddTextViewTest() {

		MobileElement el1 = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[@content-desc=\"Text\"]");
		el1.click();
		MobileElement el2 = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[@content-desc=\"LogTextBox\"]\r\n");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementByXPath(
				"	\r\n" + "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"
						+ "/android.widget.LinearLayout/android.widget.TextView");
		el3.click();
		el3.sendKeys("Welcome to Automation world ---- All the Best");

	}
	public MobileElement getTextViewByContentDesc(String contentDesc) {

		return (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[@content-desc=\"" + contentDesc + "\"]");
	}

	public void SwipeUpLong() {

		touchAction.press(PointOption.point(930, 2200)).moveTo(PointOption.point(930, 432)).release().perform();
	}

	public void SwipeUpSmall() {
		touchAction.press(PointOption.point(930, 2200)).moveTo(PointOption.point(930, 1900)).release().perform();

	}

	@Test
	public void SampleTest() {

		getTextViewByContentDesc("Views").click();

		SwipeUpLong();
		SwipeUpLong();
		SwipeUpSmall();

		getTextViewByContentDesc("Seek Bar").click();

		// io.appium.android.apis:id/seek

		MobileElement seekBar = (MobileElement) driver.findElementById("io.appium.android.apis:id/seek");

		int startXPos = seekBar.getLocation().getX();
		int yPos = seekBar.getLocation().getY();
		int lastXPos = startXPos + seekBar.getSize().getWidth();
		int centerXPos = seekBar.getCenter().getX();
		System.out.println("startXPos= " + startXPos);
		System.out.println("yPos= " + yPos);
		System.out.println("lastXPos= " + lastXPos);
		System.out.println("centerXPos= " + centerXPos);

		// move to start position = 0
		touchAction.press(ElementOption.element(seekBar)).moveTo(PointOption.point(startXPos, yPos)).release()
				.perform();

		// move to last position
		touchAction.press(ElementOption.element(seekBar)).moveTo(PointOption.point(lastXPos, yPos)).release().perform();

		// move to Center position
		touchAction.press(ElementOption.element(seekBar)).moveTo(PointOption.point(centerXPos, yPos)).release()
				.perform();

		// move to desire position = ex 30
		int delta= 40;
		int point30 =30*(startXPos+seekBar.getSize().getWidth() /100)+delta;
		touchAction.press(ElementOption.element(seekBar)).moveTo(PointOption.point(point30, yPos)).release()
				.perform();
		System.out.println("Desire point= "+point30);
	}


	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(500);
		driver.quit();
	}

}
