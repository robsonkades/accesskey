package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes da classe Series")
class SeriesTest {

    @Test
    @DisplayName("Deve criar Series válida com código dentro do limite permitido")
    void testValidSeries() {
        Series series = new Series(123);
        assertEquals(123, series.getCode());
        assertEquals("123", series.getCodeAsString());
    }

    @Test
    @DisplayName("Deve preencher zeros à esquerda corretamente no getCodeAsString()")
    void testCodeAsStringPadding() {
        Series series = new Series(5);
        assertEquals("005", series.getCodeAsString());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar Series com código negativo")
    void testNegativeSeriesThrowsException() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> new Series(-1)
        );
        assertEquals("A série não pode ser negativa.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar Series com mais de 3 dígitos")
    void testSeriesGreaterThanThreeDigitsThrowsException() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> new Series(1000)
        );
        assertEquals("A série não pode ter mais de 3 dígitos.", exception.getMessage());
    }

    @Test
    @DisplayName("equals() deve retornar true para objetos Series com o mesmo código")
    void testEqualsTrue() {
        Series series1 = new Series(10);
        Series series2 = new Series(10);
        assertEquals(series1, series2);
    }

    @Test
    @DisplayName("equals() deve retornar false para códigos diferentes")
    void testEqualsFalse() {
        Series series1 = new Series(10);
        Series series2 = new Series(20);
        assertNotEquals(series1, series2);
    }

    @Test
    @DisplayName("equals() deve retornar false ao comparar com null")
    void testEqualsWithNull() {
        Series series = new Series(10);
        assertNotEquals(null, series);
    }

    @Test
    @DisplayName("equals() deve retornar false ao comparar com objeto de classe diferente")
    void testEqualsWithDifferentClass() {
        Series series = new Series(10);
        assertNotEquals(series, "Not a Series");
    }

    @Test
    @DisplayName("hashCode() deve ser consistente com equals()")
    void testHashCode() {
        Series series1 = new Series(50);
        Series series2 = new Series(50);
        assertEquals(series1.hashCode(), series2.hashCode());
    }

    @Test
    @DisplayName("toString() deve retornar a string formatada corretamente")
    void testToString() {
        Series series = new Series(123);
        assertEquals("Series{code=123}", series.toString());
    }
}