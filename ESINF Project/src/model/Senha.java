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
public class Senha {

    /**
     * Número de contribuinte de um cidadão
     */
    private long nif;

    /**
     * Código de um serviço
     */
    private char cod_servico;

    /**
     * Número da ordem
     */
    private int num_ordem;

    /**
     * Construtor para um objeto senha
     *
     * @param nif Nif de um cidadão
     * @param cod_servico Código de serviço
     * @param num_ordem Ordem da senha
     */
    public Senha(long nif, char cod_servico, int num_ordem) {
        this.nif = nif;
        this.cod_servico = cod_servico;
        this.num_ordem = num_ordem;
    }

    public long getNif() {
        return nif;
    }

    public char getCodServico() {
        return cod_servico;
    }

    public int getNumOrdem() {
        return num_ordem;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }
}
