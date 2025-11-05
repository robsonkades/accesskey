package io.github.robsonkades.accesskey;

import io.github.robsonkades.cnpj.CNPJ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes para AccessKeyBuilder")
class AccessKeyBuilderTest {

    @Test
    @DisplayName("Deve construir corretamente uma AccessKey com todos os campos válidos")
    void shouldBuildAccessKeySuccessfully() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.of(2024, 10))
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        AccessKey accessKey = builder.build();

        assertNotNull(accessKey);
        assertEquals(State.SP, accessKey.getState());
        assertEquals(YearMonth.of(2024, 10), accessKey.getYearMonth());
        assertEquals(CNPJ.of("06546178000119"), accessKey.getCnpj());
        assertEquals(Model.NFE, accessKey.getModel());
        assertEquals(new Series(1), accessKey.getSeries());
        assertEquals(new Number(123), accessKey.getNumber());
        assertEquals(IssueMode.NORMAL, accessKey.getIssueMode());
        assertEquals(new Code(99999999), accessKey.getCode());
    }

    @Test
    @DisplayName("Deve construir corretamente uma AccessKey com CNPJ alfanumérico válido")
    void shouldBuildAccessKeySuccessfullyWithAlphanumericCnpj() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.of(2024, 10))
                .cnpj("KSP416L8000109")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        AccessKey accessKey = builder.build();

        assertNotNull(accessKey);
        assertEquals(State.SP, accessKey.getState());
        assertEquals(YearMonth.of(2024, 10), accessKey.getYearMonth());
        assertEquals(CNPJ.of("KSP416L8000109"), accessKey.getCnpj());
        assertEquals(Model.NFE, accessKey.getModel());
        assertEquals(new Series(1), accessKey.getSeries());
        assertEquals(new Number(123), accessKey.getNumber());
        assertEquals(IssueMode.NORMAL, accessKey.getIssueMode());
        assertEquals(new Code(99999999), accessKey.getCode());
    }

    @Test
    @DisplayName("Métodos fluentes devem retornar o próprio builder (padrão builder)")
    void shouldReturnSameBuilderInstance() {
        AccessKeyBuilder builder = new AccessKeyBuilder();

        assertSame(builder, builder.state(State.SP));
        assertSame(builder, builder.yearMonth(YearMonth.of(2024, 5)));
        assertSame(builder, builder.cnpj("06546178000119"));
        assertSame(builder, builder.model(Model.CTE));
        assertSame(builder, builder.series(10));
        assertSame(builder, builder.number(98765));
        assertSame(builder, builder.issueMode(IssueMode.FS_IA));
        assertSame(builder, builder.code(55555555));
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se tentar gerar build sem preencher state")
    void shouldThrowExceptionWhenStateIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se YearMonth for nulo")
    void shouldThrowExceptionWhenYearMonthIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se CNPJ for nulo")
    void shouldThrowExceptionWhenCnpjIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se Model for nulo")
    void shouldThrowExceptionWhenModelIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se Series for nulo")
    void shouldThrowExceptionWhenSeriesIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .model(Model.NFE)
                .number(123)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se Number for nulo")
    void shouldThrowExceptionWhenNumberIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .issueMode(IssueMode.NORMAL)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se IssueMode for nulo")
    void shouldThrowExceptionWhenIssueModeIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .code(99999999);

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se Code for nulo")
    void shouldThrowExceptionWhenCodeIsMissing() {
        AccessKeyBuilder builder = new AccessKeyBuilder()
                .state(State.SP)
                .yearMonth(YearMonth.now())
                .cnpj("06546178000119")
                .model(Model.NFE)
                .series(1)
                .number(123)
                .issueMode(IssueMode.NORMAL);

        assertThrows(NullPointerException.class, builder::build);
    }
}
