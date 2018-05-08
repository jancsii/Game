/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author jancsi
 */
public class Database{
    
    private Name data;
    
    //private Database() {}
    
    private static Database peldany;
    
    public static Database getPeldany()
    {
        if(peldany == null)
            peldany = new Database();
        return peldany;
    }

    public void setData(Name data) {
        this.data = data;
    }
    
    private Cards c;
    
    Database()
    {
        this.c = null;
    }
    
    public void getCards(Cards cards)
    {
        this.c = cards;
    }
    
    public void init()
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document doc = db.newDocument();
            
            Element players = doc.createElement("players");
            doc.appendChild(players);
            
            Element player = doc.createElement("player");
            players.appendChild(player);
            
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(data.getNamefield()));
            System.out.println(data.getNamefield());
            player.appendChild(name);
            
            Element score = doc.createElement("score");
            score.appendChild(doc.createTextNode(Integer.toString(c.getScore())));
            player.appendChild(score);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/xml/tempDatas.xml"));
            
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apche.org/xslt}indent-amount", "4");
            t.transform(source, result);
        
        }catch(TransformerConfigurationException | ParserConfigurationException e)
        {
            System.out.println(e.getMessage());
            
        }catch (TransformerException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void concatenateXML()
    {
        File file1 = new File("src/main/resources/xml/tempDatas.xml");
        File file2 = new File("src/main/resources/xml/datas.xml");
        Document doc = concatenateFiles("players", file1, file2);
        write(doc);
    }
    
    private static Document concatenateFiles(String expressions, File... files)
    {
        try
        {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression compiledExpression = xPath.compile(expressions);
            return concatenatePaths(compiledExpression, files);
        } 
        catch(XPathExpressionException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private static Document concatenatePaths(XPathExpression expressions, File... files)
    {
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document base = docBuilder.parse(files[0]);
            Node results = (Node) expressions.evaluate(base, XPathConstants.NODE);
            if(results == null)
            {
                throw new IOException(files[0] + " Doesn't exist node");
            }
            for(int i=1; i<files.length; i++)
            {
                Document concat = docBuilder.parse(files[i]);
                Node result = (Node) expressions.evaluate(concat, XPathConstants.NODE);
                while(result.hasChildNodes())
                {
                    Node child = result.getFirstChild();
                    result.removeChild(child);
                    child = base.importNode(child, true);
                    results.appendChild(child);
                }
            }
            return base;
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private static void write(Document doc)
    {
        try
        {
            File file = new File("src/main/resources/xml/datas.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            Result result = new StreamResult(file);
            transformer.transform(source, result);
        }
        catch(TransformerConfigurationException e)
        {
            System.out.println(e.getMessage());
        }
        catch(TransformerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
