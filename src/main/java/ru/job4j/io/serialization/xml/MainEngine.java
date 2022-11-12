package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainEngine {
    public static void main(String[] args) throws JAXBException, IOException {
        Engine engine = new Engine(false, 30, new RocketEngineXml("hydrogen"),
                new String[]{"Saturn", "silver"});
        JAXBContext context = JAXBContext.newInstance(Engine.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(engine, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Engine result = (Engine) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
