/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.DoublyLinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
     * Mapa com as listas das Senhas Por Serviço
     */
    private Map<Character, List<Senha>> mapaListaSenhasPorServico;

    /**
     * Mapa de Serviços por Cidadao
     */
    private Map<Long, Set<Character>> mapaServicosPorNif;
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

        listaSenhas = new DoublyLinkedList<>();

        mapaListaSenhasPorServico = new HashMap();
        mapaServicosPorNif = new HashMap<>();
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
            mapaListaSenhasPorServico.put(c, new ArrayList<>());
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
            mapaListaSenhasPorServico.get(senha.getCodServico()).add(senha);
            if (mapaServicosPorNif.get(senha.getNif()) == null) {
                mapaServicosPorNif.put(senha.getNif(), new HashSet<>());
            }
            mapaServicosPorNif.get(senha.getNif()).add(senha.getCodServico());
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
        int contador = mapaListaSenhasPorServico.get(cod_servico).size();
        contador++;
        Senha senha = new Senha(nif, cod_servico, contador);
        listaSenhas.addLast(senha);
        mapaListaSenhasPorServico.get(cod_servico).add(senha);
        if (mapaServicosPorNif.get(nif) == null) {
            mapaServicosPorNif.put(nif, new HashSet<>());
        }
        mapaServicosPorNif.get(nif).add(senha.getCodServico());
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

        if (mapaServicosPorNif.containsKey(nif)) {
            Set<Character> setChar = mapaServicosPorNif.get(nif);
            Set<Character> setCharARemover = new HashSet<>();
            Senha senha = null;
            List<Senha> listaSenhas = null;
            for (Character c : setChar) {
                listaSenhas = mapaListaSenhasPorServico.get(c);
                int size = listaSenhas.size();
                for (int i = 0; i < size; i++) {
                    if (listaSenhas.get(i).getNif() == nif) {
                        setCharARemover.add(c);
                        listaSenhas.remove(i);
                        size--;
                        removed = true;
                    }
                }
            }
            for (Character c : setCharARemover) {
                mapaServicosPorNif.get(nif).remove(c);
            }

            if (removed == true) {
                while (it.hasNext()) {
                    Senha s = it.next();
                    if (s.getNif() == nif) {
                        it.remove();
                        removed = true;
                    }
                }
            }
        }
        return removed;
    }

    public int quantasSenhasPorServico(char cod_servico) {
        return mapaListaSenhasPorServico.get(cod_servico).size();
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
