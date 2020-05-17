package com.listaCidades.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaCidades.dominio.Cidades;
import com.listaCidades.dominio.enums.UnidadeFederacao;
import com.listaCidades.repositorio.CidadesRepositorio;

@Service
public class DBService {

	
	@Autowired
	private CidadesRepositorio repositorio;
	
	
	public void iniciaDataBase() {
		
		Cidades c1 = new Cidades(null, "1100015", UnidadeFederacao.fromSigla("RO"), "Alta Floresta D'Oeste", "", "-61.9998238963", "-11.9355403048", "Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense");
		Cidades c2 = new Cidades(null, "1100023", UnidadeFederacao.fromSigla("RO"), "Ariquemes", "", "-63.033269278", "-9.9084628666", "Ariquemes", "", "Ariquemes", "Leste Rondoniense");
		Cidades c3 = new Cidades(null, "1100031", UnidadeFederacao.fromSigla("RO"), "Cabixi", "", "-60.5443135812", "-13.4997634597", "Cabixi", "", "Colorado do Oeste", "Leste Rondoniense");
		Cidades c4 = new Cidades(null, "1100049", UnidadeFederacao.fromSigla("RO"), "Cacoal", "", "-61.4429442118", "-11.4338650287", "Cacoal", "", "Cacoal", "Leste Rondoniense");
		
		repositorio.saveAll(Arrays.asList(c1,c2,c3,c4));
	}
}
