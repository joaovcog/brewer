package com.algaworks.brewer.service;

import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.dto.PeriodoRelatorio;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) throws Exception {
		Date dataInicial = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicial(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFinal = Date.from(LocalDateTime.of(periodoRelatorio.getDataFinal(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("pDataInicial", dataInicial);
		parametros.put("pDataFinal", dataFinal);
		
		InputStream is = this.getClass().getResourceAsStream("/relatorios/relatorio_vendas_emitidas.jasper");
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}
	
}