package br.com.psg.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.psg.entity.Arquivos;

@Path("/api")
public class Api {

	@GET
	public String getArquivo() {

		StringBuilder api = new StringBuilder();
		api.append("/penalidades ");
		api.append("/get-arquivo/{id}");
		api.append("/get-arquivo-edital/{numEdital}");
		api.append("/get-arquivo-despacho/{id}");
		api.append("/get-arquivo-despacho-proc/{numProcesso}");
		api.append("/get-byte-despacho/{numDespacho}");
		api.append("/get-byte-edital/{numProcesso}");

		return api.toString();
	}

}
