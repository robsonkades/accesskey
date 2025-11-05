package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes do enum Model")
class ModelTest {

    @Test
    @DisplayName("fromCode(int) deve retornar o modelo correto para código válido")
    void testFromCodeIntValid() {
        assertEquals(Model.NFE, Model.fromCode(55));
        assertEquals(Model.NFCE, Model.fromCode(65));
        assertEquals(Model.CTE, Model.fromCode(57));
        assertEquals(Model.MDFE, Model.fromCode(58));
    }

    @Test
    @DisplayName("fromCode(int) deve lançar exceção para código inválido")
    void testFromCodeIntInvalid() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> Model.fromCode(999)
        );
        assertEquals("Modelo inválido: 999", exception.getMessage());
    }

    @Test
    @DisplayName("fromCode(String) deve retornar o modelo correto para valor numérico válido")
    void testFromCodeStringValid() {
        assertEquals(Model.NFE, Model.fromCode("55"));
        assertEquals(Model.NFCE, Model.fromCode("65"));
    }

    @Test
    @DisplayName("fromCode(String) deve lançar exceção ao receber valor não numérico")
    void testFromCodeStringNonNumeric() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> Model.fromCode("abc")
        );
        assertEquals("Modelo deve ser numérico: abc", exception.getMessage());
    }

    @Test
    @DisplayName("fromCode(String) deve lançar exceção ao receber código numérico inexistente")
    void testFromCodeStringInvalidNumeric() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> Model.fromCode("999")
        );
        assertEquals("Modelo inválido: 999", exception.getMessage());
    }

    @Test
    @DisplayName("getCode() e getCodeAsString() devem retornar o valor correto")
    void testGetCodeAndCodeAsString() {
        assertEquals(55, Model.NFE.getCode());
        assertEquals("55", Model.NFE.getCodeAsString());
    }

    @Test
    @DisplayName("getDescription() deve retornar a descrição correta do modelo")
    void testGetDescription() {
        assertEquals("Nota Fiscal Eletrônica", Model.NFE.getDescription());
        assertEquals("Nota Fiscal de Consumidor Eletrônica", Model.NFCE.getDescription());
    }

    @Test
    @DisplayName("toString() deve retornar string formatada corretamente")
    void testToString() {
        String expected = "Model{code=55, description='Nota Fiscal Eletrônica'}";
        assertEquals(expected, Model.NFE.toString());
    }

    @Test
    @DisplayName("Todos os valores do enum devem possuir código, descrição e toString() não nulos")
    void testAllEnumValues() {
        for (Model model : Model.values()) {
            assertNotNull(model.getCode());
            assertNotNull(model.getCodeAsString());
            assertNotNull(model.getDescription());
            assertNotNull(model.toString());
        }
    }
}
