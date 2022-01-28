package org.gyula.overheadCalc.service;


import com.lowagie.text.DocumentException;
import org.gyula.overheadCalc.util.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/static/";
    private SpringTemplateEngine templateEngine;
    private FlatService flatService;

    @Autowired
    public PdfService(SpringTemplateEngine templateEngine, FlatService flatService) {
        this.templateEngine = templateEngine;
        this.flatService = flatService;
    }

    public File generatePdf(int flatId) throws IOException, DocumentException {
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        System.out.println(html);
        return renderPdf(html);
    }


    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("invoice", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext() {
        Invoice newInvoice = new Invoice();
        Context context = new Context();
        context.setVariable("invoice", newInvoice.createInvoiceData(flatService.findById(52)));
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("/me/pdf-invoice", context);
    }


}
