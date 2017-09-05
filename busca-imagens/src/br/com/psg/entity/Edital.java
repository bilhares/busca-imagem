package br.com.psg.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "edital_do")
public class Edital {
	@Id
	@Column(name = "num_edital")
	private Long nEdital;
	@Column(name = "data_edital")
	@Temporal(TemporalType.DATE)
	private Date dataEdital;
	@Column(name = "aprovado")
	private boolean aprovado;
	@Column(name = "data_publicacao")
	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;
	@Column(name = "id_arquivo")
	private int idEdital;
	@Column(name = "num_diario")
	private Long nDiario;
	@Column(name = "assinado")
	private boolean assinado;
	@Column(name = "id_arquivo_pdf")
	private int idEditalPdf;
	@Column(name = "ativo")
	private boolean ativo;
	@Column(name = "tipo_edital")
	private int tipoEdital;

	public int getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(int tipoEdital) {
		this.tipoEdital = tipoEdital;
	}

	public boolean isAssinado() {
		return assinado;
	}

	public void setAssinado(boolean assinado) {
		this.assinado = assinado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Long getnDiario() {
		return nDiario;
	}

	public void setnDiario(Long nDiario) {
		this.nDiario = nDiario;
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	public int getIdEditalPdf() {
		return idEditalPdf;
	}

	public void setIdEditalPdf(int idEditalPdf) {
		this.idEditalPdf = idEditalPdf;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Long getnEdital() {
		return nEdital;
	}

	public void setnEdital(Long nEdital) {
		this.nEdital = nEdital;
	}

	public Date getDataEdital() {
		return dataEdital;
	}

	public void setDataEdital(Date date) {
		this.dataEdital = date;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

}
