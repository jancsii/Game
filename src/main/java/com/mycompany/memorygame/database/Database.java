/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame.database;

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

import com.mycompany.memorygame.model.Cards;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
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
 * The {@link Database} class is responsible for handle the {@code xml} files.
 * 
 * <p>
 * The class contains methods which needed to read, write or concatenate xml files.
 * The class works with datas.xml and tempDatas.xml files.
 * </p>
 * 
 */
public class Database{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    
    LinkedList<Integer> scores = new LinkedList<>();
    
    private Name data;
    
    Cards c;
    
    public File temptemp;
    public File temptemp2;
    
    //private Database() {}
    
    private static Database peldany;
    
    /**
    * We control the Database class to have only one object.
    * 
    * <p>
    * If there is no instance of {@link Database} class, then creates one, 
    * if already exists then passing that.
    * </p>
    *
    * @return a {@link Database} object
    */
    public static Database getPeldany()
    {
        if(peldany == null)
            peldany = new Database();
        return peldany;
    }

    /**
    * Sets the data to the given parameters.
    * 
    * @param data is a {@link Name} object
    */
    public void setData(Name data) {
        this.data = data;
    }
    
    /**
    * Returns a {@link Name} object.
    * 
    * @return a {@link Name} object
    */
    public Name getData() {
        return this.data;
    }
    
    /**
    * Constructor of the class Database.
    */
    private Database()
    {
        this.c = Cards.getPeldany();
    }
    
    /**
    * Sets the object's cards to the given parameters.
    * 
    * @param cards is a {@link Cards} object
    */
    public void getCards(Cards cards)
    {
        this.c = cards;
    }
    
    /**
    * Creates the {@code tempDatas.xml} file with datas of the actual player.
    * 
    * <p>
    * The XML documents are created with help of nodes.
    * In case of every node we decide what role he must be.
    * </p>
    */
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
           //System.out.println(data.getNamefield());
            player.appendChild(name);
            
            Element score = doc.createElement("score");
            score.appendChild(doc.createTextNode(Integer.toString(c.getScore())));
            player.appendChild(score);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            //StreamResult result = new StreamResult(new File("target/classes/xml/tempDatas.xml"));
            
