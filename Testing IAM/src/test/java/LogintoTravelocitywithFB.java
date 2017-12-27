import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogintoTravelocitywithFB {
    public WebDriver driver;
    public String travelocityUrl = "http://localhost:8080/travelocity.com";
    WebDriverWait wait;

    @BeforeClass
    public void loginToIS()
    {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://localhost:9443/carbon/");

        WebElement UserName = driver.findElement(By.id("txtUserName"));
        UserName.clear();
        UserName.sendKeys("admin");

        WebElement password = driver.findElement(By.id("txtPassword"));
        password.clear();
        password.sendKeys("admin");

        WebElement loginbtn = driver.findElement(By.cssSelector("input.button"));
        loginbtn.click();

        String actualTitle = driver.getTitle();
        String expectedTitle = "WSO2 Management Console";
        Assert.assertEquals(actualTitle,expectedTitle);
        Assert.assertTrue(driver.getPageSource().contains("Welcome to the WSO2 Identity Server Management Console"));

    }

    @Test(priority = 1)
    public void createSP(){
        wait = new WebDriverWait(driver, 10);
        WebElement addSp = driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[3]/ul/li[8]/ul/li[1]/a"));
        addSp.click();

        WebElement spName = driver.findElement(By.id("spName"));
        spName.clear();
        spName.sendKeys("travelocity.com");

        WebElement spDescription = driver.findElement(By.id("sp-description"));
        spDescription.clear();
        spDescription.sendKeys("This is the Service Provider");


        WebElement registerBtn = driver.findElement(By.xpath(" //*[@id=\"add-sp-form\"]/div[3]/input[1]"));
        registerBtn.click();

        WebElement inboundAuth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app_authentication_head\"]/a")));
        inboundAuth.click();

        WebElement samlAuth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"saml.config.head\"]/a")));
        samlAuth.click();

        WebElement saml_link = wait.until(ExpectedConditions.elementToBeClickable(By.id("saml_link")));
        saml_link.click();

        WebElement issuer = driver.findElement(By.id("issuer"));
        issuer.clear();
        issuer.sendKeys("travelocity.com");

        WebElement assertionConsumerURLTxt = driver.findElement(By.id("assertionConsumerURLTxt"));
        assertionConsumerURLTxt.clear();;
        assertionConsumerURLTxt.sendKeys("http://localhost:8080/travelocity.com");

        WebElement addAssertionConsumerURLBtn = driver.findElement(By.id("addAssertionConsumerURLBtn"));
        addAssertionConsumerURLBtn.click();

        WebElement yesbtn = driver.findElement(By.xpath("/html/body/div[3]/div[2]/button[1]"));
        yesbtn.click();

        WebElement enableAttributeProfileBtn = driver.findElement(By.id("enableAttributeProfile"));
        enableAttributeProfileBtn.click();

        WebElement updateSPBtn = driver.findElement(By.xpath("//*[@id=\"addServiceProvider\"]/table/tbody/tr[2]/td/input[1]"));
        updateSPBtn.click();

        WebElement sucessBtn_spcreate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/button")));
        sucessBtn_spcreate.click();

        WebElement updateBtn_spcreate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"configure-sp-form\"]/div[9]/div/input[1]")));
        updateBtn_spcreate.click();


        String actualSPname = driver.findElement(By.xpath("//*[@id=\"ServiceProviders\"]/tbody/tr/td[1]")).getText();
        String expectedSPname = "travelocity.com";
        Assert.assertEquals(actualSPname,expectedSPname);


    }

    @Test(priority = 0)
    public void createIdpFacebook()
    {
        wait = new WebDriverWait(driver, 10);
        WebElement addIdpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu\"]/ul/li[3]/ul/li[10]/ul/li[1]/a")));
        addIdpBtn.click();

        WebElement idPName = driver.findElement(By.id("idPName"));
        idPName.clear();
        idPName.sendKeys("FacebookIdp");

        WebElement idpDisplayName = driver.findElement(By.id("idpDisplayName"));
        idpDisplayName.clear();
        idpDisplayName.sendKeys("Facebook");

        WebElement idPDescription = driver.findElement(By.id("idPDescription"));
        idPDescription.clear();
        idPDescription.sendKeys("User can use Facebook credentials to login to Travelocity using Facebook Idp");


        WebElement out_bound_auth_head = wait.until(ExpectedConditions.elementToBeClickable(By.id("out_bound_auth_head")));
        out_bound_auth_head.click();

        WebElement fb_auth_head = wait.until(ExpectedConditions.elementToBeClickable(By.id("fb_auth_head")));
        fb_auth_head.click();

        WebElement fbAuthEnabled = wait.until(ExpectedConditions.elementToBeClickable(By.id("fbAuthEnabled")));
        fbAuthEnabled.click();

        WebElement fbClientId = driver.findElement(By.id("fbClientId"));
        fbClientId.clear();
        fbClientId.sendKeys("126217798160084");

        WebElement fbClientSecret = driver.findElement(By.id("fbClientSecret"));
        fbClientSecret.clear();
        fbClientSecret.sendKeys("0910983a00c20499e396f71f7b45cf73");

        WebElement fbUserInfoFields = driver.findElement(By.id("fbUserInfoFields"));
        fbUserInfoFields.clear();
        fbUserInfoFields.sendKeys("id,name,gender,email,first_name,last_name,age_range,link");

        WebElement fbCallBackUrl = driver.findElement(By.id("fbCallBackUrl"));
        fbCallBackUrl.clear();
        fbCallBackUrl.sendKeys("https://localhost:9443/commonauth");

        WebElement registerIdpbtn = driver.findElement(By.xpath("//*[@id=\"middle\"]/div[2]/input[1]"));
        registerIdpbtn.click();

        String actualIdpname = driver.findElement(By.xpath("//*[@id=\"idPsListTable\"]/tbody/tr/td[1]")).getText();
        String expectedIdname = "FacebookIdp";
        Assert.assertEquals(actualIdpname,expectedIdname);


    }

    @Test(priority = 2)
    public void selectFBasFedaratedAuthenticator()
    {
        WebElement listSps = driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[3]/ul/li[8]/ul/li[2]/a"));
        listSps.click();

        WebElement editSp = driver.findElement(By.xpath("//*[@id=\"ServiceProviders\"]/tbody/tr/td[3]/a[1]"));
        editSp.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement outboundAuth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app_authentication_advance_head\"]/a")));
        outboundAuth.click();

        WebElement federatedRadiobtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("federated")));
        federatedRadiobtn.click();

        WebElement fed_idp = wait.until(ExpectedConditions.elementToBeClickable(By.id("fed_idp")));
        fed_idp.click();

        WebElement updateSpbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"configure-sp-form\"]/div[9]/div/input[1]")));
        updateSpbtn.click();

    }

    @Test(priority = 3)
    public void loginToTravelocity()
    {
        wait = new WebDriverWait(driver, 10);
        driver.get(travelocityUrl);
        WebElement clickHerelink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content-area\"]/div[2]/h2[1]/a")));
        clickHerelink.click();

        WebElement fbEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        fbEmail.clear();
        fbEmail.sendKeys("madurangi@wso2support.com");

        WebElement fbPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("pass")));
        fbPassword.clear();
        fbPassword.sendKeys("passwordFB");

        WebElement loginbutton = driver.findElement(By.id("loginbutton"));
        loginbutton.click();

        Assert.assertTrue(driver.getPageSource().contains("Travelocity.COM"));
        Assert.assertTrue(driver.getPageSource().contains("You are logged in as madurangi@wso2support.com"));

    }

    @AfterClass
    public void tearDown() {

        if(driver!=null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }





}
