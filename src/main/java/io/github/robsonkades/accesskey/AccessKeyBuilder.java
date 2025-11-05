package io.github.robsonkades.accesskey;

import io.github.robsonkades.cnpj.CNPJ;

import java.time.YearMonth;

public final class AccessKeyBuilder {

    private State state;
    private YearMonth yearMonth;
    private CNPJ cnpj;
    private Model model;
    private Series series;
    private Number number;
    private IssueMode issueMode;
    private Code code;

    public AccessKeyBuilder state(final State state) {
        this.state = state;
        return this;
    }

    public AccessKeyBuilder yearMonth(final YearMonth ym) {
        this.yearMonth = ym;
        return this;
    }

    public AccessKeyBuilder cnpj(final String value) {
        this.cnpj = CNPJ.of(value);
        return this;
    }

    public AccessKeyBuilder model(final Model model) {
        this.model = model;
        return this;
    }

    public AccessKeyBuilder series(final int value) {
        this.series = new Series(value);
        return this;
    }

    public AccessKeyBuilder number(final int value) {
        this.number = new Number(value);
        return this;
    }

    public AccessKeyBuilder issueMode(final IssueMode mode) {
        this.issueMode = mode;
        return this;
    }

    public AccessKeyBuilder code(final int value) {
        this.code = new Code(value);
        return this;
    }

    public AccessKey build() {
        return new AccessKey(state, yearMonth, cnpj, model, series, number, issueMode, code);
    }
}

