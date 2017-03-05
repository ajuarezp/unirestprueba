package com.hopware.unirestprueba.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hopware.unirestprueba.components.RequestComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public UnirestResource(RequestComponent requestComponent) {
        this.requestComponent = requestComponent;
    }

    @GetMapping("/start")
    @Timed
    public String testRequestComponentCall(HttpServletRequest request) {
        log.debug("Starting test request call");
        //DTO SHOULD BE HERE AS A PARAMETER
        return requestComponent.init();
    }
}
