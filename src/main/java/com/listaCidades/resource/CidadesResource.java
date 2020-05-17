package com.listaCidades.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.listaCidades.dominio.Cidades;
import com.listaCidades.service.CidadesService;

@RestController
@RequestMapping(value="/cidades")
public class CidadesResource {

	@Autowired
	private CidadesService cidadeService;

	
	/**
	 * 
	 * @return todas as cidades cadastradas\importadas no sistema
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cidades>> getAllCidades() {
		return ResponseEntity.ok().body(cidadeService.findAll());
	}
	
	
	/**
	 * 
	 * @param file arquivo a ser importado
	 * @return dados importados no sistema
	 */
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public void upload(MultipartFile file) {
	    cidadeService.atualizaDadosBd(file);
	}
	
	/**
	 * 
	 * @return todas as capitais cadastradas
	 */
	@RequestMapping(value = "/capitais", method = RequestMethod.GET)
	public ResponseEntity<List<Cidades>> getCapitais(){
		return ResponseEntity.ok().body(cidadeService.findCapitais());
	}
	
	/**
	 * 
	 * @return Retorna a uf com maior e menor numero de cidades
	 */
	@RequestMapping(value = "/qty", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getMaiorMenorUf(){
		return ResponseEntity.ok().body(cidadeService.maiorMenorUF());
	}
	
	/**
	 * 
	 * @return Capitais com maior e menor numero de cidades
	 */
	@RequestMapping(value = "/tamanho", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getMaiorMenorCidade(){
		return ResponseEntity.ok().body(cidadeService.getQtyCidade());
	}
	
	
	/**
	 * 
	 * @param cIbge códig de IBGE
	 * @return Cidade a partir do código do IBGE
	 */
	@RequestMapping(value="/{cIbge}", method = RequestMethod.GET)
	public ResponseEntity<Cidades> getCidadeIbge(@PathVariable String cIbge){
		
		return ResponseEntity.ok().body(cidadeService.getCidadesCodIbge(cIbge));
	}
	
	/**
	 * 
	 * @param uf Unidade Federacao
	 * @return Todas as cidades a partir do seu estado
	 */
	@RequestMapping(value="/estado/{uf}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidades>> getCidadeUf(@PathVariable String uf){
		return ResponseEntity.ok().body(cidadeService.getCidadesUf(uf));
	}
	
	
	/**
	 * 
	 * @param cidade
	 * @return Salva no banco um novo registro de cidade
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarCidade(@RequestBody Cidades cidade){
		Cidades obj = cidadeService.setCidades(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cIbge}").buildAndExpand(obj.getCodigoIbge()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * 
	 * @param cIbge
	 * @return remove uma cidade do banco
	 */
	@RequestMapping(value="/{cIbge}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCidade(@PathVariable String cIbge){
		cidadeService.delete(cIbge);
		return ResponseEntity.noContent().build();
	}
	
}