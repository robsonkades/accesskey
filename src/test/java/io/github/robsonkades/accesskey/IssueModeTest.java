package io.github.robsonkades.accesskey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssueModeTest {

    @Test
    @DisplayName("Deve retornar IssueMode correto a partir de código numérico válido")
    void testFromCodeInt() {
        assertEquals(IssueMode.NORMAL, IssueMode.fromCode(1));
        assertEquals(IssueMode.SVC_RS, IssueMode.fromCode(7));
    }

    @Test
    @DisplayName("Deve lançar exceção para código numérico inválido")
    void testFromCodeIntInvalid() {
        assertThrows(AccessKeyException.class, () -> IssueMode.fromCode(99));
    }

    @Test
    @DisplayName("Deve retornar IssueMode correto a partir do código String")
    void testFromCodeString() {
        assertEquals(IssueMode.FS_IA, IssueMode.fromCode("2"));
    }

    @Test
    @DisplayName("Deve lançar exceção se código String não for numérico")
    void testFromCodeStringInvalid() {
        assertThrows(AccessKeyException.class, () -> IssueMode.fromCode("ABC"));
    }

    @Test
    @DisplayName("Deve retornar código formatado com dois dígitos")
    void testGetCodeAsString() {
        assertEquals("1", IssueMode.NORMAL.getCodeAsString());
        assertEquals(1, IssueMode.NORMAL.getCode());
        assertEquals("Emissão normal (não em contingência)", IssueMode.NORMAL.getDescription());
        assertEquals("7", IssueMode.SVC_RS.getCodeAsString());
    }

    @Test
    @DisplayName("toString() deve retornar a string formatada corretamente")
    void testToString() {
        IssueMode issueMode = IssueMode.NORMAL;
        assertEquals("IssueMode{code=1, description='Emissão normal (não em contingência)'}", issueMode.toString());
    }
}
