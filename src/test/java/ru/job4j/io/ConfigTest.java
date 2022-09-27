package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pairWOComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenEmptyStringsWithComment() {
        String path = "./data/EmptyStringWComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues().size()).isEqualTo(0);
    }

    @Test
    void whenIncorrectPairNoKey() {
        String path = "./data/NoKey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectPairNoValue() {
        String path = "./data/NoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectPairNoDelim() {
        String path = "./data/IncorrectKey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectPairManyDelims() {
        String path = "./data/IncorrectValue.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr=Arsentev");
    }
}