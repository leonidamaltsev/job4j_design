package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    private final NameLoad nameLoad = new NameLoad();

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void NameDoesNotContainsEqualsSymbol() {
        assertThatThrownBy(() -> nameLoad.parse("name-Ivan"))
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining("Ivan");
    }

    @Test
    void NameDoesNotContainsKey() {
        assertThatThrownBy(() -> nameLoad.parse("=Ivan"))
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining("=Ivan");
    }

    @Test
    void NameDoesNotContainsValue() {
        assertThatThrownBy(() -> nameLoad.parse("name="))
                .hasMessageContaining("this name: name= does not contain a value");
    }
}