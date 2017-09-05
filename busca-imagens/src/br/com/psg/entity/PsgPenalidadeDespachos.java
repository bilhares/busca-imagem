package br.com.psg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "psg_penalidades_despachos")
public class PsgPenalidadeDespachos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "COD_PROCESSO_LECOM")
	private String codProcesso;
	
	@Column(name = "COD_PROCESSO_DETRAN")
	private String codProcessoDetran;
	
	@Column(name = "COD_ETAPA")
	private int codEtapa;
	
	@Column(name = "COD_CICLO")
	private int codCiclo;
	
	@Column(name = "TIPO_REGISTRO")
	private String tipoRegistro;
	
	@OneToOne
	@JoinColumn(name = "ID_ARQUIVO")
	private ArquivoDespacho arquivo;
	
	public String getCodProcessoDetran() {
		return codProcessoDetran;
	}

	public void setCodProcessoDetran(String codProcessoDetran) {
		this.codProcessoDetran = codProcessoDetran;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodProcesso() {
		return codProcesso;
	}

	public void setCodProcesso(String codProcesso) {
		this.codProcesso = codProcesso;
	}

	public int getCodEtapa() {
		return codEtapa;
	}

	public void setCodEtapa(int codEtapa) {
		this.codEtapa = codEtapa;
	}

	public int getCodCiclo() {
		return codCiclo;
	}

	public void setCodCiclo(int codCiclo) {
		this.codCiclo = codCiclo;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public ArquivoDespacho getArquivo() {
		return arquivo;
	}

	public void setArquivo(ArquivoDespacho arquivo) {
		this.arquivo = arquivo;
	}

}
