/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.psg.ssh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.management.RuntimeErrorException;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import br.com.psg.config.PropertiesConfig;

/**
 *
 * @author fsalles
 */
public class SSHDownload {

	Properties prop = new Properties();

	BufferedInputStream bis;
	BufferedOutputStream bos;

	public byte[] downloadAnexos(String anexo, String nomeAnexo) throws Exception {

		PropertiesConfig configProp = new PropertiesConfig();
		prop.load(configProp.getProperties());

		String SFTPHOST = prop.getProperty("hostSSH");
		int SFTPPORT = Integer.parseInt(prop.getProperty("portSSH"));
		String SFTPUSER = prop.getProperty("userSSH");
		String SFTPPASS = prop.getProperty("passSSH");
		String SFTPWORKINGDIR = anexo;
		String ANEXOlOCAL = nomeAnexo;

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000);
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			byte[] buffer = new byte[1024];
			bis = new BufferedInputStream(channelSftp.get(SFTPWORKINGDIR));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			channelSftp.get(SFTPWORKINGDIR, out);

			return out.toByteArray();

			// byte[] parseBase64Binary =
			// DatatypeConverter.parseBase64Binary(out.toByteArray().toString());
			// FileUtils.writeByteArrayToFile(new File("c:\\temp\\ssh.pdf"),
			// out.toByteArray());

			// System.out.println(out);

			// File newFile = new File(PathConfig.caminhoAnexoLocal +
			// nomeAnexo);

			// OutputStream os = new FileOutputStream(newFile);
			// bos = new BufferedOutputStream(os);
			// int readCount;
			// while ((readCount = bis.read(buffer)) > 0) {
			// bos.write(buffer, 0, readCount);
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.disconnect();
			channelSftp.quit();
		}
		throw new RuntimeException();
	}

	public List<String> downloadAnexosCorrigidos() throws Exception {
		List<String> anexosCorrigidos = new ArrayList<>();

		String SFTPHOST = prop.getProperty("hostSSH");
		int SFTPPORT = Integer.parseInt(prop.getProperty("portSSH"));
		String SFTPUSER = prop.getProperty("userSSH");
		String SFTPPASS = prop.getProperty("passSSH");
		String SFTPWORKINGDIR = prop.getProperty("dirCorrigidos");

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000);
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			channelSftp.cd(SFTPWORKINGDIR);

			Vector<ChannelSftp.LsEntry> list = channelSftp.ls(".");

			for (ChannelSftp.LsEntry oListItem : list) {
				if (!oListItem.getAttrs().isDir()) {
					if (oListItem.getFilename().endsWith(".pdf")) {
						downloadAnexos(SFTPWORKINGDIR + oListItem.getFilename(), oListItem.getFilename());
						// channelSftp.rename(SFTPWORKINGDIR + "/" +
						// oListItem.getFilename(), SFTPWORKINGDIR + "/" +
						// oListItem.getFilename().replace(".pdf",
						// ".processando"));
						anexosCorrigidos.add(oListItem.getFilename());
					}
				}
			}
			return anexosCorrigidos;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.disconnect();
			channelSftp.quit();
			// bis.close();
			// bos.close();
		}
		throw new RuntimeException();
	}

	public void rename(String nomeArquivo, String status) throws Exception {

		Session session = null;
		ChannelSftp channelSftp = null;
		try {
			String SFTPHOST = prop.getProperty("hostSSH");
			int SFTPPORT = Integer.parseInt(prop.getProperty("portSSH"));
			String SFTPUSER = prop.getProperty("userSSH");
			String SFTPPASS = prop.getProperty("passSSH");
			String SFTPWORKINGDIR = prop.getProperty("dirCorrigidos");

			session = null;
			Channel channel = null;
			channelSftp = null;

			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000);
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			if (nomeArquivo.contains("processando")) {
				channelSftp.rename(SFTPWORKINGDIR + "/" + nomeArquivo,
						SFTPWORKINGDIR + "/" + nomeArquivo.replace(".processando", "." + status));
			} else if (nomeArquivo.contains(".pdf")) {
				channelSftp.rename(SFTPWORKINGDIR + "/" + nomeArquivo,
						SFTPWORKINGDIR + "/" + nomeArquivo.replace(".pdf", "." + status));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.disconnect();
			channelSftp.quit();
		}

	}

	public static void main(String[] args) throws Exception {
		SSHDownload down = new SSHDownload();
		down.downloadAnexos(
				"/opt/lecom/app/tomcat7_producao/webapps/bpm/WEB-INF/classes/../../upload/cadastros/Penalidades_PDF_COMPLETO/PDF_COMPLETO_0135682017.pdf",
				"teste");
		// down.downloadAnexosCorrigidos();
		// down.downloadAnexos("/opt/lecom/app/tomcat7_aceite/webapps/bpm/upload/cadastros/Penalidades_Files/correcoes/",
		// "doc.pdf");
		// down.rename();
	}
}
