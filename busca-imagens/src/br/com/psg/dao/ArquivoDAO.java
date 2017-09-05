package br.com.psg.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.psg.config.JpaUtil;
import br.com.psg.entity.ArquivoDespacho;
import br.com.psg.entity.Arquivos;

public class ArquivoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	public ArquivoDAO() {
		// manager = JpaUtil.getEntityManager();
	}

	public Arquivos getArquivo(int id) {
		manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Arquivos> q = manager.createQuery("from Arquivos a where a.id = :id", Arquivos.class);
			q.setParameter("id", id);
			List<Arquivos> arq = q.getResultList();
			if (arq.size() > 0) {
				return arq.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	public ArquivoDespacho getArquivoDespacho(Long id) {
		manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<ArquivoDespacho> q = manager.createQuery("from ArquivoDespacho a where a.id = :id",
					ArquivoDespacho.class);
			q.setParameter("id", id);
			List<ArquivoDespacho> arq = q.getResultList();
			if (arq.size() > 0) {
				return arq.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	public ArquivoDespacho getArquivoDespachoByProcesso(String processo) {
		manager = JpaUtil.getEntityManager();
		try {

			ArquivoDespacho arquivo;
			TypedQuery<ArquivoDespacho> q = manager.createQuery(
					"select a.arquivo from PsgPenalidadeDespachos a where a.codProcesso like :id",
					ArquivoDespacho.class);
			q.setMaxResults(1);
			q.setParameter("id", "%" + processo + "%");

			ArquivoDespacho arqId = null;
			try {
				arqId = q.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				manager.close();
			}

			arquivo = getArquivoDespacho(arqId.getId());
			return arquivo;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	public Arquivos getArquivoByEdital(Long id) {
		manager = JpaUtil.getEntityManager();
		Arquivos arquivo;
		try {
			TypedQuery<Integer> q = manager.createQuery("select a.idEditalPdf from Edital a where a.nEdital = :id",
					Integer.class);
			q.setParameter("id", id);
			Integer arqId = null;
			try {
				arqId = q.getSingleResult();
			} catch (Exception e) {
				return null;
			} finally {
				manager.close();
			}
			arquivo = getArquivo(arqId);

			return arquivo;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ArquivoDespacho> getByteDespachoByProcesso(String processo) {

		List<BigInteger> idArquivo = getProcessosValidos(processo);
		List<ArquivoDespacho> listaDeArquivos = new ArrayList<>();

		try {

			for (BigInteger id : idArquivo) {
				listaDeArquivos.add(getArquivoDespacho(Long.parseLong(id.toString())));
			}

			return listaDeArquivos;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	private List<BigInteger> getProcessosValidos(String processo) {
		manager = JpaUtil.getEntityManager();
		try {

			ArquivoDespacho arquivo;

			Query q = manager.createNativeQuery("select distinct d.ID_ARQUIVO from psg_penalidades_despachos d "
					+ "join PROCESSO p on p.COD_PROCESSO = d.COD_PROCESSO_LECOM where "
					+ "COD_PROCESSO_LECOM =:codProc and p.COD_CICLO_ATUAL = d.COD_CICLO order by d.ID_ARQUIVO asc");

			q.setParameter("codProc", processo);
			List<BigInteger> listaDeIds = q.getResultList();

			return listaDeIds;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;

	}

	public static void main(String[] args) {
		ArquivoDAO dao = new ArquivoDAO();
		dao.getByteDespachoByProcesso("49093");
	}

	public List<Arquivos> getByteEditalByProcesso(String processo) {
		List<BigDecimal> idEdital = getIdEdital(processo);
		List<Arquivos> listaDeArquivos = new ArrayList<>();

		for (BigDecimal id : idEdital) {
			listaDeArquivos.add(getArquivoByEdital(Long.parseLong(id.toString())));
		}

		return listaDeArquivos;
	}

	private List<BigDecimal> getIdEdital(String processo) {
		manager = JpaUtil.getEntityManager();
		try {

			ArquivoDespacho arquivo;

			Query q = this.manager.createNativeQuery(
					"select distinct id_edital from proc_diario_oficial d join PROCESSO p on p.COD_PROCESSO = d.codigo_processo  "
							+ "where codigo_processo =:codProc and p.COD_CICLO_ATUAL = d.cod_ciclo  order by d.id_edital asc");

			q.setParameter("codProc", processo);
			List<BigDecimal> listaDeIds = q.getResultList();

			return listaDeIds;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	// public PsgPenalidadesPdfCompleto getPdfCompleto(String processo) {
	// manager = JpaUtil.getEntityManager();
	// try {
	// TypedQuery<PsgPenalidadesPdfCompleto> q = manager.createQuery(
	// "from PsgPenalidadesPdfCompleto where codProcessoDetran
	// =:processoDetran",
	// PsgPenalidadesPdfCompleto.class);
	// q.setParameter("processoDetran", processo);
	//
	// PsgPenalidadesPdfCompleto result = q.getSingleResult();
	//
	// return result;
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// manager.close();
	// }
	//
	// throw new RuntimeException();
	// }

	public String getPdfCompleto(String processo, String cpf) {
		manager = JpaUtil.getEntityManager();
		try {
			Query q = manager.createNativeQuery("select pdf.DIRETORIO FROM psg_penalidades_controle_pdf pdf "
					+ "join f_proc_penal f on f.COD_PROCESSO_F = pdf.COD_PROCESSO_LECOM"
					+ " join processo p on ( f.COD_PROCESSO_F = p.COD_PROCESSO and f.COD_ETAPA_F = p.COD_ETAPA_ATUAL "
					+ "and f.COD_CICLO_F = p.COD_CICLO_ATUAL) where pdf.COD_PROCESSO_DETRAN like :processoDetran and f.CPF like :cpf");
			q.setParameter("processoDetran", "%"+processo+"%");
			q.setParameter("cpf", "%"+cpf+"%");

			String result = (String) q.getSingleResult();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		throw new RuntimeException();
	}

}
