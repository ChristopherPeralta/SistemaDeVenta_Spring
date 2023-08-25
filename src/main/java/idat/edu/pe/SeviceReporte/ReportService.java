package idat.edu.pe.SeviceReporte;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.ProductoVendido;
import idat.edu.pe.entidad.Venta;
import idat.edu.pe.repositorio.VentaRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

@Service
public class ReportService {
	 
	    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

	    @Autowired

	    private VentaRepository ventaRepository;

	 

	    public byte[] generateSalesReportById(String COD_VENTA) {

	        try {

	 

	            logger.info("Generando informe de ventas para el ID: {}", COD_VENTA);

	 

	            Optional<Venta> venta = ventaRepository.findById(COD_VENTA);

	            if (!venta.isPresent()) {

	 

	                logger.error("Venta no encontrada para el ID proporcionado: {}", COD_VENTA);

	                throw new RuntimeException("Venta no encontrada para el ID proporcionado: " + COD_VENTA);

	            }

	 

	            InputStream reportStream = getClass().getResourceAsStream("/ReporteVenta.jrxml");

	            JasperReport report = JasperCompileManager.compileReport(reportStream);

	 

	            InputStream imgLogoStream = getClass().getResourceAsStream("/static/img/logo1.png");

	 

	            Map<String, Object> parameters = new HashMap<>();

	            parameters.put("logoEmpresa", imgLogoStream);

	 

	            List<Map<String, Object>> saleDataList = new ArrayList<>();

	 

	            for (ProductoVendido productoVendido : venta.get().getProductos()) {

	                Map<String, Object> saleData = new HashMap<>();

	                saleData.put("COD_VENTA", venta.get().getCOD_VENTA());

	                saleData.put("COD_CLIENTE", venta.get().getCOD_CLIENTE().getNOMBRE());

	                saleData.put("DESCRIPCION", productoVendido.getDESCRIPCION());

	                saleData.put("cantidad", productoVendido.getCantidad());

	                saleData.put("precio", productoVendido.getPrecio());

	                saleData.put("IMPORTE_TOTAL", Float.toString(productoVendido.getCantidad() * productoVendido.getPrecio()));

	                saleDataList.add(saleData);

	            }

	 

	            // Convert saleDataList to an array of maps

	            Map<String, Object>[] saleDataArray = saleDataList.toArray(new Map[saleDataList.size()]);

	 

	            parameters.put("ds", new JRBeanArrayDataSource(saleDataArray));

	 

	            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

	 

	            logger.info("Informe de ventas generado exitosamente");

	 

	            return JasperExportManager.exportReportToPdf(jasperPrint);

	        } catch (Exception e) {

	 

	            logger.error("Error al generar el informe de ventas", e);

	            throw new RuntimeException("Error al generar el informe de ventas.", e);

	        }

	    }

	 

	}

