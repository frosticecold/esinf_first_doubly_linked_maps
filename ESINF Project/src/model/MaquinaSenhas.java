/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
class MaquinaSenhas {

    private Set<Character> listaDeServicos;
    private Map<Character, Integer> mapaContadorPorAssunto;
    private DoublyLinkedList<Senha> listaSenhas;
    private static int VALOR_CONTADOR_OMISSAO = 0;

    public MaquinaSenhas(Set<Character> listaDeServicos) {
        this.listaDeServicos = listaDeServicos;
        listaSenhas = new DoublyLinkedList<>();

        for (Character c : listaDeServicos) {
            mapaContadorPorAssunto.put(c, VALOR_CONTADOR_OMISSAO);
        }

    }

    public Senha tirarSenha(long nif, char cod_servico) {
        int contador = mapaContadorPorAssunto.get(cod_servico);
        contador++;

        Senha senha = new Senha(nif, cod_servico, contador);
        listaSenhas.addLast(senha);

        return senha;
    }

    public boolean abandonarFilas(long nif) {
        boolean removed = false;
        ListIterator<Senha> it = listaSenhas.listIterator();
        while (it.hasNext()) {
            Senha s = it.next();
            if (s.getNif() == nif) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }
}
