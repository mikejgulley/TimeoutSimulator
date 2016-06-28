package hello;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

/*    @RequestMapping("/timeout-tester")
    public String timeoutTester() {

*//*        stubFor(get(urlEqualTo("/timeout-tester"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("<response>Some content</response>")));
                        //.withFixedDelay(2000)));*//*

        return "Testing timeout...";
    }*/
}
