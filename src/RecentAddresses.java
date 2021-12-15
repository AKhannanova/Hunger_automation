import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class RecentAddresses {


    private AppiumDriver driver;

    private boolean android;

    public static String NO_VALUE = null;


    //Welcome screen
    public static final String welcome_great_button = "de.ordersmart.portal:id/greatButton";
    //Location access permission window
    public String loc_per_deny_button = "com.android.packageinstaller:id/permission_deny_button";
    public String loc_per_allow_button = "com.android.packageinstaller:id/permission_allow_button";
    //"Delivery address" screen
    public String del_add_back_button = "de.ordersmart.portal:id/backButton";
    public String del_add_header = "de.ordersmart.portal:id/header";
    public String del_add_empty_result = "de.ordersmart.portal:id/emptyResults";
    public String del_add_edit_text = "de.ordersmart.portal:id/editText";
    public String del_add_search_icon = "de.ordersmart.portal:id/searchIcon";
    public String del_add_clear_text_button = "de.ordersmart.portal:id/clearTextButton";
    public String del_add_location_icon = "de.ordersmart.portal:id/locationIcon";
    public String del_add_search_result_list = "de.ordersmart.portal:id/searchResultList";
    public String del_add_recent_list = "de.ordersmart.portal:id/recentList";
    public String del_add_confirm_button = "de.ordersmart.portal:id/confirmAddressButton";
    //Branches list screen
    public String branches_postal_code = "de.ordersmart.portal:id/postalCodeTextView";

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "platformName");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "de.ordersmart.portal");//"org.wikipedia");
        capabilities.setCapability("appActivity", ".framework.app.ui.MainActivity");//".main.mainActivity");
        capabilities.setCapability("app", "C:\\Users\\user\\Hunger_automation\\apks\\hunger_old.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        android = true;

    }


    @After
    public void tearDown(){
        driver.quit();
    }


    @Test
    //Make sure that confirm address appears in the "Recent address" list
    public void firstTest(){
        /*
        1. Launch application.
        2. Click the "Great" button on the welcome screen.
        3. Allow permission on the location access permission.
        4. Clear the "Delivery address" field.
        5. Enter a full address.
        6. Click the prompt.
        7. Click the "Confirm address" button.
        8. Back from the restaurants list window.
        9. Make sure that the address is on the "Recent addresses" list
         */

        String entering_address = "Berlin rosenstrase 18";  //address entering to thr "Delivery address" field
        String address_prompt = "10178 Berlin, Rosenstraße 18"; //prompt for the entered address which is clicked

        //Click the "Great" button on the Welcome screen
        clickGreatButtonOnWelcomeScreen();

        //Click the "Allow" button on the window with location access permission
        clickAllowAccessPermission();

        //Enter and confirm an address
        enterAndConfirmAddress(entering_address, address_prompt);

        //Click the postal code on the restaurants list window
        clickPostalCode();

        //Clear the "Delivery address" field on the address specifying window
        clearDeliveryAddressByCross();

        //Make sure that the address is added to the "Recent addresses" list
        recentAddressPresented(address_prompt);

    }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timepoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timepoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeputInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeputInSeconds);
        element.sendKeys(value);;
        return element;
    }




    //Click the "Great" button on the Welcome screen
    private void clickGreatButtonOnWelcomeScreen() {
        waitForElementAndClick(
                By.id(ElementConst.WELCOME_GREAT_BUTTON.getConstBy(android)),
                "Cannot find 'Great' button",
                15
        );
    }

    //Click the "Allow" button on the window with location access permission
    private void clickAllowAccessPermission(){
        waitForElementAndClick(
                By.id(loc_per_allow_button),
                "Cannot find 'Allow' button",
                15
        );
    }

    //Clear the "Delivery address" field on the address specifying window
    private void clearDeliveryAddressByCross(){
        waitForElementAndClick(
                By.id(del_add_clear_text_button),
                "Cannot find the cross button",
                15
        );
    }

    //Enter an address to the "Delivery address" field on the address specifying window
    private void enterAddressToDeliveryAddress(String entering_address){

        waitForElementAndSendKeys(
                By.id(del_add_edit_text),
                entering_address,
                "Cannot find the 'Delivery address' field",
                15
        );
    }

    //Click the address prompt on the address specifying window
    private void clickAddressPrompt(String address_prompt){
        waitForElementAndClick(
                By.xpath("//*[@resource-id='" + del_add_search_result_list + "']//*[@text='" + address_prompt + "']"),
                "Cannot find the '" + address_prompt + "' prompt",
                15
        );
    }

    //Click the "Confirm button" ob the address specifying window
    private void clickConfirmButton(){
        waitForElementAndClick(
                By.id(del_add_confirm_button),
                "Cannot find the 'Confirm address' button",
                15
        );
    }

    //Click the postal code on the restauratns list windowa
    private void clickPostalCode(){
        waitForElementAndClick(
                By.id(branches_postal_code),
                "Cannot find the postal code",
                20
        );
    }

    //Make sure that an address is in the "Recent addresses" list and check that it is in the list
    private void recentAddressPresented(String recent_address){
        waitForElementPresent(
                By.xpath("//*[@resource-id='" + del_add_recent_list + "']//*[@text='" + recent_address + "']"),
                "Cannot find the recent address '" + recent_address + "'" ,
                15
        );
    }

    //Add an address to the "Recent Addresses" list
    private void enterAndConfirmAddress(String entering_address, String address_prompt){
        //Clear the "Delivery address" field on the address specifying window
        clearDeliveryAddressByCross();

        //Enter a location address to the "Delivery address" field  on the address specifying window
        enterAddressToDeliveryAddress(entering_address);

        //Click the prompt with full address on the address specifying window
        clickAddressPrompt(address_prompt);

        //Click the 'Confirm address' button on the address specifying window
        clickConfirmButton();
    }

}
