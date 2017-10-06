/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.RegistoCidadao;
import estruturas.RegistoReparticao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import util.Ficheiro;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class GestaoAtendimento {

    /**
     * Nome do ficheiro ficheiro_repartições
     */
    private final String FX_REPARTICAO = "fx_repartições.txt";
    /**
     * Nome do ficheiro_cidadaos
     */
    private final String FX_CIDADAOS = "fx_cidadaos.txt";
    /**
     * Nome do ficheiro_senhas
     */
    private final String FX_SENHAS = "fx_senhas.txt";

    /**
     * Registo de Repartições
     */
    private RegistoReparticao registoReparticao;
    /**
     * Registo de Cidadãos
     */
    private RegistoCidadao registoCidadao;

    /**
     * Mapa de cidadaos associados a uma repartição
     */
    private Map<Integer, Set<Cidadao>> mapaCidadaosPorReparticao;

    /**
     * Mapa de Repartições por Número de Repartição
     */
    private Map<Integer, Reparticao> mapaReparticaoPorNumReparticao;

    /**
     * Mapa de Repartição Por Nif de Cidadão
     */
    private Map<Long, Reparticao> mapaReparticaoPorNifCidadao;

    public GestaoAtendimento() {

        registoReparticao = new RegistoReparticao();
        registoCidadao = new RegistoCidadao();
        mapaCidadaosPorReparticao = new HashMap<>();
        mapaReparticaoPorNumReparticao = new HashMap<>();
        mapaReparticaoPorNifCidadao = new HashMap<>();
    }

    /*==========================================================================
    ==============================Métodos de inicializar========================*/
    private void inicializarMapaReparticoes() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            if (!mapaCidadaosPorReparticao.containsKey(r.getNumReparticao())) {
                mapaCidadaosPorReparticao.put(r.getNumReparticao(), new HashSet<>());
            }
            if (!mapaReparticaoPorNumReparticao.containsKey(r.getNumReparticao())) {
                mapaReparticaoPorNumReparticao.put(r.getNumReparticao(), r);
            }
        }
    }

    /*==========================================================================
    ==============================Alínea B========================*/
    /**
     * Método que adiciona uma Repartição ao Gestão Atendimento, Apenas se ele
     * não for duplicado ou ter código postal repetido,Caso seja adicionado,
     * todos os cidadãos com o código postal que corresponda a esta repartição
     * devem ser adicionados
     *
     * @param r Repartição a adicionar
     */
    public void adicionarReparticao(Reparticao r) {
        boolean added = registoReparticao.adicionarReparticao(r);
        if (added == true) {
            passarCidadaosDeUmaReparticaoParaOutra(r);
        }
    }

    /*==========================================================================
    ==============================Alinea C========================*/
    public boolean removerReparticao(Reparticao r) {
        boolean removed = false;
        final int LIMITE = 1;
        if (registoReparticao.size() > LIMITE) {
            Reparticao repMaisProxima = obterReparticaoMaisProximaPorCodigoPostal(r);
            Set<Cidadao> listaCidadaos = mapaCidadaosPorReparticao.get(r);
            for (Cidadao c : listaCidadaos) {
                //Remover dos mapas da reparticao antiga
                mapaCidadaosPorReparticao.get(r.getNumReparticao()).remove(c);
                mapaReparticaoPorNifCidadao.remove(c.getNif());
                mapaReparticaoPorNumReparticao.remove(r.getNumReparticao());

                //Alterar num reparticao de um cidadao
                c.setNumReparticao(repMaisProxima.getNumReparticao());
                //Adicionar aos mapas da reparticao nova
                mapaCidadaosPorReparticao.get(repMaisProxima.getNumReparticao()).add(c);
                mapaReparticaoPorNifCidadao.put(c.getNif(), repMaisProxima);
                if (removed == false) {
                    removed = true;
                }
            }
            registoReparticao.removerReparticao(r);
        }
        return removed;
    }

    /*==========================================================================
    ==============================Alínea D========================*/
    public List<CidadaoAfecto> quaisCidadaosAfectos() {
        List<CidadaoAfecto> listaCidadaoAfecto = new ArrayList<>();
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            String cidade = r.getCidade();
            int numRep = r.getNumReparticao();
            Set<Cidadao> setCidadao = mapaCidadaosPorReparticao.get(r);
            CidadaoAfecto cf = new CidadaoAfecto(cidade, numRep, setCidadao);
            listaCidadaoAfecto.add(cf);
        }
        return listaCidadaoAfecto;
    }

    /*==========================================================================
    ==============================Alínea E========================*/
    /**
     * Método que adiciona um Cidadao ao Gestão Atendimento, apenas senão
     * existir, caso seja adicionado, vamos adicionar uma referencia entre o
     * mapaCidadaosPorReparticao e o mapaReparticaoPorNifCidadao
     *
     * @param c cidadao a adicionar
     * @return true or false
     */
    public boolean adicionarCidadao(Cidadao c) {
        boolean added = false;
        added = registoCidadao.adicionarCidadao(c);
        if (added == true) {
            mapaCidadaosPorReparticao.get(c.getNumReparticao()).add(c);
            mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));
        }
        return added;
    }

    /*==========================================================================
    ==============================Métodos de Validar========================*/
    public void passarCidadaosDeUmaReparticaoParaOutra(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = registoCidadao.obterCidadaosPorCodigoPostal(r);
        if (!listaCidadaosPorCodPostal.isEmpty()) {
            for (Cidadao c : listaCidadaosPorCodPostal) {
                mapaCidadaosPorReparticao.get(c.getNumReparticao()).remove(c);
                c.setNumReparticao(r.getNumReparticao());
                mapaCidadaosPorReparticao.get(c.getNumReparticao()).add(c);
                mapaReparticaoPorNumReparticao.put(c.getNumReparticao(), r);

            }
        }
    }

    private void validarCodigoPostalTodosCidadaos() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            passarCidadaosDeUmaReparticaoParaOutra(r);
        }
    }

    /*==========================================================================
    ============================Métodos de Obter========================*/
    public Reparticao obterReparticaoAssociadaNif(Long nif) {
        if (mapaReparticaoPorNifCidadao.containsKey(nif)) {
            return mapaReparticaoPorNifCidadao.get(nif);
        }
        return null;
    }

    /*==========================================================================
    ============================Métodos de LerFicheiros========================*/
    /**
     * Método que lê todos os ficheiros de dados, Primeiro lê o ficheiro das
     * repartições e adiciona ao registo de repartições, depois incializa o mapa
     * que relaciona numrepartição com cidadãos Lê o ficheiro dos cidadãos e
     * adiciona ao registo de cidadãos Valida todos os cidadãos para pertencerem
     * à devida repartição com o código postal correspondente Por fim lê as
     * senhas e adiciona as senhas à repartição correspondente
     */
    private void lerFicheirosDados() {
        Ficheiro f = new Ficheiro();
        f.lerReparticoes(this, FX_REPARTICAO);
        inicializarMapaReparticoes();
        f.lerCidadaos(this, FX_CIDADAOS);
        validarCodigoPostalTodosCidadaos();
        f.lerSenhas(this, FX_SENHAS);

    }

    /*==========================================================================
    ============================Métodos Utilitários========================*/
    private Reparticao obterReparticaoMaisProximaPorCodigoPostal(Reparticao r) {
        int codPostalRep1 = Integer.parseInt(r.getCodigoPostal());
        Reparticao outraRep = null;
        Reparticao RepaRetornar = null;
        int dif = -1;
        final int SEM_DIFERENÇA = -1;
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            outraRep = it.next();
            int outroCodPostal = Integer.parseInt(outraRep.getCodigoPostal());
            if (dif == SEM_DIFERENÇA) {
                dif = Math.abs(codPostalRep1 - outroCodPostal);
                RepaRetornar = outraRep;
            } else {
                int d = Math.abs(codPostalRep1 - outroCodPostal);
                if (d < dif) {
                    RepaRetornar = outraRep;
                }
            }

        }
        return RepaRetornar;
    }

}
