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
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The {@link Database} class is responsible for handle the {@code XML} files.
 *
 * <p>
 * The class contains methods which needed to read and write the XML file. The
 * class works with .data.XML hidden file.
 * </p>
 *
 */
public class Database {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    LinkedList<Integer> scores = new LinkedList<>();

    private Name data;

    Cards c;

    private static Element root;

    private static Database instanceDB;

    /**
     * We control the Database class to have only one object.
     *
     * <p>
     * If there is no instance of {@link Database} class, then creates one, if
     * already exists then passing that.
     * </p>
     *
     * @return a {@link Database} object
     */
    public static Database getInstance() {
        if (instanceDB == null) {
            instanceDB = new Database();
        }
        return instanceDB;
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
    private Database() {
        this.c = Cards.getInstanceCards();
    }

    /**
     * Sets the object's cards to the given parameters.
     *
     * @param cards is a {@link Cards} object
     */
    public void getCards(Cards cards) {
        this.c = cards;
    }

    /**
     * Creates the {@code .data.xml} file.
     *
     * <p>
     * The XML document is created in the user's home directory as hidden file.
     * At the first time creates the file, and at other runs the project will
     * use the file which has been created at the first running.
     * </p>
     */
    public void init() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc;

            File input;
            input = new File(System.getProperty("user.home") + "/.data.xml");

            if (input.exists()) {
                doc = db.parse(input);
            } else {
                doc = db.newDocument();
            }

            if (doc.getDocumentElement() == null) {
                root = doc.createElement("players");
                doc.appendChild(root);
            } else {
                root = doc.getDocumentElement();
            }

            Element player = doc.createElement("player");
            root.appendChild(player);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(data.getNamefield()));
            player.appendChild(name);

            Element score = doc.createElement("score");
            score.appendChild(doc.createTextNode(Integer.toString(c.getScore())));
            player.appendChild(score);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(input);

            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apche.org/xslt}indent-amount", "4");
            t.transform(source, result);

        } catch (TransformerConfigurationException | ParserConfigurationException | IOException e) {
            LOGGER.error(e.getMessage());

        } catch (TransformerException | SAXException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Reading from {@code .data.xml} file the scores of players.
     */
    public void read() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File input;

            input = new File(System.getProperty("user.home") + "/.data.xml");

            if (input.exists()) {

                Document doc = db.parse(input);
                doc.getDocumentElement().normalize();
                NodeList n = doc.getElementsByTagName("player");
                LOGGER.info("Quantity of points: {}" ,n.getLength());
                for (int i = 0; i < n.getLength(); i++) {
                    Element e = (Element) n.item(i);
                    scores.add(Integer.parseInt(e.getElementsByTagName("score").item(0).getTextContent()));
                }
                c.maxScore(scores);
                LOGGER.info("Reading in...");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
