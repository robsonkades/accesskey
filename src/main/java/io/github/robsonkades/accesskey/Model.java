package io.github.robsonkades.accesskey;

/**
 * Enum que representa os diferentes modelos de documentos fiscais eletrônicos utilizados no Brasil.
 *
 * <p>Cada modelo possui um código numérico definido pela SEFAZ e uma descrição correspondente.
 * Exemplos de modelos incluem:</p>
 *
 * <ul>
 *   <li>{@link #NFE} - Nota Fiscal Eletrônica (código 55)</li>
 *   <li>{@link #NFCE} - Nota Fiscal de Consumidor Eletrônica (código 65)</li>
 *   <li>{@link #CTE} - Conhecimento de Transporte Eletrônico (código 57)</li>
 *   <li>{@link #MDFE} - Manifesto Eletrônico de Documentos Fiscais (código 58)</li>
 * </ul>
 *
 * <p>Esta enumeração oferece métodos utilitários para conversão a partir de
 * um código numérico ou {@link String} ({@link #fromCode(int)} e {@link #fromCode(String)}).</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 * Model model = Model.fromCode(55); // NFE
 * String codigo = model.getCodeAsString(); // "55"
 * }</pre>
 *
 * @author Robson Kades
 * @since 1.0.0
 */
public enum Model {
    NFE(55, "Nota Fiscal Eletrônica"),
    NFCE(65, "Nota Fiscal de Consumidor Eletrônica"),
    CTE(57, "Conhecimento de Transporte Eletrônico"),
    MDFE(58, "Manifesto Eletrônico de Documentos Fiscais");

    private final int code;
    private final String description;

    /**
     * Construtor interno para associar código e descrição ao modelo fiscal.
     *
     * @param code        código numérico do modelo (ex: 55 para NFe)
     * @param description descrição legível do modelo
     */
    Model(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Obtém o modelo a partir do seu código numérico.
     *
     * @param value código do modelo
     * @return modelo correspondente
     * @throws AccessKeyException se o código não corresponder a nenhum modelo válido
     */
    public static Model fromCode(final int value) {
        for (Model m : values()) {
            if (m.code == value) {
                return m;
            }
        }
        throw new AccessKeyException("Modelo inválido: " + value);
    }

    /**
     * Obtém o modelo a partir de uma {@link String} que representa um número.
     *
     * @param code string com o valor do código do modelo (ex: "55")
     * @return modelo correspondente
     * @throws AccessKeyException se a {@link String} não for numérica ou o valor for inválido
     */
    public static Model fromCode(final String code) {
        try {
            return fromCode(Integer.parseInt(code));
        } catch (NumberFormatException e) {
            throw new AccessKeyException("Modelo deve ser numérico: " + code);
        }
    }

    /**
     * Retorna o código numérico deste modelo fiscal.
     *
     * @return código do modelo
     */
    public int getCode() {
        return code;
    }

    /**
     * Retorna o código numérico deste modelo como {@link String}.
     *
     * @return código em formato de string
     */
    public String getCodeAsString() {
        return String.valueOf(code);
    }

    /**
     * Retorna a descrição textual do modelo fiscal.
     *
     * @return descrição do modelo
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Model{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
