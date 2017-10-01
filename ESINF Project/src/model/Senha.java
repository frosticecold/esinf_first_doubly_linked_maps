package model;

public class Senha implements Comparable<Senha> {

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

    @Override
    public int compareTo(Senha o) {
        return this.n_ordem - o.n_ordem;
    }

    @Override
    public int hashCode() {
        return n_ordem + codigo;
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
        final Senha other = (Senha) obj;
        if (this.n_ordem != other.n_ordem) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

}
