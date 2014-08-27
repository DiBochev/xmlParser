package xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mitko
 */
public class XMLReader {
    public static void XMLparser(String pathIN, String TXTpathOUT){
       Document doc=null;
    	try {
            File file = new File(pathIN);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
    	} catch (SAXException ex) {          
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Problem with XML file.");
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } NodeList nodeLst = doc.getElementsByTagName("project");
            StringBuilder out= new StringBuilder();
            for (int i = 0; i < nodeLst.getLength(); i++){
                Element project = (Element) nodeLst.item(i);
                out.append("- product=\""+project.getAttribute("product")+"\" "+"project=\""+project.getAttribute("project") +"\" "+"datetime=\""+ project.getAttribute("datetime")+"\""+System.getProperty("line.separator"));
                Element childElement = (Element) nodeLst.item(i);
                NodeList child=childElement.getElementsByTagName("module");
                for (int j = 0; j < child.getLength(); j++) {
                    Element module = (Element) child.item(j);
                    out.append("--" + module.getAttribute("name")+ System.getProperty("line.separator"));
                }
            } BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(TXTpathOUT, true));
				bw.write(out.toString());
	            bw.newLine();
	            bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
}

