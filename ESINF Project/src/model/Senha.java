package model;

public class Senha {

    /**
     * Número da senha
     */
    private final int n_ordem;
    /**
     * Código da senha
     */
    private final char codigo;

    /**
     * Construtor de uma senha
     *
     * @param n_ordem Número da senham
     * @param codigo Código da Senha
     */
    public Senha(int n_ordem, char codigo) {
        this.n_ordem = n_ordem;
        this.codigo = codigo;
    }

    /**
     *
     * @return Retorna o número da Senha
     */
    public int getNumSenha() {
        return n_ordem;
    }

    /**
     *
     * @return Retorna o código da Senha
     */
    public char getCodigoSenha() {
        return codigo;
    }

}
