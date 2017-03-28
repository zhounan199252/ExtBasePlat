<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.pmcc.soft.core.utils.SystemPropertyUtil" %>

<input type=hidden name="test1" id="test1" value="<%= request.getParameter("fid") %>"/>
<%!
    String filename = "";
    String filecname = "";
    Map parameters = new HashMap();

    private void prepare(HttpServletRequest request) {
        String fileid = request.getParameter("fid");
        switch (Integer.valueOf(fileid)) {
            case 1: {
                filename = "examCard";
                filecname = "准考证号";
                parameters.put("BATCH_ID",  request.getParameter("batchId"));
                parameters.put("PHOTO_DIR", SystemPropertyUtil.getProperty("system.pic.path"));
                parameters.put("LOGO_DIR", request.getSession().getServletContext().getRealPath("/") + "jasper/");
                break;
            }

            default: {
            }
        }
    }
%>
<%
    prepare(request);
    String type = request.getParameter("type");
    File reportFile = new File(application.getRealPath("/jasper/" + filename + ".jasper"));
    if (!reportFile.exists())
        throw new JRRuntimeException("error");
    response.setCharacterEncoding("UTF-8");
      /*       JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath()); */


    //  String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=KTCReport";
    //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
    //   Connection conn = DriverManager.getConnection(url,"sa","123456");

    //          String url = "jdbc:jtds:sqlserver://192.168.17.112:1433;DatabaseName=CJDT";

    //       String url = "jdbc:jtds:sqlserver://172.16.166.164:1433;DatabaseName=CJDT";

    //       Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance(); 
    //   Connection conn = DriverManager.getConnection(url,"sa","123456");
    //         Connection conn = DriverManager.getConnection(url,"sa","pmcc*123456");

    //      Connection conn = DriverManager.getConnection(url,"sa","123456");


    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
    Properties pro = new Properties();
    pro.load(inputStream);
    String url = pro.getProperty("jdbc.url");
    String user = pro.getProperty("jdbc.username");
    String pwd = pro.getProperty("jdbc.password");
    Connection conn = DriverManager.getConnection(url, user, pwd);


    try {
        /*	   
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
           
           JRHtmlExporter exporter = new JRHtmlExporter();

           StringBuffer sbuffer = new StringBuffer();

           session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
          
           exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING , "UTF-8"); 
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
           exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
           exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");

           exporter.exportReport(); */


        if (type == null || type == "null" || type.equals("null")) {
            response.setCharacterEncoding("UTF-8");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JRHtmlExporter exporter = new JRHtmlExporter();


            StringBuffer sbuffer = new StringBuffer();

            session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);

            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
            exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, "pt");
            exporter.exportReport();
        } else if (type.equals("pdf") || type == "pdf") {
            System.out.println("ssss" + type);
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=" + filecname + ".xls");

            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
            out.clear();
            out = pageContext.pushBody();

        } else if (type.equals("excel")) {
            JRXlsExporter exporter = new JRXlsExporter();
            ByteArrayOutputStream oStream = new ByteArrayOutputStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.exportReport();

            byte[] bytes = oStream.toByteArray();
            response.setContentType("application/vnd.ms-excel");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=" + filecname + ".xls");
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
            conn.close();
            out.clear();
            out = pageContext.pushBody();
        } else if (type.equals("word")) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(application.getRealPath("/jasper/" + filename + ".jasper"), parameters, conn);
            ByteArrayOutputStream oStrEeam = new ByteArrayOutputStream();
            JRExporter exporter = new JRRtfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStrEeam);
            exporter.exportReport();

            byte[] bytes = oStrEeam.toByteArray();
            if (bytes != null && bytes.length > 0) {
                response.reset();
                response.setContentType("application/msword;charset=GBK");
                response.setHeader("Content-Disposition", "attachment;filename=" + filecname + ".doc");

                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                conn.close();
                out.clear();
                out = pageContext.pushBody();
            }
        } else {

        }


    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        if (conn != null) {
            conn.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }


%>
