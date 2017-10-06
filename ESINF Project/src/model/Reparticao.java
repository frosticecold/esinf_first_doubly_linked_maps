/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Reparticao {

    /**
     * Cidade da repartição
     */
    private String cidade;
    /**
     * Número de repartição
     */
    private int numReparticao;
    /**
     * Código Postal de uma repartição
     */
    private String codigoPostal;

    @Override
    public int hashCode() {
        int hash = cidade.hashCode() + numReparticao + codigoPostal.hashCode();
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
        final Reparticao other = (Reparticao) obj;
        if (this.numReparticao != other.numReparticao) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        if (!Objects.equals(this.listaServicos, other.listaServicos)) {
            return false;
        }
        if (!Objects.equals(this.maquinaDeSenhas, other.maquinaDeSenhas)) {
            return false;
        }
        return true;
    }
    /**
     * Set de Serviços Disponíveis
     */
    private Set<Character> listaServicos;

    /**
     * Máquina de Senhas de uma Repartição
     */
    private MaquinaSenhas maquinaDeSenhas;

    public Reparticao(String cidade, int numReparticao, String codigoPostal) {
        this.cidade = cidade;
        this.numReparticao = numReparticao;
        this.codigoPostal = codigoPostal;
        listaServicos = new HashSet<>();
        maquinaDeSenhas = new MaquinaSenhas(listaServicos);
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the numReparticao
     */
    public int getNumReparticao() {
        return numReparticao;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @return the listaServicos
     */
    public Set<Character> getListaServicos() {
        return listaServicos;
    }

    /**
     * @return the maquinaDeSenhas
     */
    public MaquinaSenhas getMaquinaDeSenhas() {
        return maquinaDeSenhas;
    }

    public boolean adicionarServiço(char c) {
        if (!listaServicos.contains(c)) {
            return getListaServicos().add(c);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reparticao{" + "cidade=" + cidade + ", numReparticao=" + numReparticao + ", codigoPostal=" + codigoPostal + ", listaServicos=" + listaServicos + ", maquinaDeSenhas=" + maquinaDeSenhas + '}';
    }

}
