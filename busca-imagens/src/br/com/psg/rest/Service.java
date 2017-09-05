package br.com.psg.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.psg.dao.ArquivoDAO;
import br.com.psg.entity.ArquivoDespacho;
import br.com.psg.entity.Arquivos;
import br.com.psg.ssh.SSHDownload;

@Path("/penalidades")
public class Service {

	// private EntityManager manager;
	// private EntityTransaction tx;

	ArquivoDAO arqDao;
	
	SSHDownload ssh = new SSHDownload();

	@PostConstruct
	public void init() {
		// manager = JpaUtil.getEntityManager();
		// tx = manager.getTransaction();
		arqDao = new ArquivoDAO();
	}

	@GET
	@Path("/get-arquivo/{id}")
	@Produces("application/pdf")
	public Response getArquivo(@PathParam("id") int id) {
		Arquivos arquivo = arqDao.getArquivo(id);
		InputStream inStream = new ByteArrayInputStream(arquivo.getArquivo());
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(inStream).build();
	}

	@GET
	@Path("/get-arquivo-edital/{numEdital}")
	@Produces("application/pdf")
	public Response getArquivoByEdital(@PathParam("numEdital") Long id) {
		Arquivos arquivo = arqDao.getArquivoByEdital(id);
		InputStream inStream = new ByteArrayInputStream(arquivo.getArquivo());
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(inStream).build();
	}

	@GET
	@Path("/get-arquivo-despacho/{id}")
	@Produces("application/pdf")
	public Response getArquivoDespacho(@PathParam("id") Long id) {
		ArquivoDespacho arquivo = arqDao.getArquivoDespacho(id);
		InputStream inStream = new ByteArrayInputStream(arquivo.getArquivo());
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(inStream).build();
	}

	@GET
	@Path("/get-arquivo-despacho-proc/{numProcesso}")
	@Produces("application/pdf")
	public Response getArquivoDespachoByProc(@PathParam("numProcesso") String processo) {
		ArquivoDespacho arquivo = arqDao.getArquivoDespachoByProcesso(processo);
		InputStream inStream = new ByteArrayInputStream(arquivo.getArquivo());
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(inStream).build();
	}

	@POST
	@Path("/get-arquivo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArquivo(Arquivos param) {
		Arquivos arquivo = arqDao.getArquivo(param.getId());
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(arquivo).build();
	}

	@GET
	@Path("/get-byte-despacho/{numDespacho}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArquivoDespacho> getByteDespacho(@PathParam("numDespacho") String processo) {
		List<ArquivoDespacho> arquivo = arqDao.getByteDespachoByProcesso(processo);
		return arquivo;
	}

	@GET
	@Path("/get-byte-edital/{numProcesso}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Arquivos> getByteEdital(@PathParam("numProcesso") String processo) {
		List<Arquivos> arquivo = arqDao.getByteEditalByProcesso(processo);
		return arquivo;
	}

	@GET
	@Path("/get-pdfCompleto")
	@Produces("application/pdf")
	public Response getArquivo(@QueryParam(value = "numProcDetran") String processo,@QueryParam(value = "cpf") String cpf) throws Exception {
		//017310/2017  92330258100
		String pdfAll = arqDao.getPdfCompleto(processo,cpf);
		byte[] arquivo = ssh.downloadAnexos(pdfAll, "");
		InputStream inStream = new ByteArrayInputStream(arquivo);
		return Response.status(Status.OK).header("Content-Disposition", "filename=doc.pdf").entity(inStream).build();
	}

}
