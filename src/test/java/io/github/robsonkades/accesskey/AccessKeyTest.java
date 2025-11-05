package io.github.robsonkades.accesskey;

import io.github.robsonkades.cnpj.CNPJ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes para AccessKey")
class AccessKeyTest {

    private final State state = State.SP;
    private final YearMonth yearMonth = YearMonth.of(2024, 10);
    private final CNPJ cnpj = CNPJ.of("06546178000119");
    private final Model model = Model.NFE;
    private final Series series = new Series(1);
    private final Number number = new Number(123);
    private final IssueMode issueMode = IssueMode.NORMAL;
    private final Code code = new Code(99999999);

    @Test
    @DisplayName("Deve criar AccessKey válida com todos os parâmetros corretos")
    void shouldCreateValidAccessKey() {
        AccessKey accessKey = new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, code);
        assertNotNull(accessKey);
        assertEquals(state, accessKey.getState());
        assertEquals(yearMonth, accessKey.getYearMonth());
        assertEquals(cnpj, accessKey.getCnpj());
        assertEquals(model, accessKey.getModel());
        assertEquals(series, accessKey.getSeries());
        assertEquals(number, accessKey.getNumber());
        assertEquals(issueMode, accessKey.getIssueMode());
        assertEquals(code, accessKey.getCode());
    }

    @Test
    @DisplayName("Deve criar AccessKey válida com todos os parâmetros corretos e CNPJ alfanumérico válido")
    void shouldCreateValidAccessKeyWithAlphanumericCnpj() {
        AccessKey accessKey = AccessKey.from("352410KSP416L8000109550010000001231999999993");
        assertNotNull(accessKey);
        assertEquals(state, accessKey.getState());
        assertEquals(yearMonth, accessKey.getYearMonth());
        assertEquals("KSP416L8000109", accessKey.getCnpj().getValue());
        assertEquals(model, accessKey.getModel());
        assertEquals(series, accessKey.getSeries());
        assertEquals(number, accessKey.getNumber());
        assertEquals(issueMode, accessKey.getIssueMode());
        assertEquals(code, accessKey.getCode());
    }

    @Test
    @DisplayName("Deve lançar exceção se a chave for nula")
    void shouldThrowExceptionWhenKeyIsNull() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> AccessKey.from(null)
        );
        assertEquals("A chave de acesso deve conter exatamente 44 caracteres alfanuméricos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se a chave tiver tamanho diferente de 44 caracteres")
    void shouldThrowExceptionWhenKeyHasInvalidLength() {
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> AccessKey.from("123")
        );
        assertEquals("A chave de acesso deve conter exatamente 44 caracteres alfanuméricos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se a chave contiver caracteres inválidos")
    void shouldThrowExceptionWhenKeyContainsInvalidCharacters() {
        String invalidKey = "ABCDEFGHIJKLMNOPQRSTUVWX1234567890@#"; // Contém '@' e '#'
        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> AccessKey.from(invalidKey)
        );
        assertEquals("A chave de acesso deve conter exatamente 44 caracteres alfanuméricos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se o DV calculado for diferente do informado")
    void shouldThrowExceptionWhenDVIsInvalid() {
        String validKey = "35101212345678000195550010000012341000000001"; // 44 caracteres
        String invalidDVKey = validKey.substring(0, 43) + "9"; // altera o DV

        AccessKeyException exception = assertThrows(
                AccessKeyException.class,
                () -> AccessKey.from(invalidDVKey)
        );

        assertTrue(exception.getMessage().contains("Chave de acesso inválida"));
    }

    @Test
    @DisplayName("Método generate() deve retornar chave válida")
    void shouldGenerateValidAccessKey() {
        AccessKey accessKey = new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, code);
        String generated = accessKey.generate();
        assertNotNull(generated);
        assertEquals(44, generated.length());
    }

    @Test
    @DisplayName("Método toString() deve conter todas as informações da chave")
    void testToString() {
        AccessKey accessKey = new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, code);
        String text = accessKey.toString();

        assertTrue(text.contains("state=State{code=35, name='São Paulo'}"));
        assertTrue(text.contains("yearMonth=2024-10"));
        assertTrue(text.contains("cnpj=CNPJ{value='06546178000119'}"));
        assertTrue(text.contains("model=Model{code=55, description='Nota Fiscal Eletrônica'}"));
    }

    @Test
    @DisplayName("Construtor deve lançar NullPointerException se algum parâmetro for nulo")
    void shouldThrowExceptionWhenConstructorArgumentsAreNull() {
        assertThrows(NullPointerException.class, () -> new AccessKey(null, yearMonth, cnpj, model, series, number, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, null, cnpj, model, series, number, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, null, model, series, number, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, cnpj, null, series, number, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, cnpj, model, null, number, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, cnpj, model, series, null, issueMode, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, cnpj, model, series, number, null, code));
        assertThrows(NullPointerException.class, () -> new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, null));
    }
}
