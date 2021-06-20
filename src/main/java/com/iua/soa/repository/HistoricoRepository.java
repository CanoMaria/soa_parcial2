package com.iua.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iua.soa.model.Historico;


@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long>{

}
