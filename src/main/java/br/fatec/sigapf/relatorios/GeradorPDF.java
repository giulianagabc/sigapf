package br.fatec.sigapf.relatorios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Repository(value = "geradorPDF")
@Transactional
public class GeradorPDF {

	private String usuarioCriador;
	private String usuarioRevisor;
	private String usuarioAprovador;
	private String usuarioInvalidador;

	public void gerarRelatorio(Planilha planilhaSelecionada,
			List<ItemPlanilha> itens) throws IOException, DocumentException {

		Document doc = null;
		FileOutputStream os = null;

		try {
			doc = new Document(PageSize.A4.rotate(), 5, 5, 72, 72);
			os = new FileOutputStream(getFilename(planilhaSelecionada));
			PdfWriter.getInstance(doc, os);
			doc.open();
			Font f_negrito = new Font(Font.BOLD);
			
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
			PdfPTable tablePlanilha = new PdfPTable(4);
			tablePlanilha.setSpacingBefore(20);
			tablePlanilha.addCell("Ordem de Serviço: "
					+ planilhaSelecionada.getIdOs().getSigla());
			tablePlanilha.addCell("Nome da Planilha: " + planilhaSelecionada.getNome());
			tablePlanilha.addCell("Empresa: " + planilhaSelecionada.getEmpresa());
			tablePlanilha.addCell("Tipo de Contagem: "
					+ planilhaSelecionada.getIdTipoContagem().getDescricao());
			tablePlanilha.addCell("Valor Deflator - Inclusão: "
					+ planilhaSelecionada.getDeflatorAdd());
			tablePlanilha.addCell("Valor Deflator - Conversão: "
					+ planilhaSelecionada.getDeflatorCon());
			tablePlanilha.addCell("Valor do Ponto de Função: "
					+ planilhaSelecionada.getValorPontoFuncao());
			tablePlanilha.addCell("Estado da Planilha: "
					+ planilhaSelecionada.getEstado().getLabel());
			verificarUsuarios(planilhaSelecionada);
			tablePlanilha.addCell("Usuário Criador: " + usuarioCriador);
			tablePlanilha.addCell("Usuário Revisor: " + usuarioRevisor);
			tablePlanilha.addCell("Usuário Aprovador: " + usuarioAprovador);
			tablePlanilha.addCell("Usuário Invalidador: " + usuarioInvalidador);
			PdfPCell escopo = new PdfPCell(new Paragraph("Escopo da Contagem: "
					+ planilhaSelecionada.getEscopo()));
			escopo.setColspan(2);
			tablePlanilha.addCell(escopo);
			PdfPCell proposito = new PdfPCell(new Paragraph(
					"Propósito da Contagem: "
							+ planilhaSelecionada.getProposito()));
			proposito.setColspan(2);
			tablePlanilha.addCell(proposito);
			doc.add(tablePlanilha);
			
			doc.add(Chunk.NEXTPAGE);
						
			PdfPTable tableItens = new PdfPTable(9);
			
			Paragraph t1 = (new Paragraph("Nome da Função", f_negrito));
			t1.setAlignment(Element.ALIGN_CENTER);
			Paragraph t2 = (new Paragraph("Descrição da Função", f_negrito));
			t2.setAlignment(Element.ALIGN_CENTER);
			Paragraph t3 = (new Paragraph("ALR/RLR", f_negrito));
			t3.setAlignment(Element.ALIGN_CENTER);
			Paragraph t4 = (new Paragraph("DER", f_negrito));
			t4.setAlignment(Element.ALIGN_CENTER);
			Paragraph t5 = (new Paragraph("Tipo da Função", f_negrito));
			t5.setAlignment(Element.ALIGN_CENTER);
			Paragraph t6 = (new Paragraph("Tipo do Deflator", f_negrito));
			t6.setAlignment(Element.ALIGN_CENTER);
			Paragraph t7 = (new Paragraph("Complexidade", f_negrito));
			t7.setAlignment(Element.ALIGN_CENTER);
			Paragraph t8 = (new Paragraph("PF Local", f_negrito));
			t8.setAlignment(Element.ALIGN_CENTER);
			Paragraph t9 = (new Paragraph("PF Total", f_negrito));
			t9.setAlignment(Element.ALIGN_CENTER);
			
			tableItens.addCell(t1);
			tableItens.addCell(t2);
			tableItens.addCell(t3);
			tableItens.addCell(t4);
			tableItens.addCell(t5);
			tableItens.addCell(t6);
			tableItens.addCell(t7);
			tableItens.addCell(t8);
			tableItens.addCell(t9);
			
			for (ItemPlanilha ip : itens){
				tableItens.addCell(ip.getNome());
				tableItens.addCell(ip.getDescricao());
				tableItens.addCell("" + ip.getQuantidadeRl());
				tableItens.addCell("" + ip.getQuantidadeTd());
				tableItens.addCell(ip.getTipoFuncao().getSigla());
				tableItens.addCell(ip.getTipoDeflator().getSigla());
				tableItens.addCell(ip.getComplexidade().getLabel());
				tableItens.addCell("" + ip.getQuantidadePontoFuncaoLocal());
				tableItens.addCell("" + ip.getQuantidadePontoFuncaoTotal());
			}
			
			doc.add(tableItens);

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
		return "C:\\Users\\giulianagabc\\Desktop\\"
				+ planilhaSelecionada.getNome()
				+ "_"
				+ planilhaSelecionada.getIdOs().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getIdProjeto()
						.getSigla() + "_"
				+ planilhaSelecionada.getEstado().getLabel() + ".pdf";
	}

	public void verificarUsuarios(Planilha planilhaSelecionada) {
		if (planilhaSelecionada.getIdUsuarioCriador() == null) {
			usuarioCriador = "-";
		} else {
			usuarioCriador = planilhaSelecionada.getIdUsuarioCriador()
					.getIdPatente().getSigla() + " "
					+ planilhaSelecionada.getIdUsuarioCriador().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioRevisor() == null) {
			usuarioRevisor = "-";
		} else {
			usuarioRevisor = planilhaSelecionada.getIdUsuarioRevisor()
					.getIdPatente().getSigla() + " "
					+ planilhaSelecionada.getIdUsuarioRevisor().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioAprovador() == null) {
			usuarioAprovador = "-";
		} else {
			usuarioAprovador = planilhaSelecionada.getIdUsuarioAprovador()
					.getIdPatente().getSigla() + " "
					+ planilhaSelecionada.getIdUsuarioAprovador().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioInvalidador() == null) {
			usuarioInvalidador = "-";
		} else {
			usuarioInvalidador = planilhaSelecionada.getIdUsuarioInvalidador()
					.getIdPatente().getSigla() + " "
					+ planilhaSelecionada.getIdUsuarioInvalidador().getLogin();
		}
	}
}