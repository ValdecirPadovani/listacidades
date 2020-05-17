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
	public String upload(MultipartFile file) {
	    cidadeService.atualizaDadosBd(file);
	    return "Chupa";
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarCidade(@RequestBody Cidades cidade){
		Cidades obj = cidadeService.setCidades(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cIbge}").buildAndExpand(obj.getCodigoIbge()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{cIbge}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCidade(@PathVariable String cIbge){
		cidadeService.delete(cIbge);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 *  1. Ler o arquivo CSV das cidades para a base de dados;
	 *  OK
		
		2. Retornar somente as cidades que são capitais ordenadas por nome;
		OK
		
		3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
		quantidade de cidades;
		OK
		
		4. Retornar a quantidade de cidades por estado;
		OK
		
		5. Obter os dados da cidade informando o id do IBGE;
		OK
				
		6. Retornar o nome das cidades baseado em um estado selecionado;
		OK
		
		7. Permitir adicionar uma nova Cidade;
		OK
		
		8. Permitir deletar uma cidade;
		
		9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma
		string para
		filtrar. retornar assim todos os objetos que contenham tal string;
		
		10. Retornar a quantidade de registro baseado em uma coluna. Não deve
		contar itens iguais;
		
		11. Retornar a quantidade de registros total;
		
		12. Dentre todas as cidades, obter as duas cidades mais distantes uma da
		outra com base na localização (distância em KM em linha reta); 

	 */
}