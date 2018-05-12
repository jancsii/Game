/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame;

/*-
 * #%L
 * MemoryGame
 * %%
 * Copyright (C) 2018 Faculty of Informatics, University of Debrecen
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jancsi
 */
public class Database{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    
    LinkedList<Integer> scores = new LinkedList<>();
    
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
    
    public Name getData() {
        return this.data;
    }
    
    Cards c ;
    
    private Database()
    {
        this.c = Cards.getPeldany();
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
            StreamResult result = new StreamResult(new File("target/classes/xml/tempDatas.xml"));
            
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apche.org/xslt}indent-amount", "4");
            t.transform(source, result);
        
        }catch(TransformerConfigurationException | ParserConfigurationException e)
        {
            LOGGER.error(e.getMessage());
            
        }catch (TransformerException e) 
        {
            LOGGER.error(e.getMessage());
        }
    }
    
    public void concatenateXML()
    {
        //File file1 = new File("/main/resources/xml/tempDatas.xml");
        File file1 = new File("target/classes/xml/tempDatas.xml");
        
        File file2 = new File("target/classes/xml/datas.xml");
        //try
       // {
        //URL u1 = Database.class.getResource("target/classes/xml/tempDatas.xml");
        //URL u2 = Database.class.getResource("target/classes/xml/datas.xml");
        //File file1 = Paths.get(u1.toURI()).toFile();
        //File file2 = Paths.get(u1.toURI()).toFile();
        Document doc = concatenateFiles("players", file1, file2);
        write(doc);
        //}
        //catch(URISyntaxException e)
        //{
         //   LOGGER.error(e.getMessage());
       // }
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
            LOGGER.error(e.getMessage());
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
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    
    private void write(Document doc)
    {
        try
        {
            File file = new File("target/classes/xml/datas.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            Result result = new StreamResult(file);
            transformer.transform(source, result);
        }
        catch(TransformerConfigurationException e)
        {
            LOGGER.error(e.getMessage());
        }
        catch(TransformerException e)
        {
            LOGGER.error(e.getMessage());
        }
    }
    
    public void read()
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File score = new File("target/classes/xml/datas.xml");
            //File score = new File(getClass().getResource("/xml/datas.xml").getFile());
            Document doc = db.parse(score);
            doc.getDocumentElement().normalize();
            NodeList n = doc.getElementsByTagName("player");
            LOGGER.info("Quantity of points: " + n.getLength());
            for( int i=0; i<n.getLength(); i++)
            {
                Element e = (Element) n.item(i);
                
                scores.add(Integer.parseInt(e.getElementsByTagName("score").item(0).getTextContent()));
                
            }
            c.maxScore(scores);
            LOGGER.info("Reading in...");
        }
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            LOGGER.error(e.getMessage());
        }
    }
}
