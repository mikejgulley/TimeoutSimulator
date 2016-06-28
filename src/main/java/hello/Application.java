package hello;

import java.util.Arrays;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
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

        //wireMockServer.stop();
    }

}
