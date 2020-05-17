package com.listaCidades.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.listaCidades.dominio.Cidades;
import com.listaCidades.dominio.enums.UnidadeFederacao;
import com.listaCidades.repositorio.CidadesRepositorio;

@Service
public class CidadesService {

	@Autowired
	private CidadesRepositorio repositorio;
	
	/**
	 * 
	 * @return Todas as cidades ja migradas para a base de dados
	 */
	public List<Cidades> findAll(){
		return repositorio.findAll();
	}
	/**
	 * 
	 * @param MultipartFile file
	 * @return Faz o processamento das informações e retorna o status
	 */
	public boolean atualizaDadosBd(MultipartFile file) {
		
		InputStream inputStream;
	    Scanner scanner;
	    List<String[]> linhas = new ArrayList<>();
	    boolean cabecalho = true;

	    try {
			inputStream = file.getInputStream();
			
            scanner = new Scanner(inputStream);
            
			while(scanner.hasNext()) {
				if(!cabecalho) {
					linhas.add(scanner.nextLine().split(","));
				}else {
					cabecalho = false;
					scanner.nextLine();
				}
			}
		} catch (IOException e) {
			//ToDO: Implementar validação das informações 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Cidades> cidades = new ArrayList<>();
		
		for(String[] cit : linhas) {
			
			Cidades cidade = new Cidades();
			cidade.setCodigoIbge(cit[0]);
			cidade.setUf(UnidadeFederacao.fromSigla(cit[1]));
			cidade.setNome(cit[2]);
			cidade.setCapital(cit[3]);
			cidade.setLon(cit[4]);
			cidade.setLat(cit[5]);
			cidade.setNo_accents(cit[6]);
			cidade.setAlternativeNames(cit[7]);
			cidade.setMicroregion(cit[8]);
			cidade.setMesoregion(cit[9]);
			
			cidades.add(cidade);
		}
		
		repositorio.saveAll(cidades);
		return true;
	}

	public List<Cidades> findCapitais() {
		// TODO Auto-generated method stub
		
		return repositorio.findByCapitalMeu(Sort.by(Sort.Direction.DESC, "nome"));
	}
	
	/**
	 * 
	 * @return Estado com maior numero de cidades
	 */
	public List<String> maiorMenorUF(){
		List<String> ufs = repositorio.findQtyUF();
		int maior = 0, menor = 0;
		String ufm ="", ufmn="";
		for (String strings : ufs) {
			
			String[] dados = strings.split(",");
			
			//maior valor
			if(Integer.valueOf(dados[1]) > maior) {
				maior = Integer.valueOf(dados[1]);
				ufm = strings;
			}
			
			//menor valor
			if(Integer.valueOf(dados[1]) < menor || menor == 0) {
				menor = Integer.valueOf(dados[1]);
				ufmn = strings;
			}
		}
		
		return Arrays.asList(ufm, ufmn);
	}
	
	/**
	 * 
	 * @return retorna todos os estados e a quantidade de cidades de cada um
	 */
	public List<String> getQtyCidade() {
		return repositorio.findQtyUF();
	}
	
	/**
	 * 
	 * @param codIbge 
	 * @return Cidade correspondente ao código informado
	 */
	public Cidades getCidadesCodIbge(String codIbge) {
		return repositorio.findByCodigoIbge(codIbge);
	}
	
	/**
	 * 
	 * @param uf UnidadeFederacao
	 * @return Todas as cidades que corresponde ao UF informado
	 */
	public List<Cidades> getCidadesUf(String uf) {
		UnidadeFederacao unf = UnidadeFederacao.fromSigla(uf);
		return repositorio.findByUf(unf);
	}
	
	/**
	 * 
	 * @param cidade
	 * @return Cria um novo registro de cidade
	 */
	public Cidades setCidades(Cidades cidade) {
		cidade.setId(null);
		return repositorio.save(cidade);
	}
	
	/**
	 * 
	 * @param codIbge
	 */
	public void delete(String codIbge) {
		Cidades cidade = getCidadesCodIbge(codIbge);
		repositorio.delete(cidade);
	}
}
