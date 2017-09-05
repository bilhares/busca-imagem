package br.com.psg.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "psg_penalidades_controle_pdf")
public class PsgPenalidadesControlePdf {

	@Id
	@Column(name = "COD_PROCESSO_LECOM")
	private int codProcessoLecom;

	@Column(name = "COD_PROCESSO_DETRAN")
	private String codProcessoDetran;

	@Column(name = "DIRETORIO")
	private String diretorio;

	@Column(name = "COD_ETAPA")
	private int codEtapa;

	@Column(name = "COD_CICLO")
	private int codCiclo;

	@Column(name = "DATA_ALTERACAO")
	private Date dataAlteracao;

	public int getCodProcessoLecom() {
		return codProcessoLecom;
	}

	public void setCodProcessoLecom(int codProcessoLecom) {
		this.codProcessoLecom = codProcessoLecom;
	}

	public String getCodProcessoDetran() {
		return codProcessoDetran;
	}

	public void setCodProcessoDetran(String codProcessoDetran) {
		this.codProcessoDetran = codProcessoDetran;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

}
