package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CodeTest {

    @Test
    @DisplayName("Deve criar Code com valor válido")
    void testValidCodeCreation() {
        Code code = new Code(12345678);
        assertEquals(12345678, code.getCode());
    }

    @Test
    @DisplayName("Deve formatar o código com 8 dígitos e zeros à esquerda")
    void testGetCodeAsString() {
        Code code = new Code(123);
        assertEquals("00000123", code.getCodeAsString());
    }

    @Test
    @DisplayName("Deve lançar exceção se código for negativo")
    void testNegativeCode() {
        AccessKeyException exception = assertThrows(AccessKeyException.class, () -> new Code(-1));
        assertEquals("Código não pode ser negativo", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se código exceder 8 dígitos")
    void testCodeExceedsMax() {
        AccessKeyException exception = assertThrows(AccessKeyException.class, () -> new Code(100_000_000));
        assertEquals("Código não pode exceder 8 dígitos (max: 99,999,999)", exception.getMessage());
    }

    @Test
    @DisplayName("equals() deve retornar true para objetos com mesmo valor")
    void testEqualsSameValue() {
        Code code1 = new Code(12345678);
        Code code2 = new Code(12345678);
        assertEquals(code1, code2);
    }

    @Test
    @DisplayName("equals() deve retornar false para objetos com valores diferentes")
    void testEqualsDifferentValue() {
        Code code1 = new Code(12345678);
        Code code2 = new Code(87654321);
        assertNotEquals(code1, code2);
    }

    @Test
    @DisplayName("hashCode() deve ser igual para objetos equivalentes")
    void testHashCode() {
        Code code1 = new Code(98765432);
        Code code2 = new Code(98765432);
        assertEquals(code1.hashCode(), code2.hashCode());
    }

    @Test
    @DisplayName("toString() deve exibir corretamente o valor de code")
    void testToString() {
        Code code = new Code(123);
        assertEquals("Code{code=123}", code.toString());
    }
}
