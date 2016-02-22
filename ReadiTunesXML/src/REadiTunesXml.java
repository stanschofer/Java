import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import CTheFuture.Postgres;
import CTheFuture.iTunesMusic;

import java.io.File;

public class REadiTunesXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    try {


			Postgres db = new Postgres();
			db.connect("//localhost/itunesmusic", "postgres", "Brent2012");
			
			if (!db.isConnected())
			{
				System.out.println("Cannot access databse");
				System.out.println(db.getErrorMessage());
				return;
			}
	    	
	    	File fXmlFile = new File("/Users/swschofer/Documents/DataFiles/iTunes Music Library.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	String album = "";
	    	String lastAlbum = "";
	    	String artist = "";
	    	String genre = "";
	    	String albumArtist = "";
	    	String kind = "";
	    	String name = "";
	    	String location = "";
	    	String value ="";
	    			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();

	    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    	
	    	Element node = (Element) doc.getElementsByTagName("dict").item(0);
	    	NodeList nList = node.getElementsByTagName("dict");
	   // 	NodeList nList = nList1.getElementsByTagName("dict");
	    	
	    			
	    	System.out.println("----------------------------");

	    	for (int temp = 0; temp < nList.getLength(); temp++) {

	    		Node nNode = nList.item(temp);
	    				
//	    		System.out.println("\nCurrent Element :" + nNode.getNodeName());
	    				
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    			Element eElement = (Element) nNode;
	    			NodeList dictNodes = eElement.getElementsByTagName("key");
	    	    	album = "";
	    	    	artist = "";
	    	    	genre = "";
	    	    	albumArtist = "";
	    	    	kind = "";
	    	    	name = "";
	    	    	location = "";
	    	    	
	    	    	for (int temp2 = 0; temp2 < dictNodes.getLength(); temp2++) {

	    	    		Node nNode2 = dictNodes.item(temp2);
	    	    		if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
	    	    			Element eElement2 = (Element) nNode2;
 	    	    			value = eElement2.getChildNodes().item(0).getNodeValue();
	    	    			if (eElement2.getChildNodes().item(0).getNodeValue().matches("Album") )
	    	    			{
    	    					album= nNode2.getNextSibling().getTextContent();
	    	    			}
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Kind") )
	    	    			{
	    	    				kind = nNode2.getNextSibling().getTextContent();
	    	    			}
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Name") )
	    	    			{
	    	    				name = nNode2.getNextSibling().getTextContent();
	    	    			}
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Artist") )
	    	    			{
	    	    				artist = nNode2.getNextSibling().getTextContent();
	    	    			}	
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Album Artist") )
	    	    			{
	    	    				albumArtist = nNode2.getNextSibling().getTextContent();
	    	    			}	
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Genre") )
	    	    			{
	    	    				genre = nNode2.getNextSibling().getTextContent();
	    	    			}	
	    	    			else if (eElement2.getChildNodes().item(0).getNodeValue().matches("Location") )
	    	    			{
	    	    				location = nNode2.getNextSibling().getTextContent();
	    	    			}	
	    	    		}
	    	    	}
    				if (kind.contains("AAC audio file") )
    				{
    					if (lastAlbum.compareTo(album) != 0)
    					{
    						System.out.println("\"" + album + "\",\"" + name + "\",\"" + artist + "\",\"" + albumArtist + "\",\"" + genre);
    						lastAlbum = album;
    					}
    					if (location != "")
    					{
    						iTunesMusic iTunesMusicAlbum = new iTunesMusic(album,name,artist,albumArtist,genre,kind,location);
    						db.InsertAlbum(iTunesMusicAlbum);
    					}
    				}

	    		}
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	      
	}

}
