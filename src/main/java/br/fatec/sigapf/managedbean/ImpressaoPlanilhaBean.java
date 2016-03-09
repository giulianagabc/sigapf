package br.fatec.sigapf.managedbean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.PlanilhaService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

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
		Document document = new Document(PageSize.A4.rotate(), 72, 72, 72, 72);
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\giulianagabc\\Desktop\\"
							+ planilhaSelecionada.getNome()
							+ "_"
							+ planilhaSelecionada.getIdOs().getSigla()
							+ "_"
							+ planilhaSelecionada.getIdOs().getIdContrato()
									.getSigla()
							+ "_"
							+ planilhaSelecionada.getIdOs().getIdContrato()
									.getIdProjeto().getSigla() + "_"
							+ planilhaSelecionada.getEstado().getLabel()
							+ ".pdf"));
			document.open();
			
			//Image 

			//Header
			Paragraph p1 = new Paragraph("MINISTÉRIO DA DEFESA");
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			Paragraph p2 = new Paragraph("COMANDO DA AERONÁUTICA");
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			Paragraph p3 = new Paragraph(
					"CENTRO DE COMPUTAÇÃO DA AERONÁUTICA DE SÃO JOSÉ DOS CAMPOS");
			p3.setAlignment(Element.ALIGN_CENTER);
			document.add(p3);

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
		Mensagem.informacao("PDF gerado com sucesso!");
	}
}