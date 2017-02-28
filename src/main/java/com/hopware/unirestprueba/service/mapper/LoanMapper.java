package com.hopware.unirestprueba.service.mapper;

import com.hopware.unirestprueba.domain.*;
import com.hopware.unirestprueba.service.dto.LoanDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Loan and its DTO LoanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoanMapper {

    LoanDTO loanToLoanDTO(Loan loan);

    List<LoanDTO> loansToLoanDTOs(List<Loan> loans);

    Loan loanDTOToLoan(LoanDTO loanDTO);

    List<Loan> loanDTOsToLoans(List<LoanDTO> loanDTOs);
}
