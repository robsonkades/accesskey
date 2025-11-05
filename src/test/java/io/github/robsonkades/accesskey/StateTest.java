package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes da Enum State")
class StateTest {

    @Test
    @DisplayName("Deve retornar o estado correto ao buscar por código numérico válido")
    void testFromCodeValid() {
        assertEquals(State.SP, State.fromCode(35));
        assertEquals(State.RJ, State.fromCode(33));
        assertEquals(State.AC, State.fromCode(12));
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar estado com código numérico inválido")
    void testFromCodeInvalid() {
        AccessKeyException exception =
                assertThrows(AccessKeyException.class, () -> State.fromCode(999));
        assertEquals("Código do estado inválido: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar o estado correto ao buscar por código String numérico válido")
    void testFromCodeStringValid() {
        assertEquals(State.MG, State.fromCode("31"));
        assertEquals(State.RS, State.fromCode("43"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar estado com código String não numérico")
    void testFromCodeStringInvalidNonNumeric() {
        AccessKeyException exception =
                assertThrows(AccessKeyException.class, () -> State.fromCode("ABC"));
        assertEquals("Código do estado deve ser numérico: ABC", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar estado com código String numérico inexistente")
    void testFromCodeStringInvalidNumericNotMapped() {
        AccessKeyException exception =
                assertThrows(AccessKeyException.class, () -> State.fromCode("999"));
        assertEquals("Código do estado inválido: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar corretamente os valores de código, nome e código como String")
    void testGetCodeAndName() {
        State state = State.SC;
        assertEquals(42, state.getCode());
        assertEquals("42", state.getCodeAsString());
        assertEquals("Santa Catarina", state.getName());
    }

    @Test
    @DisplayName("Deve formatar corretamente o método toString()")
    void testToString() {
        State state = State.PR;
        String expected = "State{code=41, name='Paraná'}";
        assertEquals(expected, state.toString());
    }

    @Test
    @DisplayName("Todos os valores do enum devem possuir código, nome e toString() válido")
    void testAllEnumValuesNotNull() {
        for (State state : State.values()) {
            assertNotNull(state.getCode());
            assertNotNull(state.getName());
            assertNotNull(state.getCodeAsString());
            assertNotNull(state.toString());
        }
    }
}