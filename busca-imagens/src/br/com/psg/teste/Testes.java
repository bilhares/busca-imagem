package br.com.psg.teste;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import br.com.psg.config.JpaUtil;
import br.com.psg.entity.Arquivos;
import br.com.psg.entity.PsgPenalidadesPdfCompleto;

public class Testes {
	public static void main(String[] args) throws ParseException, IOException {

		try {

			// EntityManager manager;
			// manager = JpaUtil.getEntityManager();
			// EntityTransaction tx;
			// tx = manager.getTransaction();
			// Path p = Paths.get("c:\\temp\\pdfBaixado.pdf");
			// byte[] data = Files.readAllBytes(p);
			// Arquivos a = new Arquivos();
			// a.setArquivo(data);
			// tx.begin();
			// manager.persist(a);
			// tx.commit();
			// manager.close();
			//
			// System.out.println(data);

			// InputStream f = new FileInputStream("c:\\temp\\1.pdf");
			//
			// ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			//
			// int nRead;
			// byte[] data = new byte[16384];
			//
			// while ((nRead = f.read(data, 0, data.length)) != -1) {
			// buffer.write(data, 0, nRead);
			// }
			//
			// buffer.flush();
			//
			// byte[] encoded =
			// Base64.getEncoder().encode(buffer.toByteArray());
			// System.out.println(new String(encoded));

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction tx = manager.getTransaction();

			Path p = Paths.get("c:\\temp\\processo.pdf");
			byte[] data = Files.readAllBytes(p);

			PsgPenalidadesPdfCompleto pdfCompleto = new PsgPenalidadesPdfCompleto();
			pdfCompleto.setCodProcessoLecom(333);
			pdfCompleto.setCodProcessoDetran("333/333");
			pdfCompleto.setArquivo(data);

			tx.begin();
			manager.persist(pdfCompleto);
			tx.commit();
			manager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
