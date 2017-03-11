package com.hopware.unirestprueba.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopware.unirestprueba.domain.RequestDTO;
import com.hopware.unirestprueba.service.dto.LoanDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by gerardoparajeles on 3/5/17.
 *
 * --Update by DouglasBrenes on 3/5/17.--
 */

@Component
public class RequestComponent{
    // todo: IF LOCALHOST GET THE URL AND PORT FROM THE SYSTEM ENV.
    // todo: USE TYPE AS ENUM OR CONSTANT
    // todo: MAKE THIS CLASS TRULY A COMPONENT, THIS METHODS ARE JUST A POC
    // todo: DONT' TEST THIS USING POST CONSTRUCT, THE SERVER IS NOT AVAILABLE WHEN SPRING IoC RUNS THIS BLOCK OF CODE.
    // todo: LOCAL DATE.NOW IS NOT BEING PARSED SUCCESSFULLY
    // todo: LOCALDATE JACKSON THRU OBJECT MAPPER
    private final Logger log = LoggerFactory.getLogger(RequestComponent.class);

    ObjectMapper mapper = new ObjectMapper();
    LoanDTO testLoan = new LoanDTO();
    RequestDTO requestDTO = new RequestDTO();
    ObjectMapperGson objectgson = new ObjectMapperGson();


    public String init(String type){
        String result = "";

        switch (type){

            case "POST":
                testLoan.setBanco("banco");
                testLoan.setFecha(LocalDate.now());
                testLoan.setMonto(2500D);
                testLoan.setNombre("Douglas Test Loan");

                try {

                    requestDTO.setUrl("http://localhost:8080/api/loans");
                    requestDTO.setHeaders( new HashMap<String, String>() {{ put("content-type","application/json"); put("accept","application/json"); }} );
                    requestDTO.setBody(mapper.writeValueAsString(testLoan));

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                log.debug("Making post call");
                result = makePostCall(requestDTO);
                break;

            case "PUT":
                long id = 2;
                testLoan.setBanco("BAC");
                testLoan.setFecha(LocalDate.now());
                testLoan.setMonto(200D);
                testLoan.setNombre("Hola objraro");
                testLoan.setId(id);

                System.out.println("LocalDate: " + LocalDate.now());

                try {
                    requestDTO.setUrl("http://localhost:8080/api/loans");
                    requestDTO.setHeaders( new HashMap<String, String>() {{ put("content-type","application/json"); put("accept","application/json"); }} );
                    requestDTO.setBody(objectgson.writeValueAsString(testLoan));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.debug("Making put call");
                 result = makePutCall(requestDTO);
                break;

            case "GET":
                long loanId = 1;
                testLoan.setId(loanId);
                try {
                    requestDTO.setUrl("http://localhost:8080/api/loans/" + loanId);
                    requestDTO.setHeaders( new HashMap<String, String>() {{ put("content-type","application/json"); put("accept","application/json"); }} );

                } catch (Exception e) {
                    e.printStackTrace();
                }
                result = makeGetCall(requestDTO);
                break;
        }

        log.debug(result);
        return result;
    }

    private String makePostCall(RequestDTO testDTO) {

        String result = "";
        try {
            log.debug("Doing unirest call");
            HttpResponse<String> mainResponse = Unirest.post(testDTO.getUrl()).headers(testDTO.getHeaders()).body(testDTO.getBody()).asString();
            result = mainResponse.getBody();
            log.debug("unirest call done");
        } catch (UnirestException e) {
            e.printStackTrace();
            log.debug("unable to make unirest call");
        }

        return result;
    }

    private String makePutCall(RequestDTO requestDTO) {

        String result = "";
        try {
            log.debug("Doing unirest call");
            HttpResponse<String> mainResponse = Unirest.put(requestDTO.getUrl()).headers(requestDTO.getHeaders()).body(requestDTO.getBody()).asString();
            result = mainResponse.getBody();
            log.debug("unirest call done");
        } catch (UnirestException e) {
            e.printStackTrace();
            log.debug("unable to make unirest call");
        }

        return result;
    }

    private String makeGetCall(RequestDTO requestDTO) {
        String result = "";
        try {
            HttpResponse<String> mainResponse = Unirest.get(requestDTO.getUrl()).headers(requestDTO.getHeaders()).asString();
            result = mainResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return result;
    }
}
