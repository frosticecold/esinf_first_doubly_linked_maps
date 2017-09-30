package model;

/**
 *
 * @author Raúl Correia
 */
public class Cidadao implements Comparable<Cidadao> {

    /**
     * Nome de um cidadao
     */
    private String nome;
    /**
     * Email de um cidadao
     */
    private String email;
    /**
     * Código Postal de um cidadão
     */
    private CodigoPostal cod_postal;
    /**
     * Número de contribuinte de um cidadão
     */
    private final int n_contribuinte;
    /**
     * Número de repartição associada a um cidadão
     */
    private final int numero_reparticao;

    /**
     * Construtor de um cidadão
     *
     * @param nome Nome do cidadao
     * @param n_contribuinte Número contribuinte de um cidadão
     * @param email Email de um cidadão
     * @param cod_postal Código Postal de um cidadão
     * @param numero_reparticao Número de repartição associada a um cidadão
     */
    public Cidadao(String nome, int n_contribuinte, String email, CodigoPostal cod_postal, int numero_reparticao) {
        this.nome = nome;
        this.email = email;
        this.cod_postal = cod_postal;
        this.n_contribuinte = n_contribuinte;
        this.numero_reparticao = numero_reparticao;
    }

    public Cidadao(Cidadao c) {
        this.nome = c.nome;
        this.email = c.email;
        this.cod_postal = c.cod_postal;
        this.n_contribuinte = c.n_contribuinte;
        this.numero_reparticao = c.numero_reparticao;
    }

    /**
     * @return O nome de um cidadão
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Número de Contribuinte
     */
    public int getNumContribuinte() {
        return n_contribuinte;
    }

    /**
     * @return Email de um cidadão
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Código Postal de um cidadão
     */
    public CodigoPostal getCodPostal() {
        return cod_postal;
    }

    /**
     *
     * @return Número de Repartição associado a um cidadão
     */
    public int getNumeroReparticao() {
        return numero_reparticao;
    }

    /**
     * @param Nome nome a alterar um cidadão
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param email Email para alterar um cidadão
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param cod_postal Código Postal a alterar de um cidadão
     */
    public void setCodPostal(CodigoPostal cod_postal) {
        this.cod_postal = cod_postal;
    }

    /**
     *
     * @return Hashcode de um cidadao
     */
    @Override
    public int hashCode() {
        return nome.hashCode() + email.hashCode() + cod_postal.hashCode() + n_contribuinte;
    }

    /**
     * Retorna se um cidadão é igual a outro
     *
     * @param obj Objeto a comparar
     * @return true or false
     */
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
        final Cidadao other = (Cidadao) obj;
        if (this.n_contribuinte != other.n_contribuinte) {
            return false;
        }
        if (!this.nome.equals(other.nome)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        if (!this.cod_postal.equals(other.cod_postal)) {
            return false;
        }
        if (this.numero_reparticao != other.numero_reparticao) {
            return false;
        }
        return true;
    }

    /**
     * Comparar dois cidadãos pela ordem natural do número de contribuinte
     *
     * @param o Cidadao a comparar
     * @return a diferença entre número de contribuintes
     */
    @Override
    public int compareTo(Cidadao o) {
        return this.n_contribuinte - o.n_contribuinte;
    }

    /**
     *
     * @return String com a designação de um Cidadão
     */
    @Override
    public String toString() {
        return "Cidadao{" + "nome=" + nome + ", email=" + email + ", cod_postal=" + cod_postal + ", n_contribuinte=" + n_contribuinte + ", numero_reparticao=" + numero_reparticao + '}';
    }
}
