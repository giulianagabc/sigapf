package br.fatec.sigapf.relatorios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Repository(value = "geradorPDF")
@Transactional
public class GeradorPDF {

	public void gerarRelatorio(Planilha planilhaSelecionada,
			List<ItemPlanilha> itens) throws IOException, DocumentException {

		Document doc = null;
		FileOutputStream os = null;

		try {
			doc = new Document(PageSize.A4.rotate(), 72, 72, 72, 72);
			os = new FileOutputStream(getFilename(planilhaSelecionada));
			PdfWriter.getInstance(doc, os);
			doc.open();
			Paragraph p = new Paragraph("MINISTÉRIO DA DEFESA");
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			Paragraph p2 = new Paragraph("COMANDO DA AERONÁUTICA");
			p2.setAlignment(Element.ALIGN_CENTER);
			doc.add(p2);
			Paragraph p3 = new Paragraph(
					"CENTRO DE COMPUTAÇÃO DA AERONÁUTICA DE SÃO JOSÉ DOS CAMPOS");
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

	public String getFilename(Planilha planilhaSelecionada) {
		return "C:\\Users\\giulianagabc\\Desktop\\" + planilhaSelecionada.getNome()
				+ "_"
				+ planilhaSelecionada.getIdOs().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getIdProjeto()
						.getSigla() + "_"
				+ planilhaSelecionada.getEstado().getLabel() + ".pdf";
	}
}