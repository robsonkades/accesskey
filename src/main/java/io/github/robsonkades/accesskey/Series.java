package io.github.robsonkades.accesskey;

/**
 * Representa a série do documento fiscal eletrônico (como NF-e, NFC-e, CT-e).
 *
 * <p>A série é um número inteiro entre 0 e 999, utilizado para diferenciar sequências
 * de numeração de notas fiscais emitidas por um mesmo estabelecimento.</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 * Series serie = new Series(1);
 * String valorFormatado = serie.getCodeAsString(); // "001"
 * }</pre>
 *
 * <p>Restrições:</p>
 * <ul>
 *   <li>Não pode ser negativa;</li>
 *   <li>Não pode ser superior a 999 (3 dígitos);</li>
 *   <li>Formatada sempre com 3 dígitos via {@link #getCodeAsString()}.</li>
 * </ul>
 *
 * @author Robson Kades
 * @since 1.0.0
 */
public class Series {

    private final int code;

    /**
     * Cria uma instância de {@link Series}.
     *
     * @param code valor numérico da série (0 a 999)
     * @throws AccessKeyException se a série for negativa ou possuir mais de 3 dígitos
     */
    public Series(final int code) {
        validate(code);
        this.code = code;
    }

    /**
     * Retorna o valor numérico da série.
     *
     * @return código da série
     */
    public int getCode() {
        return code;
    }

    /**
     * Retorna a série formatada com exatamente 3 dígitos, preenchendo com zeros à esquerda.
     *
     * @return código da série formatado (ex: 1 → "001")
     */
    public String getCodeAsString() {
        return String.format("%03d", code);
    }

    /**
     * Valida os limites da série, garantindo que esteja entre 0 e 999.
     *
     * @param code valor a ser validado
     * @throws AccessKeyException se for negativo ou maior que 999
     */
    private void validate(final int code) {
        if (code < 0) {
            throw new AccessKeyException("A série não pode ser negativa.");
        }
        if (code > 999) {
            throw new AccessKeyException("A série não pode ter mais de 3 dígitos.");
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        Series series = (Series) object;
        return getCode() == series.getCode();
    }

    @Override
    public int hashCode() {
        return getCode();
    }

    @Override
    public String toString() {
        return "Series{" +
                "code=" + code +
                '}';
    }
}
