package com.scut.PreprocessingTool.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.AbstractWordConverter;
import org.apache.poi.hwpf.converter.WordToFoConverter;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToTextConverter;

import org.w3c.dom.Document;





public class POIUtil {
    enum ConverterType {
        HTML,
        TEXT,
        XML
    }

    private static void convert(String srcPath, String destPathWithoutExtension, ConverterType type) {
        InputStream is = null;
        Writer writer = null;

        try {
            is = new FileInputStream(srcPath);
            HWPFDocument hwpfDocument = new HWPFDocument(is);
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            AbstractWordConverter converter = null;
            String method = null;
            switch (type) {
            case HTML:
                converter = new WordToHtmlConverter(document);
                method = "html";
                destPathWithoutExtension += ".html";
                break;
            case TEXT:
                converter = new WordToTextConverter(document);
                method = "text";
                destPathWithoutExtension += ".txt";
                break;
            case XML:
                converter = new WordToFoConverter(document);
                method = "xml";
                destPathWithoutExtension += ".xml";
                break;
            }
            converter.processDocument(hwpfDocument);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            writer = new FileWriter(destPathWithoutExtension);
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, method);
            DOMSource domSource = new DOMSource(converter.getDocument());
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void convertToText(String srcPath, String dstPath) {
        convert(srcPath, dstPath, ConverterType.TEXT);
    }
}
