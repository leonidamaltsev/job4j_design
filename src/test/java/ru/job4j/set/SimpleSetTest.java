package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    public void whenAddAll() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(4)).isTrue();
        assertThat(set.contains(4)).isTrue();
        assertThat(set.add(7)).isTrue();
        assertThat(set.contains(7)).isTrue();
        assertThat(set.add(8)).isTrue();
        assertThat(set.contains(8)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.add(3)).isFalse();
        assertThat(set.add(4)).isFalse();
        assertThat(set.add(7)).isFalse();
        assertThat(set.add(8)).isFalse();
    }
}