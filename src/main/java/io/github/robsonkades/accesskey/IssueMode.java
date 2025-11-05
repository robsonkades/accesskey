package io.github.robsonkades.accesskey;

public enum IssueMode {
    NORMAL(1, "Emissão normal (não em contingência)"),
    FS_IA(2, "Contingência FS-IA"),
    SCAN(3, "Contingência SCAN"),
    DPEC(4, "Contingência DPEC"),
    FS_DA(5, "Contingência FS-DA"),
    SVC_AN(6, "Contingência SVC-AN"),
    SVC_RS(7, "Contingência SVC-RS");

    private final int code;
    private final String description;

    IssueMode(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static IssueMode fromCode(final int code) {
        for (IssueMode t : values()) {
            if (t.code == code) {
                return t;
            }
        }
        throw new AccessKeyException("Tipo de emissão inválido: " + code);
    }

    public static IssueMode fromCode(final String code) {
        try {
            return fromCode(Integer.parseInt(code));
        } catch (NumberFormatException e) {
            throw new AccessKeyException("Tipo de emissão deve ser numérico: " + code);
        }
    }

    public int getCode() {
        return code;
    }

    public String getCodeAsString() {
        return String.valueOf(code);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "IssueMode{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
