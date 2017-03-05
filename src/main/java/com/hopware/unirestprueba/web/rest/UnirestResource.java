package com.hopware.unirestprueba.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hopware.unirestprueba.components.RequestComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
// todo: REMAKE THIS CONTROLLER
public class UnirestResource {

    private final Logger log = LoggerFactory.getLogger(UnirestResource.class);

    private final RequestComponent requestComponent;

    private final LoanResource loan;

    public UnirestResource(RequestComponent requestComponent, LoanResource loan) {
        this.requestComponent = requestComponent;
        this.loan = loan;
    }

    @PostMapping("/start")
    @Timed
    public String testRequestComponentCall(HttpServletRequest request) {
        log.debug("Starting test request call");
        //DTO SHOULD BE HERE AS A PARAMETER
        return requestComponent.init(request.getMethod());
    }

    @PutMapping("/start")
    @Timed
    public String testRequestComponentCallPut(HttpServletRequest request) {
        log.debug("Starting test request call");
        //DTO SHOULD BE HERE AS A PARAMETER
    return requestComponent.init(request.getMethod());
    }
}
