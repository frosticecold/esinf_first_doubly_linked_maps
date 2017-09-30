package model;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class CodigoPostal {

    /**
     * Prefixo, primeiros 4 números de um código postal
     */
    private int prefixo;
    /**
     * Sufixo, últimos 3 números de um código postal
     */
    private int sufixo;

    /**
     * Valor por omissão caso não tenha sufixo
     */
    private static int SEM_NUM = 0;

    /**
     * Construtor código postal
     *
     * @param prefixo Recebe o prefixo de um código postal
     * @param sufixo Recebe o sufixo de um código postal
     */
    public CodigoPostal(int prefixo, int sufixo) {
        setPrefixo(prefixo);
        setSufixo(sufixo);
    }

    /**
     * Construtor código postal que recebe apenas o prefixo
     *
     * @param prefixo Recebe o prefixo de um código postal
     */
    public CodigoPostal(int prefixo) {
        setPrefixo(prefixo);
        setSufixo(SEM_NUM);
    }

    /**
     * Construtor vazio de um código postal
     */
    public CodigoPostal() {
        setPrefixo(SEM_NUM);
        setSufixo(SEM_NUM);
    }

    /**
     *
     * @return Retorna o Prefixo de um CódigoPostal
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     *
     * @return Retorna o Sufixo de um CódigoPostal
     */
    public int getSufixo() {
        return sufixo;
    }

    /**
     * Altera o prefixo de um código postal se tiver um número entre 4 dígitos
     *
     * @param prefixo prefixo a alterar
     */
    private void setPrefixo(int prefixo) {
        if (prefixo > 0 && prefixo < 10000) {
            this.prefixo = prefixo;
        } else {
            throw new IllegalArgumentException("Prefixo inválido");
        }
    }

    /**
     * Altera o valor do sufixo de um código postal se tiver um número entre 3
     * dígitios
     *
     * @param sufixo sufixo a alterar
     */
    private void setSufixo(int sufixo) {
        if (sufixo >= 0 && sufixo < 1000) {
            this.sufixo = sufixo;
        } else {
            throw new IllegalArgumentException("Sufixo inválido");
        }
    }

    @Override
    public int hashCode() {
        return prefixo + sufixo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CodigoPostal other = (CodigoPostal) obj;
        if (this.prefixo != other.prefixo) {
            return false;
        }
        if (this.sufixo != other.sufixo) {
            return false;
        }
        return true;
    }

}
