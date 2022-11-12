package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rocketEngineXml")
public class RocketEngineXml {

    @XmlAttribute
    private String fuel;

    public RocketEngineXml() {

    }

    public RocketEngineXml(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "RocketEngineXml{"
                + "fuel='" + fuel + '\''
                + '}';
    }
}
