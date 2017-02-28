package com.hopware.unirestprueba.repository;

import com.hopware.unirestprueba.domain.Loan;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Loan entity.
 */
@SuppressWarnings("unused")
public interface LoanRepository extends JpaRepository<Loan,Long> {

}
