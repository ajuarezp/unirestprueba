package com.hopware.unirestprueba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hopware.unirestprueba.config.ApplicationProperties;
import com.hopware.unirestprueba.config.DefaultProfileUtil;

import com.hopware.unirestprueba.domain.Loan;
import com.hopware.unirestprueba.service.dto.LoanDTO;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
//import com.mashape.unirest.GetRequest.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import io.github.jhipster.config.JHipsterConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

@ComponentScan
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class UnirestpruebaApp {

    private static final Logger log = LoggerFactory.getLogger(UnirestpruebaApp.class);

    private final Environment env;

    public UnirestpruebaApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes unirestprueba.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://jhipster.github.io/profiles/">http://jhipster.github.io/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not" +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException, Exception, UnirestException, IOException, JsonProcessingException {
        SpringApplication app = new SpringApplication(UnirestpruebaApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            env.getProperty("server.port"),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());



//        //Agregar throw IOException y JsonProcessingException
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }



            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        try{

//            HttpResponse<String> response = Unirest.post("http://localhost:8080/api/loans")
//                .header("content-type", "application/json")
//                .header("cache-control", "no-cache")
//                .header("postman-token", "0a068148-d303-f06c-0085-b7a223384f37")
//                .body("  {\n    \"nombre\": \"cuarenta\",\n    \"monto\": 12,\n    \"banco\": \"bcr\",\n    \"fecha\": \"2017-02-08\"\n  }")
//                .asString();

//
            HttpResponse<Object> response1 = Unirest.get("http://localhost:8080/api/loans/1")
                .asObject(Object.class);

            Object obj = response1.getBody();
            obj.

            System.out.println("-djasljdjsakdksajdkhsdhshdjsghdgsjdg;ojd;sajdjsa-");

            System.out.println(objL);

//                HttpResponse<LoanDTO[]> response = Unirest.get("http://localhost:8080/api/loans").asObject(LoanDTO[].class);
//                LoanDTO[] books = response.getBody();
//                System.out.println(books);


        }catch (UnirestException o){
            throw o;
        }
//
//
//        Unirest.setObjectMapper(response1).writeValue(Loan.class);
//
//        // Response to Object
//    HttpResponse<Loan> bookResponse = Unirest.get("http://http://localhost:8080/#/loan/loan/2").asObject(Loan.class);
//        Loan loanObject = bookResponse.getBody();
//
//        HttpResponse<Loan> authorResponse = Unirest.get("http://httpbin.org/books/{id}/author")
//            .routeParam("id", loanObject.getId().toString())
//            .asObject(Loan.class);
//
//        Author authorObject = authorResponse.getBody();
////
////        HttpResponse<JsonNode> postResponse = Unirest.post("http://httpbin.org/authors/post")
////            .header("accept", "application/json")
////            .header("Content-Type", "application/json")
////            .body(loanObject)
////            .asJson();
//
//
//
//



    }
}
