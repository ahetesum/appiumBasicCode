import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MapTest
{
    public static final int ONE_SECONDS = 1000;
    public static final int TWO_SECONDS = 2000;
    public static final int FIVE_SECONDS = 5000;
    public static final int TEN_SECONDS = 10000;
    private AppiumDriver driver=null;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities= new DesiredCapabilities();

        capabilities.setCapability("deviceName","TestDevice");
        capabilities.setCapability(CapabilityType.PLATFORM,"Android");
        capabilities.setCapability("platformVersion","6");
        capabilities.setCapability("appPackage", "app.shiveh.ir.map.shivehmap");

        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("appWaitActivity", "app.shiveh.ir.map.shivehmap.screens.intro.IntroActivity");

        File apkFile=new File("/home/ahetesum/TestApk","map.apk");

        capabilities.setCapability("app",apkFile.getAbsolutePath());


        driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() throws Exception
    {

        if (driver!=null) {
            Thread.sleep(FIVE_SECONDS);
            driver.quit();
        }
    }

    @Test
    public void mapTest() throws Exception
    {

        if(driver!=null) {

            introScreenFunctionalityTest();

            mainPageFunctionalityTest();

        }
        else
        {
            System.out.print("Driver is null");
        }
    }

    private void mainPageFunctionalityTest()  throws Exception
    {
        Thread.sleep(ONE_SECONDS);

        MobileElement burgerMenuButton= (MobileElement)driver.findElement(By.id("drawer_menu_MaterialMainView"));
        burgerMenuButton.click();

        Thread.sleep(ONE_SECONDS);

        MobileElement loginNavButton= (MobileElement)driver.findElement(By.id("drawer_profile_layout"));
        loginNavButton.click();

        Thread.sleep(FIVE_SECONDS);

        MobileElement userNameEditText=(MobileElement)driver.findElement(By.id("login_username_edit"));
        userNameEditText.sendKeys("son");
        MobileElement passwdEditText=(MobileElement)driver.findElement(By.id("login_password_edit"));
        passwdEditText.sendKeys("123456789");

        MobileElement loginButton= (MobileElement)driver.findElement(By.id("login_submit_btn"));
        loginButton.click();

        Thread.sleep(TWO_SECONDS);


        burgerMenuButton.click();
        Thread.sleep(TWO_SECONDS);


        MobileElement usernameTextView=(MobileElement)driver.findElement(By.id("drawer_name_text"));
        String loginNameGot=usernameTextView.getText();


        String loginName="son";
        Assert.assertEquals(loginName,loginNameGot);

        MobileElement bookMarkNavButton= (MobileElement)driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='مپ نشونه']"));
        bookMarkNavButton.click();

        Thread.sleep(FIVE_SECONDS);

        MobileElement bookMarkTitleTextView= (MobileElement)driver.findElement(By.xpath("//android.widget.TextView[@text='مپ نشونه']"));
        bookMarkNavButton.click();

        MobileElement logoutButton= (MobileElement)driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='خروج']"));
        logoutButton.click();

        Thread.sleep(ONE_SECONDS);





    }

    private void introScreenFunctionalityTest() throws Exception
    {
        Thread.sleep(ONE_SECONDS);

        MobileElement firstIntroTextView=(MobileElement)driver.findElement(By.id("textViewIntroTitle"));
        String firstIntroText=firstIntroTextView.getText();
        Assert.assertEquals("مپ گردی",firstIntroText);

        Thread.sleep(ONE_SECONDS);

        MobileElement introSkipButton =(MobileElement)driver.findElement(By.id("buttonIntroStartApp"));
        introSkipButton.click();
    }


}
