package com.cantonsoft.admin.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.cantonsoft.framework.exception.ServiceException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cantonsoft.framework.mvc.util.MsgParser;
import com.cantonsoft.framework.util.Config;

@Component
public class ReportUtils {
	private static final String EXPORT_TYPE_EXCEL = "excel";
	private static final String EXPORT_TYPE_PDF = "pdf";
	private String EXPORT_FILE_TYPE = null;
	@Autowired
	private MsgParser msgParser;
	@Autowired
	private Config config;

	@PostConstruct
	public void init(){
		this.EXPORT_FILE_TYPE = config.getProperty("report.export.fileType");
	}
	private void exportPdf(JasperPrint jasperPrint,String defaultFilename,  
      HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {  
        response.setContentType("application/pdf");  
        String defaultname=null;      
        if(defaultFilename != null && defaultFilename.trim() != ""){  
           defaultname=defaultFilename+".pdf";  
        }else{  
           defaultname="export.pdf";  
        }  
        setFileDownloadHeader(request, response, defaultname);
        ServletOutputStream ouputStream = response.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream); 
        ouputStream.flush();  
        ouputStream.close();  
    }
    
	@SuppressWarnings({ "deprecation" })
    private void exportXls(JasperPrint jasperPrint,String defaultFilename,  
    HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
	     String defaultname=null;      
	     if(defaultFilename != null && defaultFilename.trim() != ""){  
	        defaultname=defaultFilename+".xls";  
	     }else{  
	         defaultname="export.xls";  
	     }  

	     setFileDownloadHeader(request, response, defaultname);
		 JRXlsExporter exporter=new JRXlsExporter();
         exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	     exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	     exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	     exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	     response.setContentType("application/vnd_ms-excel");
	     exporter.exportReport();		      

	}
    
	public void export(String filePath,Map<String,Object> maps,List<?> list,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		if(list.isEmpty()){
			throw new ServiceException(msgParser.parse("error.report.employ"));
		}
		//maps.put("PAGE_RECORD_LIMIT", Integer.parseInt(config.getProperty("report.export.record.max")));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		maps.put("time", format.format(new Date()));
		format = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = maps.get("fileName").toString() + format.format(new Date());
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(filePath);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, maps, new JRBeanCollectionDataSource(list));
	        if(EXPORT_FILE_TYPE.equals(EXPORT_TYPE_PDF)){
	        	exportPdf(jasperPrint, fileName, request,response);
	        }else if(EXPORT_FILE_TYPE.equals(EXPORT_TYPE_EXCEL)){
	        	exportXls(jasperPrint, fileName, request, response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public Map<String, Object> getReportParaMap(){
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("company", msgParser.parse("info.footer"));
		maps.put("form", msgParser.parse("report.common.form"));
		maps.put("reportTitle", msgParser.parse("report.common.reportTitle"));
		maps.put("printTime", msgParser.parse("report.common.printTime"));
		maps.put("recordNumber", msgParser.parse("report.unit.recordNumber"));
		maps.put("sum", msgParser.parse("report.common.sum"));
		maps.put("unit_money", msgParser.parse("report.unit.money"));
		maps.put("unit_page", msgParser.parse("report.unit.pageNumber"));
		maps.put("unit_record", msgParser.parse("report.unit.recordNumber"));
		return maps;
    }
    
    private void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            	fileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
            	fileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
   	        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        } catch (UnsupportedEncodingException e) {
        }
    }
}
