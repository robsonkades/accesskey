package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes da classe Number")
class NumberTest {

    @Test
    @DisplayName("Deve criar Number válido quando código está dentro do limite permitido")
    void testValidNumber() {
        Number number = new Number(123456789);
        assertEquals(123456789, number.getCode());
        assertEquals("123456789", number.getCodeAsString());
    }

    @Test
    @DisplayName("Deve formatar com zeros à esquerda corretamente no getCodeAsString()")
    void testCodeAsStringPadding() {
        Number number = new Number(123);
        assertEquals("000000123", number.getCodeAsString());
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar Number com valor negativo")
    void testNegativeNumberThrowsException() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> new Number(-1)
        );
        assertEquals("O número não pode ser negativo.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar Number com valor maior que 9 dígitos")
    void testNumberExceedsMaxValueThrowsException() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> new Number(1_000_000_000)
        );
        assertEquals("O número não pode exceder 9 dígitos (max: 999,999,999)", exception.getMessage());
    }

    @Test
    @DisplayName("equals() deve retornar true para objetos Number com o mesmo código")
    void testEqualsTrue() {
        Number n1 = new Number(999);
        Number n2 = new Number(999);
        assertEquals(n1, n2);
    }

    @Test
    @DisplayName("equals() deve retornar false para códigos diferentes")
    void testEqualsFalse() {
        Number n1 = new Number(10);
        Number n2 = new Number(20);
        assertNotEquals(n1, n2);
    }

    @Test
    @DisplayName("equals() deve retornar false ao comparar com null")
    void testEqualsNull() {
        Number number = new Number(10);
        assertNotEquals(null, number);
    }

    @Test
    @DisplayName("equals() deve retornar false ao comparar com objeto de classe diferente")
    void testEqualsDifferentClass() {
        Number number = new Number(10);
        assertNotEquals(number, "Not a Number");
    }

    @Test
    @DisplayName("hashCode() deve ser consistente com equals()")
    void testHashCodeConsistency() {
        Number n1 = new Number(777);
        Number n2 = new Number(777);
        assertEquals(n1.hashCode(), n2.hashCode());
    }

    @Test
    @DisplayName("toString() deve retornar a string formatada corretamente")
    void testToString() {
        Number number = new Number(123);
        assertEquals("Number{code=123}", number.toString());
    }
}
