package io.github.robsonkades.accesskey;

/**
 * Representa o número do documento fiscal (NF-e, NFC-e, CT-e, etc.).
 *
 * <p>O número é um identificador sequencial atribuído ao documento fiscal emitido.
 * Ele deve ser um valor inteiro entre 0 e 999.999.999 (máximo de 9 dígitos).</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 * Number numero = new Number(123);
 * String formatado = numero.getCodeAsString(); // "000000123"
 * }</pre>
 *
 * <p>Regras de validação:</p>
 * <ul>
 *   <li>Não pode ser negativo;</li>
 *   <li>Não pode exceder 9 dígitos (valor máximo: 999.999.999);</li>
 *   <li>É sempre retornado com 9 dígitos no método {@link #getCodeAsString()}, com zeros à esquerda.</li>
 * </ul>
 *
 * @author Robson Kades
 * @since 1.0.0
 */
public final class Number {

    /**
     * Valor máximo permitido (9 dígitos).
     */
    private static final int MAX_VALUE = 999_999_999;

    private final int code;

    /**
     * Cria uma instância de {@link Number}.
     *
     * @param code valor numérico do número fiscal
     * @throws AccessKeyException se for negativo ou maior que 999.999.999
     */
    public Number(final int code) {
        validate(code);
        this.code = code;
    }

    /**
     * Valida o valor informado para o número fiscal.
     *
     * @param code valor a ser validado
     * @throws AccessKeyException se o valor for negativo ou maior que {@link #MAX_VALUE}
     */
    private void validate(final int code) {
        if (code < 0) {
            throw new AccessKeyException("O número não pode ser negativo.");
        }
        if (code > MAX_VALUE) {
            throw new AccessKeyException("O número não pode exceder 9 dígitos (max: 999,999,999)");
        }
    }

    /**
     * Retorna o valor numérico do número fiscal.
     *
     * @return código numérico
     */
    public int getCode() {
        return code;
    }

    /**
     * Retorna o número formatado com 9 dígitos, preenchendo com zeros à esquerda.
     *
     * @return número formatado (exemplo: 123 → "000000123")
     */
    public String getCodeAsString() {
        return String.format("%09d", code);
    }

    @Override
    public String toString() {
        return "Number{" +
                "code=" + code +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number other = (Number) o;
        return code == other.code;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(code);
    }
}