            InputStream in = getClass().getClassLoader().getResourceAsStream("xml/tempDatas.xml");
            File tempFile = File.createTempFile("vmi3", ".xml");
            tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[10 * 1024];
            temptemp = tempFile;
            for (int length; (length = in.read(buffer)) != -1;) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            out.close();
            StreamResult result = new StreamResult(tempFile);
            
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apche.org/xslt}indent-amount", "4");
            t.transform(source, result);
        
        }catch(TransformerConfigurationException | ParserConfigurationException | IOException e )
        {
            LOGGER.error(e.getMessage());
            
        }catch (TransformerException e) 
        {
            LOGGER.error(e.getMessage());
        }
    }
    
    /**
    * Creates the final {@code datas.xml}.
    * @throws java.io.FileNotFoundException when file not found
    * @throws IOException when occured some problem with input or output file
    */
    public void concatenateXML() throws FileNotFoundException, IOException
    {
        //File file1 = new File("/main/resources/xml/tempDatas.xml");
        
        //File file1 = new File("target/classes/xml/tempDatas.xml");
        //File file2 = new File("target/classes/xml/datas.xml");
        
        /*InputStream in = getClass().getClassLoader().getResourceAsStream("xml/tempDatas.xml");
            File tempFile = File.createTempFile("vmi4", ".xml");
            tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[10 * 1024];
            temptemp = tempFile;
            for (int length; (length = in.read(buffer)) != -1;) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            out.close();*/
            //StreamResult result = new StreamResult(tempFile);
            
        InputStream in2 = getClass().getClassLoader().getResourceAsStream("xml/datas.xml");
            File tempFile2 = File.createTempFile("vmi5", ".xml");
            tempFile2.deleteOnExit();
            FileOutputStream out2 = new FileOutputStream(tempFile2);
            byte[] buffer2 = new byte[10 * 1024];
            temptemp2 = tempFile2;
            for (int length; (length = in2.read(buffer2)) != -1;) {
                out2.write(buffer2, 0, length);
            }
            in2.close();
            out2.flush();
            out2.close();
            //StreamResult result2 = new StreamResult(tempFile2);
            
        
        
        //try
       // {
        //URL u1 = Database.class.getResource("target/classes/xml/tempDatas.xml");
        //URL u2 = Database.class.getResource("target/classes/xml/datas.xml");
        //File file1 = Paths.get(u1.toURI()).toFile();
        //File file2 = Paths.get(u1.toURI()).toFile();
        Document doc = concatenateFiles("players", temptemp, tempFile2);
        write(doc);
        //}
        //catch(URISyntaxException e)
        //{
         //   LOGGER.error(e.getMessage());
       // }
    }
    
    /**
    * Concatenate xml files with help of String.
    */
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
    
    /**
    * Concatenate xml files with help of XPathExpression.
    */
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
    
    /**
    * Creates the datas.xml file.
    */
    private void write(Document doc) throws IOException
    {
        try
        {
            //File file = new File("target/classes/xml/datas.xml");
            InputStream in3 = getXMLjar();//getClass().getClassLoader().getResourceAsStream("xml/datas.xml");
            File valami = new File("datas.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            Result result = new StreamResult(valami);
            transformer.transform(source, result);
            makingJar(valami.getAbsolutePath());
            valami.delete();
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
    
    /**
    * Reading from {@code datas.xml} file the scores of players.
    */
    public void read()
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //File score = new File("target/classes/xml/datas.xml");
            
            InputStream in = getClass().getClassLoader().getResourceAsStream("xml/datas.xml");
            File tempFile = File.createTempFile("vmi7", ".xml");
            tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[10 * 1024];
            temptemp = tempFile;
            for (int length; (length = in.read(buffer)) != -1;) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            out.close();
            //StreamResult result = new StreamResult(tempFile);
            
            //File score = new File(getClass().getResource("/xml/datas.xml").getFile());
            Document doc = db.parse(tempFile);
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
    
    public void makingJar(String parameter) throws FileNotFoundException, IOException {

        try {
            
            String jarName = Database.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String fileName = parameter;
            
            // Create file descriptors for the jar and a temp jar.
            File jarFile = new File(jarName);
            File tempJarFile = new File(jarName + ".tmp");
            
            // Open the jar file.
            JarFile jar = new JarFile(jarFile);
            System.out.println(jarName + " opened.");
            
            // Initialize a flag that will indicate that the jar was updated.
            boolean jarUpdated = false;
            
            try {
                // Create a temp jar file with no manifest. (The manifest will
                // be copied when the entries are copied.)
                
                //Manifest jarManifest = jar.getManifest();
                JarOutputStream tempJar
                        = new JarOutputStream(new FileOutputStream(tempJarFile));
                
                // Allocate a buffer for reading entry data.
                byte[] buffer = new byte[1024];
                int bytesRead;
                
                try {
                    // Open the given file.
                    
                    FileInputStream file = new FileInputStream(fileName);
                    
                    try {
                        // Create a jar entry and add it to the temp jar.
                        
                        JarEntry entry = new JarEntry("xml/datas.xml");
                        tempJar.putNextEntry(entry);
                        
                        // Read the file and write it to the jar.
                        while ((bytesRead = file.read(buffer)) != -1) {
                            tempJar.write(buffer, 0, bytesRead);
                        }
                        
                        System.out.println(entry.getName() + " added.");
                    } finally {
                        file.close();
                    }
                    
                    // Loop through the jar entries and add them to the temp jar,
                    // skipping the entry that was added to the temp jar already.
                    for (Enumeration entries = jar.entries(); entries.hasMoreElements();) {
                        // Get the next entry.
                        
                        JarEntry entry = (JarEntry) entries.nextElement();
                        
                        // If the entry has not been added already, add it.
                        if (!entry.getName().equals("xml/datas.xml")) {
                            // Get an input stream for the entry.
                            
                            InputStream entryStream = jar.getInputStream(entry);
                            
                            // Read the entry and write it to the temp jar.
                            tempJar.putNextEntry(entry);
                            
                            while ((bytesRead = entryStream.read(buffer)) != -1) {
                                tempJar.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                    
                    jarUpdated = true;
                } catch(IOException ex) {
                    System.out.println(ex);
                    
                    // Add a stub entry here, so that the jar will close without an
                    // exception.
                    tempJar.putNextEntry(new JarEntry("stub"));
                } finally {
                    tempJar.close();
                }
            } finally {
                jar.close();
                System.out.println(jarName + " closed.");
                
                // If the jar was not updated, delete the temp jar file.
                if (!jarUpdated) {
                    tempJarFile.delete();
                }
            }
            
            // If the jar was updated, delete the original jar file and rename the
            // temp jar file to the original name.
            if (jarUpdated) {
                jarFile.delete();
                tempJarFile.renameTo(jarFile);
                System.out.println(jarName + " updated.");
            }
            
        }   catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
        }

    }
    
    private InputStream getXMLjar() {
        InputStream result = null;
        try {
            String jarName = Database.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File jarFile = new File(jarName);
            JarFile jar = new JarFile(jarFile);
            for (Enumeration entries = jar.entries(); entries.hasMoreElements();) {
                JarEntry entry = (JarEntry) entries.nextElement();
                if (entry.getName().equals("xml/datas.xml")) {
                    // Get an input stream for the entry.
                    result = clone(jar.getInputStream(entry));
                }
            }
        } catch (IOException | URISyntaxException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }


    private static InputStream clone(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
            return is1;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
