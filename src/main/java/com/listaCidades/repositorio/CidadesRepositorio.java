package com.listaCidades.repositorio;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.listaCidades.dominio.Cidades;
import com.listaCidades.dominio.enums.UnidadeFederacao;

@Repository
public interface CidadesRepositorio extends JpaRepository<Cidades, Integer>{

	@Transactional
	@Query("SELECT c FROM Cidades c WHERE c.capital = 'true' ")
	List<Cidades> findByCapitais(Sort sort);
	
	@Transactional
	@Query(value = "SELECT uf, count(*) from cidades group by uf having Count(*)>1 ",nativeQuery = true )
	List<String> findQtyUF();
	
	Cidades findByCodigoIbge(String codigoIbge);
	
	List<Cidades> findByUf(UnidadeFederacao uf);
	
}
