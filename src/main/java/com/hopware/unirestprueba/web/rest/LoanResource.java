package com.hopware.unirestprueba.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hopware.unirestprueba.service.LoanService;
import com.hopware.unirestprueba.web.rest.util.HeaderUtil;
import com.hopware.unirestprueba.web.rest.util.PaginationUtil;
import com.hopware.unirestprueba.service.dto.LoanDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Loan.
 */
@RestController
@RequestMapping("/api")
public class LoanResource {

    private final Logger log = LoggerFactory.getLogger(LoanResource.class);

    private static final String ENTITY_NAME = "loan";

    private final LoanService loanService;

    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * POST  /loans : Create a new loan.
     *
     * @param loanDTO the loanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loanDTO, or with status 400 (Bad Request) if the loan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loans")
    @Timed
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) throws URISyntaxException {
        log.debug("REST request to save Loan : {}", loanDTO);
        if (loanDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new loan cannot already have an ID")).body(null);
        }
        LoanDTO result = loanService.save(loanDTO);
        System.out.println(result);
        return ResponseEntity.created(new URI("/api/loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/procesar")



    /**
     * PUT  /loans : Updates an existing loan.
     *
     * @param loanDTO the loanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loanDTO,
     * or with status 400 (Bad Request) if the loanDTO is not valid,
     * or with status 500 (Internal Server Error) if the loanDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loans")
    @Timed
    public ResponseEntity<LoanDTO> updateLoan(@RequestBody LoanDTO loanDTO) throws URISyntaxException {
        log.debug("REST request to update Loan : {}", loanDTO);
        if (loanDTO.getId() == null) {
            return createLoan(loanDTO);
        }
        LoanDTO result = loanService.save(loanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loans : get all the loans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loans in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/loans")
    @Timed
    public ResponseEntity<List<LoanDTO>> getAllLoans(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Loans");
        Page<LoanDTO> page = loanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loans/:id : get the "id" loan.
     *
     * @param id the id of the loanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/loans/{id}")
    @Timed
    public ResponseEntity<LoanDTO> getLoan(@PathVariable Long id) {
        log.debug("REST request to get Loan : {}", id);
        LoanDTO loanDTO = loanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(loanDTO));
    }

    /**
     * DELETE  /loans/:id : delete the "id" loan.
     *
     * @param id the id of the loanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loans/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        log.debug("REST request to delete Loan : {}", id);
        loanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
