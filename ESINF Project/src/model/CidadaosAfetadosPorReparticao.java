/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class CidadaosAfetadosPorReparticao {

    private final int numReparticao;

    private final String cidade;

    private final Set<Long> listaNif;

    /**
     * Construtor de um CidadaoAfecto que recebe como parâmetro um número de
     * reparticao, uma cidade e uma lista de cidadaos
     *
     * @param numReparticao
     * @param cidade
     * @param listaCidadao
     */
    public CidadaosAfetadosPorReparticao(String cidade, int numReparticao, Set<Cidadao> listaCidadao) {
        this.numReparticao = numReparticao;
        this.cidade = cidade;
        this.listaNif = new HashSet<>();
        copiarListaCidadaosParaListaNif(listaCidadao);
    }


    public int getNumReparticao() {
        return numReparticao;
    }

    public String getCidade() {
        return cidade;
    }

    public Set<Long> getListaNif() {
        return listaNif;
    }

    @Override
    public String toString() {
        return "CidadaoAfecto{" + "numReparticao=" + numReparticao + ", cidade=" + cidade + ", listaNif=" + listaNif + '}';
    }
    
    /**
     * Copia os nifs de uma lista de cidadaos
     *
     * @param listaCidadao Lista de Cidadaos a copiar os nifs
     */
    private void copiarListaCidadaosParaListaNif(Set<Cidadao> listaCidadao) { 
        for (Cidadao c : listaCidadao) {    //O(n)
            listaNif.add(c.getNif());       //O(1)
        }

    }
}                                           //Total O(n)
