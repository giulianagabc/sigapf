package br.fatec.sigapf.managedbean;

import java.io.FileOutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;

import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.PlanilhaService;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Scope(value = "view")
@Service(value = "impressaoPlanilhaBean")
public class ImpressaoPlanilhaBean implements Serializable {

	private static final long serialVersionUID = -6712827120154568671L;

	@Autowired
	private ItemPlanilhaService itemPlanilhaService;
	@Autowired
	private PlanilhaService planilhaService;

	@PostConstruct
	private void init() {
	}

	public void imprimir(Planilha planilhaSelecionada) throws Exception {
		Document doc = null;
		OutputStream os = null;

		try {
			doc = new Document(PageSize.A4.rotate(), 72, 72, 72, 72);
			os = new FileOutputStream(planilhaSelecionada.getNome() + "_"
					+ new Date());
			PdfWriter.getInstance(doc, os);
			doc.open();
			Font f = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			Image img = Image.getInstance("/sigapf/src/main/webapp/resources/imagens/timbre-federal.bmp");
			img.setAlignment(Element.ALIGN_CENTER);
			doc.add(img);

			Paragraph p1 = new Paragraph("MINISTÉRIO DA DEFESA", f);
			p1.setAlignment(Element.ALIGN_CENTER);
			doc.add(p1);
			Paragraph p2 = new Paragraph("COMANDO DA AERONÁUTICA", f);
			p2.setAlignment(Element.ALIGN_CENTER);
			doc.add(p2);
			Paragraph p3 = new Paragraph("CENTRO DE COMPUTAÇÃO DE AERONÁUTICA DE SÃO JOSÉ DOS CAMPOS",f);
			p3.setAlignment(Element.ALIGN_CENTER);
			doc.add(p3);

		} finally {
			if (doc != null) {
				doc.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}
}