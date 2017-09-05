package br.com.psg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "psg_penalidades_pdf_completo")
public class PsgPenalidadesPdfCompleto {
	@Id
	@Column(name = "COD_PROCESSO_LECOM")
	private int codProcessoLecom;

	@Column(name = "COD_PROCESSO_DETRAN")
	private String codProcessoDetran;

	@Column(name = "ARQUIVO")
	private byte[] arquivo;

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

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

}