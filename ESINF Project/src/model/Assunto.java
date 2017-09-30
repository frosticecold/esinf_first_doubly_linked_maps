package model;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public enum Assunto {
    /**
     * Valores possíveis de assuntoe
     */
    IRS_IRC('A'), INICIO_ATIVIDADE('B'), ALTERACOES('C'),
    TESOURARIA('D'), OPCAO_E('E'), OPCAO_F('F');

    private final char tipoAssunto;

    Assunto(char assunto) {
        tipoAssunto = assunto;
    }

    public char getTipoAssunto() {
        return tipoAssunto;
    }

    public static boolean isAssunto(String str) {
        if (str.length() > 1) {
            return false;
        }
        char a = str.charAt(0);
        for (Assunto as : Assunto.values()) {
            if (as.getTipoAssunto() == a) {
                return true;
            }
        }
        return false;
    }

    public static Assunto converterStringParaAssunto(String sr) {
        char a = sr.charAt(0);
        for (Assunto as : Assunto.values()) {
            if (as.getTipoAssunto() == a) {
                return as;
            }
        }
        return null;
    }

}
