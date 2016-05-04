package br.fatec.sigapf.relatorios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

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
		ByteArrayOutputStream out = null;

		try {
			doc = new Document(PageSize.A4.rotate(), 5, 5, 72, 72);
			out = new ByteArrayOutputStream();
			PdfWriter.getInstance(doc, out);
			doc.open();

			Paragraph p = new Paragraph("MINISTÉRIO DA DEFESA", new Font(Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			Paragraph p2 = new Paragraph("COMANDO DA AERONÁUTICA", new Font(Font.BOLD));
			p2.setAlignment(Element.ALIGN_CENTER);
			doc.add(p2);
			Paragraph p3 = new Paragraph(
					"CENTRO DE COMPUTAÇÃO DA AERONÁUTICA DE SÃO JOSÉ DOS CAMPOS", new Font(Font.BOLD));
			p3.setAlignment(Element.ALIGN_CENTER);
			doc.add(p3);
			Paragraph pp = new Paragraph("PLANILHA");
			pp.setSpacingBefore(20);
			pp.setAlignment(Element.ALIGN_CENTER);
			doc.add(pp);
			
			PdfPTable tablePlanilha = new PdfPTable(4);
			tablePlanilha.setSpacingBefore(20);
			tablePlanilha.addCell("Ordem de Serviço: "
					+ planilhaSelecionada.getIdOs().getSigla());
			tablePlanilha.addCell("Nome da Planilha: "
					+ planilhaSelecionada.getNome());
			tablePlanilha.addCell("Empresa: "
					+ planilhaSelecionada.getEmpresa());
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

			Paragraph p4 = new Paragraph("MINISTÉRIO DA DEFESA", new Font(Font.BOLD));
			p4.setAlignment(Element.ALIGN_CENTER);
			doc.add(p4);
			Paragraph p5 = new Paragraph("COMANDO DA AERONÁUTICA", new Font(Font.BOLD));
			p5.setAlignment(Element.ALIGN_CENTER);
			doc.add(p5);
			Paragraph p6 = new Paragraph(
					"CENTRO DE COMPUTAÇÃO DA AERONÁUTICA DE SÃO JOSÉ DOS CAMPOS", new Font(Font.BOLD));
			p6.setAlignment(Element.ALIGN_CENTER);
			doc.add(p6);
			Paragraph pi = new Paragraph("ITENS DA PLANILHA");
			pi.setSpacingBefore(20);
			pi.setAlignment(Element.ALIGN_CENTER);
			doc.add(pi);
			
			PdfPTable tableItens = new PdfPTable(9);
			tableItens.setSpacingBefore(20);
			Paragraph t1 = (new Paragraph("Nome da Função", new Font(Font.BOLD)));
			t1.setAlignment(Element.ALIGN_CENTER);
			Paragraph t2 = (new Paragraph("Descrição da Função", new Font(Font.BOLD)));
			t2.setAlignment(Element.ALIGN_CENTER);
			Paragraph t3 = (new Paragraph("ALR/RLR", new Font(Font.BOLD)));
			t3.setAlignment(Element.ALIGN_CENTER);
			Paragraph t4 = (new Paragraph("DER", new Font(Font.BOLD)));
			t4.setAlignment(Element.ALIGN_CENTER);
			Paragraph t5 = (new Paragraph("Tipo da Função", new Font(Font.BOLD)));
			t5.setAlignment(Element.ALIGN_CENTER);
			Paragraph t6 = (new Paragraph("Tipo do Deflator", new Font(Font.BOLD)));
			t6.setAlignment(Element.ALIGN_CENTER);
			Paragraph t7 = (new Paragraph("Complexidade", new Font(Font.BOLD)));
			t7.setAlignment(Element.ALIGN_CENTER);
			Paragraph t8 = (new Paragraph("PF Local", new Font(Font.BOLD)));
			t8.setAlignment(Element.ALIGN_CENTER);
			Paragraph t9 = (new Paragraph("PF Total", new Font(Font.BOLD)));
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

			for (ItemPlanilha ip : itens) {
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
			if (out != null) {
				out.close();
			}
		}
		gerarRespostaHttp(planilhaSelecionada, out);
	}

	public String getFilename(Planilha planilhaSelecionada) {
		return planilhaSelecionada.getNome()
				+ "_"
				+ planilhaSelecionada.getIdOs().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getIdProjeto()
						.getSigla() + "_"
				+ planilhaSelecionada.getEstado().getLabel();
	}

	private final void gerarRespostaHttp(Planilha planilhaSelecionada, ByteArrayOutputStream out)
			throws DocumentException {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			externalContext
					.setResponseHeader("Content-Type", "application/pdf");
			externalContext.setResponseHeader("Content-disposition",
					"attachment; filename=\""
							+ getFilename(planilhaSelecionada) + ".pdf\"");
			externalContext.getResponseOutputStream().write(out.toByteArray());
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new SystemRuntimeException(
					"Ocorreu um problema ao gerar um relatório: "
							+ e.getLocalizedMessage());
		}
	}

	public void verificarUsuarios(Planilha planilhaSelecionada) {
		if (planilhaSelecionada.getIdUsuarioCriador() == null) {
			usuarioCriador = "-";
		} else {
			usuarioCriador = planilhaSelecionada.getIdUsuarioCriador()
					.getIdPatente().getSigla()
					+ " "
					+ planilhaSelecionada.getIdUsuarioCriador().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioRevisor() == null) {
			usuarioRevisor = "-";
		} else {
			usuarioRevisor = planilhaSelecionada.getIdUsuarioRevisor()
					.getIdPatente().getSigla()
					+ " "
					+ planilhaSelecionada.getIdUsuarioRevisor().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioAprovador() == null) {
			usuarioAprovador = "-";
		} else {
			usuarioAprovador = planilhaSelecionada.getIdUsuarioAprovador()
					.getIdPatente().getSigla()
					+ " "
					+ planilhaSelecionada.getIdUsuarioAprovador().getLogin();
		}
		if (planilhaSelecionada.getIdUsuarioInvalidador() == null) {
			usuarioInvalidador = "-";
		} else {
			usuarioInvalidador = planilhaSelecionada.getIdUsuarioInvalidador()
					.getIdPatente().getSigla()
					+ " "
					+ planilhaSelecionada.getIdUsuarioInvalidador().getLogin();
		}
	}
}