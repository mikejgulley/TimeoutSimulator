package hello;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //ApplicationContext ctx = SpringApplication.run(Application.class, args);

/*        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/

        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        System.out.println("WireMock server started...");

        wireMockServer.stubFor(get(urlEqualTo("/timeout-tester")).willReturn(
                aResponse()
                        .withStatus(200)
                        .withFixedDelay(40000))); // take longer than our expected timeout for Manual's server
                        // http://esbtest.mtdproducts.com:5656/ws/MTDManualsWS.ProvWSD:getManualsWSD


        //wireMockServer.stubFor(get(urlEqualTo("/timeout-tester")));

        //wireMockServer.stop();

       /* ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.*/

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MILLISECONDS);

        //driver.get("http://esbtest.mtdproducts.com:5656/ws/MTDManualsWS.ProvWSD:getManualsWSD?storeId=10051&catalogId=14101&langId=-1&logoCode=01&modelNumber=13WX90AS010&serialNumber=");
        try {
            driver.get("http://local.cubcadet.com/equipment/cubcadet/DisplayOwnersManualList?storeId=10051&catalogId=14101&langId=-1&logoCode=01&modelNumber=13WX90AS010&serialNumber=");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
