/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
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

    /**
     * Set de Serviços Disponíveis
     */
    private Set<Character> listaServicos;

    /**
     * Mapa com contador por determinado serviço
     */
    private Map<Character, Integer> mapaContadorSenhasPorServico;

    /**
     * Lista duplamente ligada com as senhas
     */
    private DoublyLinkedList<Senha> listaSenhas;

    /**
     * Valor omissao de um contador
     */
    private static final int VALOR_CONTADOR_OMISSAO = 0;

    public Reparticao(String cidade, int numReparticao, String codigoPostal) {
        this.cidade = cidade;
        this.numReparticao = numReparticao;
        this.codigoPostal = codigoPostal;

        //Inicializar contentores
        listaServicos = new HashSet<>();
        mapaContadorSenhasPorServico = new HashMap();
        listaSenhas = new DoublyLinkedList<>();
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
     * Método que adiciona serviços ao set de Serviços caso já não existam
     *
     * @param c Serviço a adicionar
     * @return true or false
     */
    public boolean adicionarServiço(char c) {
        if (!listaServicos.contains(c)) {
            mapaContadorSenhasPorServico.put(c, VALOR_CONTADOR_OMISSAO);
            return listaServicos.add(c);
        }
        return false;
    }
    
    /**
     * Método que adiciona senhas ao set de Senhas caso já não existam
     * 
     * @param senha Senha a adicionar
     * @return true or false
     */
    public boolean adicionarSenha(Senha senha) {
        if (listaServicos.contains(senha.getCodServico())) {
            listaSenhas.addLast(senha);
            Integer contador = mapaContadorSenhasPorServico.get(senha.getCodServico());
            mapaContadorSenhasPorServico.put(senha.getCodServico(), contador + 1);
            return true;
        }
        return false;

    }

    /**
     * Método que tira uma nova senha
     *
     * @param nif Nif do cidadao
     * @param cod_servico cod_serviço
     * @return
     */
    public Senha tirarSenha(long nif, char cod_servico) {
        int contador = mapaContadorSenhasPorServico.get(cod_servico);
        contador++;
        Senha senha = new Senha(nif, cod_servico, contador);
        listaSenhas.addLast(senha);
        return senha;
    }

    /**
     * Método para abandonar todas as senhas de um determinado nif
     *
     * @param nif NIF do cidadão
     * @return true or false
     */
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.numReparticao;
        hash = 97 * hash + Objects.hashCode(this.codigoPostal);
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
        return Objects.equals(this.listaServicos, other.listaServicos);
    }

    @Override
    public String toString() {
        return "Reparticao{" + "cidade=" + cidade + ", numReparticao=" + numReparticao + ", codigoPostal=" + codigoPostal + ", listaServicos=" + listaServicos + '}';
    }

}
