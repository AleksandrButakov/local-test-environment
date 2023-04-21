package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class TestBase {

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "112.0";
//        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "http://62.113.108.218:4444/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "http://62.113.108.218:4444/wd/hub";

        //ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "My tests...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC+5");
            }});

            /* How to enableVNC */
            put("enableVNC", true);
        }});
        Configuration.browserCapabilities = capabilities;





//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        //capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVideo", true
//        ));

        //Configuration.browserCapabilities = capabilities;



    }

    @BeforeEach
    void addListener() {

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
