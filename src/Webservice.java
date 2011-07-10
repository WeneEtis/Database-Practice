import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import java.io.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Fayimora Femi-Balogun
 */
public class Webservice
{
    public static void main(String[] param) 
    {
        try {
            /*  // the SAX way:
              XMLReader myReader = XMLReaderFactory.createXMLReader();
              myReader.setContentHandler(handler);
              myReader.parse(new InputSource(new URL(url).openStream()));

              */    // or if you prefer DOM:
              DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
              DocumentBuilder db = dbf.newDocumentBuilder();
              Document doc = db.parse(new URL("http://www.fayimora.com/webservice2.php?table=suscribers&database=mywebsite1").openStream());
              test(doc);
              
        } catch (IOException ex) {
            //Logger.getLogger(XMLParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            //Logger.getLogger(XMLParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            //Logger.getLogger(XMLParse.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    static void test(Node node) throws DOMException
	{
                node = node.getLastChild();
                System.out.println(node.getNodeName());
		NodeList children = node.getChildNodes();
                System.out.println(children.item(0).getNodeName());
		//if( children.item(0).getNodeType() == Node.ELEMENT_NODE && ( children.item(0).getNodeName().equals("mywebsite1") ) )
		//{ 
			for(int i=0; i<children.getLength(); i++)
			{
                            NodeList suscribers = children.item(0).getChildNodes();
                            for(int j=0; j <suscribers.getLength(); j++)
                            {
                                if(suscribers.item(1).getNodeType() == Node.ELEMENT_NODE && suscribers.item(1).getNodeValue().equals("Email"))
                                    System.out.println("Email is " + suscribers.item(1).getNodeValue());
                            }
			}
		//}
	}

}
