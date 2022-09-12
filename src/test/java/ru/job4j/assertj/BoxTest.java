package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenNumberOfVerticesIs4() {
        Box box = new Box(4, 6);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(4);
    }

    @Test
    void whenNumberOfVerticesIs8() {
        Box box = new Box(8, 64);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(8);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(4, 0);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenGetAreaOfCube() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(600)
                .isNotNegative()
                .isGreaterThan(500);
    }

    @Test
    void whenGetAreaOfTetrahedron() {
        Box box = new Box(4, 16);
        double area = box.getArea();
        assertThat(area).isEqualTo(443.40500673763256)
                .isLessThan(500)
                .isGreaterThan(100);
    }
}