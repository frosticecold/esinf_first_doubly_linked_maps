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
    //Modificado
    public boolean adicionarServico(char c) {
        if (!listaServicos.contains(c)) {
            mapaListaSenhasPorServico.put(c, new ArrayList<>());
            return listaServicos.add(c);
        }
        return false;
    }

    /**
     * Método que adiciona senhas ao ler do ficheiro para o set de Senhas caso
     * já não existam
     *
     * @param senha Senha a adicionar
     * @return true or false
     */
    //Modificado
    public boolean adicionarSenha(Senha senha) {                                //O(1)
        if (listaServicos.contains(senha.getCodServico())) {                    //O(1)
            listaSenhas.addLast(senha);                                         //O(1)
            mapaListaSenhasPorServico.get(senha.getCodServico()).add(senha);    //O(1)
            if (mapaServicosPorNif.get(senha.getNif()) == null) {               //O(1)
                mapaServicosPorNif.put(senha.getNif(), new HashSet<>());        //O(1)
            }

            mapaServicosPorNif.get(senha.getNif()).add(senha.getCodServico());  //O(1)
            return true;                                                        //O(1)
        }
        return false;                                                           //O(1)

    }

    /**
     * Método que tira uma nova senha
     *
     * @param nif Nif do cidadao
     * @param cod_servico cod_serviço
     * @return
     */
    public Senha tirarSenha(long nif, char cod_servico) {                   //O(1)
        int contador = mapaListaSenhasPorServico.get(cod_servico).size();   //O(1)
        contador++;                                                         //O(1)
        Senha senha = new Senha(nif, cod_servico, contador);                //O(1)
        listaSenhas.addLast(senha);                                         //O(1)
        mapaListaSenhasPorServico.get(cod_servico).add(senha);              //O(1)
        if (mapaServicosPorNif.get(nif) == null) {                          //O(1)
            mapaServicosPorNif.put(nif, new HashSet<>());                   //O(1)
        }
        mapaServicosPorNif.get(nif).add(senha.getCodServico());             //O(1)
        return senha;                                                       //O(1)
    }

    /**
     * Método para abandonar todas as senhas de um determinado nif
     *
     * @param nif NIF do cidadão
     * @return true or false
     */
    //Modificado
    public boolean abandonarFilas(long nif) {
        boolean removed = false;                                            //O(1)   
        ListIterator<Senha> it = listaSenhas.listIterator();                //O(1)

        if (mapaServicosPorNif.containsKey(nif)) {                          //O(1)
            Set<Character> setChar = mapaServicosPorNif.get(nif);           //O(1)
            Set<Character> setCharARemover = new HashSet<>();               //O(1)
            Senha senha = null;                                             //O(1)
            List<Senha> listaSenhas = null;                                 //O(1)
            for (Character c : setChar) {                                   //O(n) * O(n) = O(n^2)
                listaSenhas = mapaListaSenhasPorServico.get(c);             //O(1)

                ListIterator<Senha> it_interno = listaSenhas.listIterator();
                while (it_interno.hasNext()) {                              //O(n)
                    senha = it_interno.next();                              //O(1)
                    if (senha.getNif() == nif) {                            //O(1)
                        setCharARemover.add(c);                             //O(1)
                        it_interno.remove();                                //O(1)
                        removed = true;                                     //O(1)
                    }
                }
            }

            for (Character c : setCharARemover) {                           //O(n)
                mapaServicosPorNif.get(nif).remove(c);                      //O(1)
            }

            if (removed == true) {                                          //O(1)
                while (it.hasNext()) {                                      //O(n)
                    Senha s = it.next();                                    //O(1)
                    if (s.getNif() == nif) {                                //O(1)
                        it.remove();                                        //O(1)
                        removed = true;                                     //O(1)
                    }
                }
            }
        }
        return removed;                                                     //O(1)
    }                                                                       //Total = O(n^2)

    /**
     * Retorna quantas senhas existe numa lista de Senhas para um dado serviço
     *
     * @param cod_servico codigo de serviço
     * @return Quantidade de senhas
     */
    public int quantasSenhasPorServico(char cod_servico) {
        return mapaListaSenhasPorServico.get(cod_servico).size();
    }

    /**
     * Retorna quantas senhas têm uma repartição
     *
     * @return
     */
    public int quantasSenhas() {
        return listaSenhas.size();
    }

    /**
     * Método que retorna um mapa com os serviços e o número de senhas associado
     * a cada serviço
     *
     * @return Map<Character,Integer>
     */
    public Map<Character, Integer> determinarProcura() {

        Map<Character, Integer> mapaNumSenhasPorServico = new HashMap<>();      //O(1)
        for (Character c : listaServicos) {                                     //O(n)
            int size = mapaListaSenhasPorServico.get(c).size();                 //O(1)
            mapaNumSenhasPorServico.put(c, size);                               //O(1)
        }

        return mapaNumSenhasPorServico;                                         //O(1)
    }                                                                           //Total = O(n)

    /**
     * Método que recebe quantas filas de 10mins se vai atender e mostra que
     * clientes são atendidos por cada fila Tendo em conta que clientes não
     * podem ser atendidos ao mesmo tempo
     *
     * @param numFilas Num Filas de atendimento numa determinada hora/min
     * @return Mapa que tem como chave cada 10mins (indice 0,1,2,3) com a lista
     * de senhas atendidas
     */
    public Map<Integer, List<Senha>> conhecerUtilizacaoReparticao(int numFilas) {
        Map<Integer, List<Senha>> mapaSenhasPorFila = new HashMap<>();

        for (int i = 0; i < numFilas; i++) {
            mapaSenhasPorFila.put(i, new ArrayList<>());
        }
        DoublyLinkedList<Senha> listaSenhasRemovidas = new DoublyLinkedList<>();

        //Copia a doublylinkedlist para uma nova
        ListIterator<Senha> itSenhas = listaSenhas.listIterator();
        while (itSenhas.hasNext()) {
            listaSenhasRemovidas.addLast(itSenhas.next());
        }
        ListIterator<Senha> it = listaSenhasRemovidas.listIterator();
        int indice = 0;
        int numSenha = 0;
        do {

            Senha senha = null;
            List<Senha> listaS = mapaSenhasPorFila.get(indice);
            if (it.hasNext()) {
                senha = it.next();
                if (listaContemMesmoNifOuCodSenha(listaS, senha) == false) {
                    listaS.add(senha);
                    it.remove();
                    numSenha++;
                }

            } else {
                it = listaSenhasRemovidas.listIterator();
                indice++;

            }
        } while (!listaSenhasRemovidas.isEmpty() || indice < numFilas);
        return mapaSenhasPorFila;
    }

    /**
     * Método que verifica se uma lista contêm uma senha com o mesmo nif ou cod
     * de serviço
     *
     * @param lista Lista de Senhas
     * @param senha Senha a comparar
     * @return true or false
     */
    private boolean listaContemMesmoNifOuCodSenha(List<Senha> lista, Senha senha) {
        for (Senha s : lista) {
            if (s.getNif() == senha.getNif() || s.getCodServico() == senha.getCodServico()) {
                return true;
            }
        }
        return false;
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
