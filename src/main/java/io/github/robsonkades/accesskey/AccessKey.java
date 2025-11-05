package io.github.robsonkades.accesskey;

import io.github.robsonkades.cnpj.CNPJ;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AccessKey {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMM");

    private final State state;
    private final YearMonth yearMonth;
    private final CNPJ cnpj;
    private final Model model;
    private final Series series;
    private final Number number;
    private final IssueMode issueMode;
    private final Code code;

    AccessKey(final State state,
              final YearMonth yearMonth,
              final CNPJ cnpj,
              final Model model,
              final Series series,
              final Number number,
              final IssueMode issueMode,
              final Code code) {

        this.state = Objects.requireNonNull(state, "O código do estado (UF) é obrigatório.");
        this.yearMonth = Objects.requireNonNull(yearMonth, "O ano e mês de emissão são obrigatórios.");
        this.cnpj = Objects.requireNonNull(cnpj, "O CNPJ do emitente é obrigatório.");
        this.model = Objects.requireNonNull(model, "O modelo é obrigatório.");
        this.series = Objects.requireNonNull(series, "A série é obrigatória.");
        this.number = Objects.requireNonNull(number, "O número da nota é obrigatório.");
        this.issueMode = Objects.requireNonNull(issueMode, "O tipo de emissão é obrigatório.");
        this.code = Objects.requireNonNull(code, "O código numérico é obrigatório.");
    }

    public static AccessKey from(final String key) {
        if (key == null || !key.matches("[A-Z0-9]{44}")) {
            throw new AccessKeyException("A chave de acesso deve conter exatamente 44 caracteres alfanuméricos.");
        }

        State state = State.fromCode(key.substring(0, 2));
        YearMonth yearMonth = YearMonth.parse(key.substring(2, 6), DATE_TIME_FORMATTER);
        CNPJ cnpj = CNPJ.of(key.substring(6, 20));
        Model model = Model.fromCode(key.substring(20, 22));
        Series series = new Series(Integer.parseInt(key.substring(22, 25)));
        Number number = new Number(Integer.parseInt(key.substring(25, 34)));
        IssueMode issueMode = IssueMode.fromCode(key.substring(34, 35));
        Code code = new Code(Integer.parseInt(key.substring(35, 43)));
        int dv = Character.digit(key.charAt(43), 10);

        AccessKey parsed = new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, code);
        int calculatedDv = AccessKeyGenerator.generate(parsed).charAt(43) - '0';

        if (dv != calculatedDv) {
            throw new AccessKeyException(
                    String.format("Chave de acesso inválida. Esperado DV=%d mas foi encontrado %d.", calculatedDv, dv)
            );
        }
        return parsed;
    }


    public String generate() {
        return AccessKeyGenerator.generate(this);
    }

    public State getState() {
        return state;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public Model getModel() {
        return model;
    }

    public Series getSeries() {
        return series;
    }

    public Number getNumber() {
        return number;
    }

    public IssueMode getIssueMode() {
        return issueMode;
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AccessKey{" +
                "state=" + state +
                ", yearMonth=" + yearMonth +
                ", cnpj=" + cnpj +
                ", model=" + model +
                ", series=" + series +
                ", number=" + number +
                ", issueMode=" + issueMode +
                ", code=" + code +
                '}';
    }
}

