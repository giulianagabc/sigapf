package br.fatec.sigapf.relatorios;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Scanner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.apache.velocity.tools.generic.SortTool;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.fatec.sigapf.framework.exception.SystemRuntimeException;

import com.lowagie.text.DocumentException;

public abstract class Relatorio {

	public abstract String getTemplate();

	public abstract Map<String, Object> getMapa();

	public abstract String getFilename();

	public void gerarRelatorio(boolean border) {
		gerarRelatorio(false, border);
	}

	public void gerarRelatorio(boolean paisagem, boolean border) {

		VelocityEngine ve = gerarEngine();
		ve.init();

		Template t = ve.getTemplate(getTemplate());

		VelocityContext context = gerarContext(getMapa());
		context.put("number", new NumberTool());
		context.put("date", new DateTool());
		context.put("esc", new EscapeTool());
		context.put("sorter", new SortTool());
		context.put(
				"css",
				obterTextoDeArquivo("/sigapf/src/main/resources/pdf-templates/estilo-pdf.css"));
		if (border) {
			context.put(
					"border",
					obterTextoDeArquivo("/sigapf/src/main/resources/pdf-templates/estilo-border-pdf.css"));
		}

		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		String html = writer.toString();
		if (paisagem) {
			html = html.replace("21cm 29.7cm", "29.7cm 21cm");
		}
		gerarRespostaHttp(gerarOutputStreamPDF(html));
	}

	// esse método obtem texto de dentro de arquivos
	private String obterTextoDeArquivo(String file) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream(file)));
		Scanner scanner = new Scanner(reader);
		String result = "";
		while (scanner.hasNext()) {
			result = result + scanner.nextLine();
		}
		scanner.close();
		return result;
	}

	private final VelocityEngine gerarEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty("input.encoding", "UTF-8");
		ve.setProperty("output.encoding", "UTF-8");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		return ve;
	}

	private VelocityContext gerarContext(Map<String, Object> mapa) {
		VelocityContext context = new VelocityContext();

		for (String key : mapa.keySet()) {
			context.put(key, mapa.get(key));
		}

		return context;
	}

	// Esse método é reponsável por criar o PDF a partir de um HTML
	private ByteArrayOutputStream gerarOutputStreamPDF(String html) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ITextRenderer renderer = new ITextRenderer();
			// renderer.setDocumentFromString(html);
			renderer.setDocument(html);
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			return os;
		} catch (DocumentException | IOException e) {
			throw new SystemRuntimeException("Problema ao gerar o PDF "
					+ e.getMessage());
		}
	}

	// Esse método é reponsável por enviar o PDF via download para o usuário
	private final void gerarRespostaHttp(ByteArrayOutputStream out) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			externalContext
					.setResponseHeader("Content-Type", "application/pdf");
			externalContext.setResponseHeader("Content-disposition",
					"attachment; filename=\"" + getFilename() + ".pdf\"");
			externalContext.getResponseOutputStream().write(out.toByteArray());
			facesContext.responseComplete();

		} catch (IOException e) {
			throw new SystemRuntimeException(
					"Ocorreu um problema ao gerar um relatório: "
							+ e.getLocalizedMessage());
		}
	}
}