package idat.edu.pe.controlador;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.edu.pe.SeviceReporte.ReportService;

@Controller
@RequestMapping("/reporte")
public class ReporteControlador {
	
	    @Autowired
	    private ReportService reportService;

	    @GetMapping("/generateReport/{ventaId}")

	    public void generateReport(@PathVariable String ventaId, HttpServletResponse response) {

	        try {

	            byte[] reportBytes = reportService.generateSalesReportById(ventaId);

	 

	            response.setContentType(MediaType.APPLICATION_PDF_VALUE);

	            response.setHeader("Content-Disposition", "attachment; filename=reporte.pdf");

	 

	            // Escribir los bytes del informe generado en el flujo de salida de la respuesta y vaciarlo

	            response.getOutputStream().write(reportBytes);

	            response.getOutputStream().flush();

	 

	        } catch (Exception e) {

	            e.printStackTrace();

	            // Handle the error if necessary

	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	        }

	    }

	 

	 

	 

	}

