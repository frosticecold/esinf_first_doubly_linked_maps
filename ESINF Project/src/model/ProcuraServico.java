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
public class ProcuraServico implements Comparable<ProcuraServico> {

    private final char servico;

    private final int qtdSenhas;

    public ProcuraServico(char servico, int qtdSenhas) {
        this.servico = servico;
        this.qtdSenhas = qtdSenhas;
    }

    public char getServico() {
        return servico;
    }

    public int getQtdSenhas() {
        return qtdSenhas;
    }

    @Override
    public int compareTo(ProcuraServico o) {
        if (qtdSenhas > o.qtdSenhas) {
            return qtdSenhas - o.qtdSenhas;
        } else {
            if (qtdSenhas == o.qtdSenhas) {
                return 0;
            } else {
                return o.qtdSenhas - qtdSenhas;
            }
        }
    }

}
