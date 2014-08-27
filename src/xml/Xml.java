package xml;
/**
 *
 * @author mitko
 */
public class Xml {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            String XMLpathIN="C:\\input.xml";
            String TXTpathOUT="D:\\output.txt";
            XMLReader.XMLparser(XMLpathIN,TXTpathOUT);
    }
}