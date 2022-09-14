package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    private SimpleConvert simpleConvert;

    @BeforeEach
    void iniAll() {
        simpleConvert = new SimpleConvert();
    }

    @Test
    void checkArray() {
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .element(2).isEqualTo("three");
    }

    @Test
    void checkSet() {
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .doesNotContain("six")
                .contains("three")
                .containsAnyOf("zero", "second", "six")
                .containsExactlyInAnyOrder("second", "first", "three", "five", "four")
                .element(2).isNotNull();
    }

    @Test
    void checkMap() {
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("second", "three", "five")
                .doesNotContainKey("six")
                .isNotEmpty()
                .hasFieldOrProperty("four");
    }
}