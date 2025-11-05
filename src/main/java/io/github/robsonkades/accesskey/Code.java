package io.github.robsonkades.accesskey;

public class Code {

    private static final int MAX_VALUE = 99_999_999;
    private final int code;

    public Code(final int code) {
        validate(code);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getCodeAsString() {
        return String.format("%08d", code);
    }

    private void validate(final int code) {
        if (code < 0) {
            throw new AccessKeyException("Código não pode ser negativo");
        }
        if (code > MAX_VALUE) {
            throw new AccessKeyException("Código não pode exceder 8 dígitos (max: 99,999,999)");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code other = (Code) o;
        return code == other.code;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(code);
    }

    @Override
    public String toString() {
        return "Code{" +
                "code=" + code +
                '}';
    }
}
