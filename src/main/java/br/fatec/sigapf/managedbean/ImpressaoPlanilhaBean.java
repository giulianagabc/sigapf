package br.fatec.sigapf.managedbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import net.sf.jasperreports.engine.JasperExportManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.PlanilhaService;

@Scope(value = "view")
@Controller(value = "impressaoPlanilhaBean")
public class ImpressaoPlanilhaBean implements Serializable {

	private static final long serialVersionUID = -6712827120154568671L;

	@Autowired
	private ItemPlanilhaService itemPlanilhaService;
	@Autowired
	private PlanilhaService planilhaService;

	@PostConstruct
	private void init() {
	}

	public void imprimir(Planilha planilhaSelecionada) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\temp\\PDF_DevMedia.pdf"));
			document.open();
			document.add(new Paragraph("Gerando PDF - Java"));
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

}