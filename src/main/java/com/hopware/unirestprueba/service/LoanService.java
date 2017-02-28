package com.hopware.unirestprueba.service;

import com.hopware.unirestprueba.domain.Loan;
import com.hopware.unirestprueba.repository.LoanRepository;
import com.hopware.unirestprueba.service.dto.LoanDTO;
import com.hopware.unirestprueba.service.mapper.LoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Loan.
 */
@Service
@Transactional
public class LoanService {

    private final Logger log = LoggerFactory.getLogger(LoanService.class);
    
    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save
     * @return the persisted entity
     */
    public LoanDTO save(LoanDTO loanDTO) {
        log.debug("Request to save Loan : {}", loanDTO);
        Loan loan = loanMapper.loanDTOToLoan(loanDTO);
        loan = loanRepository.save(loan);
        LoanDTO result = loanMapper.loanToLoanDTO(loan);
        return result;
    }

    /**
     *  Get all the loans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Loans");
        Page<Loan> result = loanRepository.findAll(pageable);
        return result.map(loan -> loanMapper.loanToLoanDTO(loan));
    }

    /**
     *  Get one loan by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public LoanDTO findOne(Long id) {
        log.debug("Request to get Loan : {}", id);
        Loan loan = loanRepository.findOne(id);
        LoanDTO loanDTO = loanMapper.loanToLoanDTO(loan);
        return loanDTO;
    }

    /**
     *  Delete the  loan by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Loan : {}", id);
        loanRepository.delete(id);
    }
}
