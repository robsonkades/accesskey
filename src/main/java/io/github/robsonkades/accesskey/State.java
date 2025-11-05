package io.github.robsonkades.accesskey;

/**
 * Enum que representa as Unidades Federativas do Brasil utilizadas na
 * composição da Chave de Acesso de documentos fiscais eletrônicos (como NFe, CTe, MDF-e).
 *
 * <p>Cada estado possui:
 * <ul>
 *   <li><b>code</b>: Código numérico do IBGE utilizado na chave de acesso (dois dígitos);</li>
 *   <li><b>name</b>: Nome completo do estado.</li>
 * </ul>
 *
 * <p>Exemplo de uso:
 * <pre>{@code
 * State sp = State.fromCode(35);       // Retorna State.SP
 * State rj = State.fromCode("33");     // Retorna State.RJ
 * int codigo = sp.getCode();           // 35
 * String nome = sp.getName();          // "São Paulo"
 * }</pre>
 *
 * @author Robson Kades
 * @since 1.0.0
 */
public enum State {
    AC(12, "Acre"),
    AL(27, "Alagoas"),
    AP(16, "Amapá"),
    AM(13, "Amazonas"),
    BA(29, "Bahia"),
    CE(23, "Ceará"),
    DF(53, "Distrito Federal"),
    ES(32, "Espírito Santo"),
    GO(52, "Goiás"),
    MA(21, "Maranhão"),
    MT(51, "Mato Grosso"),
    MS(50, "Mato Grosso do Sul"),
    MG(31, "Minas Gerais"),
    PA(15, "Pará"),
    PB(25, "Paraíba"),
    PR(41, "Paraná"),
    PE(26, "Pernambuco"),
    PI(22, "Piauí"),
    RJ(33, "Rio de Janeiro"),
    RN(24, "Rio Grande do Norte"),
    RS(43, "Rio Grande do Sul"),
    RO(11, "Rondônia"),
    RR(14, "Roraima"),
    SC(42, "Santa Catarina"),
    SP(35, "São Paulo"),
    SE(28, "Sergipe"),
    TO(17, "Tocantins");

    private final int code;
    private final String name;

    /**
     * Cria uma instância de {@link State}.
     *
     * @param code código numérico de 2 dígitos do estado (conforme tabela IBGE)
     * @param name nome completo do estado
     */
    State(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Obtém o estado a partir do código numérico.
     *
     * @param codigo código do estado (entre 11 e 99)
     * @return instância correspondente de {@link State}
     * @throws AccessKeyException caso o código seja inválido
     */
    public static State fromCode(final int codigo) {
        for (State m : values()) {
            if (m.code == codigo) {
                return m;
            }
        }
        throw new AccessKeyException("Código do estado inválido: " + codigo);
    }

    /**
     * Obtém o estado a partir de uma string numérica.
     *
     * @param codigo código do estado em formato de {@link String}
     * @return instância correspondente de {@link State}
     * @throws AccessKeyException se não for possível converter para número
     */
    public static State fromCode(final String codigo) {
        try {
            return fromCode(Integer.parseInt(codigo));
        } catch (NumberFormatException e) {
            throw new AccessKeyException("Código do estado deve ser numérico: " + codigo);
        }
    }

    /**
     * @return código numérico do estado (tabela IBGE)
     */
    public int getCode() {
        return code;
    }

    /**
     * @return código numérico como {@link String}
     */
    public String getCodeAsString() {
        return String.valueOf(code);
    }

    /**
     * @return nome completo do estado
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "State{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
