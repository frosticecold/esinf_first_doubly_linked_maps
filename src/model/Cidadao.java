/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Cidadao implements Comparable<Cidadao> {

    /**
     * Nif de um cidadão
     */
    private final long nif;

    /**
     * Nome de um cidadão
     */
    private String nome;

    /**
     * Email de um cidadão
     */
    private String email;

    /**
     * Código postla de um cidadão
     */
    private String codigoPostal;

    /**
     * Número de repartição a que pertence
     */
    private int numReparticao;

    /**
     *
     * Construtor que recebe como parâmetro
     *
     * @param nif nif de um cidadão
     * @param nome nome de um cidadão
     * @param email email de um cidadão
     * @param codigoPostal código postal de um cidadão
     * @param numReparticao qual o número de repartição a que pertence
     */
    public Cidadao(String nome, long nif, String email, String codigoPostal, int numReparticao) { 
        this.nif = nif; 
        this.nome = nome;
        this.email = email;
        this.codigoPostal = codigoPostal;
        this.numReparticao = numReparticao;
    }

    /**
     * @return the nif
     */
    public long getNif() {
        return nif;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @return the numReparticao
     */
    public int getNumReparticao() {
        return numReparticao;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @param numReparticao the numReparticao to set
     */
    public void setNumReparticao(int numReparticao) {
        this.numReparticao = numReparticao;
    }

    @Override
    public int compareTo(Cidadao o) {
        return nome.compareTo(o.nome);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.nif ^ (this.nif >>> 32));
        return hash;
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
        final Cidadao other = (Cidadao) obj;
        if (this.nif != other.nif) {
            return false;
        }
        if (this.numReparticao != other.numReparticao) {
            return false;
        }
        if (!this.nome.equals(other.nome)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        if(!this.codigoPostal.equals(other.codigoPostal)){
        return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cidadao{" + "nif=" + nif + ", nome=" + nome + ", email=" + email + ", codigoPostal=" + codigoPostal + ", numReparticao=" + numReparticao + '}';
    }

}
