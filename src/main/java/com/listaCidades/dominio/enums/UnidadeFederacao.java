package com.listaCidades.dominio.enums;

public enum UnidadeFederacao {

	  AM("Amazonas", "AM", "Manaus"), 
	  AL("Alagoas", "AL", "Maceió"), 
	  AC("Acre", "AC", "Rio Branco"),
	  AP("Amapá", "AP", "Macapá"), 
	  BA("Bahia", "BA", "Salvador"), 
	  PA("Pará", "PA", "Belém"),
	  MT("Mato Grosso", "MT", "Cuiabá"), 
	  MG("Minas Gerais", "MG", "Belo Horizonte"),
	  MS("Mato Grosso do Sul", "MS", "Campo Grande"), 
	  GO("Goiás", "GO", "Goiânia"),
	  MA("Maranhão", "MA", "São Luís"), 
	  RS("Rio Grande do Sul", "RS", "Porto Alegre"),
	  TO("Tocantins", "TO", "Palmas"), 
	  PI("Piauí", "PI", "Teresina"), 
	  SP("São Paulo", "SP", "São Paulo"),
	  RO("Rondônia", "RO", "Porto Velho"), 
	  RR("Roraima", "RR", "Boa Vista"),
	  PR("Paraná", "PR", "Curitiba"), 
	  CE("Ceará", "CE", "Fortaleza"), 
	  PE("Pernambuco", "PE", "Recife"),
	  SC("Santa Catarina", "SC", "Florianópolis"), 
	  PB("Paraíba", "PB", "João Pessoa"),
	  RN("Rio Grande do Norte", "RN", "Natal"), 
	  ES("Espírito Santo", "ES", "Vitória"),
	  RJ("Rio de Janeiro", "RJ", "Rio de Janeiro"),
	  SE("Sergipe", "SE", "Aracaju"),
	  DF("Distrito Federal", "DF", "Brasília");
	  
	  private String nome;
	  private String sigla;
	  private String capital;

	  /**
	   * Construtor do enum
	   *
	   * @param nome nome da unidade da federação completo
	   * @param sigla sigla da unidade da federação
	   * @param capital nome da capital da unidade da federação
	   */
	  UnidadeFederacao(final String nome, final String sigla, final String capital) {
	    this.nome = nome;
	    this.sigla = sigla;
	    this.capital = capital;
	  }

	  /**
	   * Converter a partir do nome da Unidade da Federacao, para o respectivo enum.
	   * 
	   * @param nomeUf
	   * @return a Unidade da Federação
	   */
	  public static UnidadeFederacao fromUF(final String nomeUf) {
	    for (final UnidadeFederacao uf : UnidadeFederacao.values()) {
	      if (uf.nome.equalsIgnoreCase(nomeUf)) {
	        return uf;
	      }
	    }
	    throw new IllegalArgumentException(nomeUf);
	  }

	  /**
	   * Converte a partir da Sigla do parâmetro, para o enum da Unidade da Federação.
	   * 
	   * @param sigla
	   * @return a Unidade da Federação
	   */
	  public static UnidadeFederacao fromSigla(final String sigla) {
	    for (final UnidadeFederacao uf : UnidadeFederacao.values()) {
	      if (uf.sigla.equalsIgnoreCase(sigla)) {
	        return uf;
	      }
	    }
	    throw new IllegalArgumentException(sigla);
	  }

	  /**
	   * Converte, a partir do nome da capital da UF, para o Enum.
	   * 
	   * @param capital
	   * @return a Unidade da Federacao com a capital passada no parâmetro
	   */
	  public static UnidadeFederacao fromCapital(final String capital) {
	    for (final UnidadeFederacao uf : UnidadeFederacao.values()) {
	      if (uf.capital.equalsIgnoreCase(capital)) {
	        return uf;
	      }
	    }
	    throw new IllegalArgumentException(capital);
	  }

	  /**
	   * Obtém a sigla da UF
	   *
	   * @return a sigla da UF
	   */
	  public String sigla() {
	    return this.sigla;
	  }

	  /**
	   * Nome da UF
	   *
	   * @return nome completo da UF
	   */
	  public String nome() {
	    return this.nome;
	  }

	  /**
	   * Nome da capital da UF
	   *
	   * @return nome da capital da UF
	   */
	  public String capital() {
	    return this.capital;
	  }

	  @Override
	  public String toString() {
	    final StringBuilder sb = new StringBuilder("UnidadeFederacao{");
	    sb.append("nome='").append(nome).append('\'');
	    sb.append(", sigla='").append(sigla).append('\'');
	    sb.append(", capital='").append(capital).append('\'');
	    sb.append('}');
	    return sb.toString();
	  }
}