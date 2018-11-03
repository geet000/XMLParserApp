package com.glasierinc.xmlparserapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    String xml="<audio version=\"2\" desc=\"Last updated on 18Oct2018\"><audio name=\"Introduction audio\" desc=\"Some description here! And ગુજરાતી and हिंदी text too!\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C5Hindi_Luke10.mp3\"/><folder name=\"Option 1 ગુજરાતી લખાણમાં\"><folder name=\"Suboption 1\"><audio name=\"Audio title 1.1\" desc=\"More information about this track\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 1.2 no desc\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><folder name=\"Suboption 1.1\"><folder name=\"Suboption 1.1.1\"><audio name=\"Audio title 1.1.1.1\" desc=\"More information about this track\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 1.1.1.2 no desc\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/></folder><audio name=\"Audio title 1.1.1\" desc=\"More information about this track\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 1.1.2 no desc\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/></folder></folder><audio name=\"Audio title 1\" desc=\"More information about this track\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 2 no desc\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/></folder><folder name=\"Option 2 शायद हिंदी में\"><audio name=\"Audio title 2.1\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 2.2\" desc=\"Description for 2.2\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/></folder><folder name=\"Option 3\"><audio name=\"Audio title 3.1\" desc=\"Description for 3.1\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 3.2 शायद हिंदी में\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C2Hindi_Acts8.mp3\"/><audio name=\"Audio title 3.3\" desc=\"Description for 3.3\" url=\"https://7cmdaudio.ams3.digitaloceanspaces.com/audio_v1/C3Hindi_Matthew6.mp3\"/></folder></audio>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream istream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(istream);
            traverseNodes(doc.getFirstChild().getChildNodes(),-1);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    private void traverseNodes(NodeList nl, int level) {
        level++;
        if(nl != null && nl.getLength() > 0){
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                System.out.println(n.getNodeName()+": "+n.getAttributes().getNamedItem("name").getNodeValue() + " --->" + level);
                //insert in db
                if(n.hasChildNodes()){
                    traverseNodes(n.getChildNodes(), level);
                }
            }
        }else{
            return;
        }

    }
}
