package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {

    @XmlAttribute
    private boolean enginetype;

    @XmlAttribute
    private int thrust;
    private RocketEngineXml rocketEngineXml;

    private String[] statuses;

    public Engine() {

    }

    public Engine(boolean enginetype, int thrust, RocketEngineXml rocketEngineXml, String[] statuses) {
        this.enginetype = enginetype;
        this.thrust = thrust;
        this.rocketEngineXml = rocketEngineXml;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "enginetype=" + enginetype
                + ", thrust=" + thrust
                + ", rocketEngineXml=" + rocketEngineXml
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Engine engine = new Engine(false, 30, new RocketEngineXml("hydrogen"),
                new String[]{"Saturn", "silver"});
        JAXBContext context = JAXBContext.newInstance(Engine.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(engine, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
