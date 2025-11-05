package io.github.robsonkades.accesskey;

public final class AccessKeyGenerator {

    private AccessKeyGenerator() {
        // empty constructor
    }

    public static String generate(final AccessKey key) {
        String base = key.getState().getCodeAsString() +
                key.getYearMonth().format(AccessKey.DATE_TIME_FORMATTER) +
                key.getCnpj().getValue() +
                key.getModel().getCodeAsString() +
                key.getSeries().getCodeAsString() +
                key.getNumber().getCodeAsString() +
                key.getIssueMode().getCodeAsString() +
                key.getCode().getCodeAsString();

        int dv = calcModulo112(base);
        return base + dv;
    }

    private static int calcModulo112(final String base) {
        int sum = 0;
        int weight = 2;

        for (int i = base.length() - 1; i >= 0; i--) {
            int digit = base.charAt(i) - '0';
            sum += digit * weight;
            weight = (weight == 9) ? 2 : weight + 1;
        }

        int mod = sum % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }
}
