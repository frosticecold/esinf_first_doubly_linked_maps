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
     * Registo de Repartições
     */
    private final RegistoReparticao registoReparticao;
    /**
     * Registo de Cidadãos
     */
    private final RegistoCidadao registoCidadao;

    /**
     * Mapa de cidadaos associados a uma repartição
     */
    private final Map<Integer, Set<Cidadao>> mapaCidadaosPorNumReparticao;

    /**
     * Mapa de Repartições por Número de Repartição
     */
    private final Map<Integer, Reparticao> mapaReparticaoPorNumReparticao;

    /**
     * Mapa de Repartição Por Nif de Cidadão
     */
    private final Map<Long, Reparticao> mapaReparticaoPorNifCidadao;

    /**
     * Nome do ficheiro ficheiro_repartições
     */
    private static final String FX_REPARTICAO = "fx_repartições.txt";
    /**
     * Nome do ficheiro_cidadaos
     */
    private static final String FX_CIDADAOS = "fx_cidadaos.txt";
    /**
     * Nome do ficheiro_senhas
     */
    private static final String FX_SENHAS = "fx_senhas.txt";

    /**
     * Construtor de uma Gestão de Atendimento
     */
    public GestaoAtendimento() {
        registoReparticao = new RegistoReparticao();
        registoCidadao = new RegistoCidadao();
        mapaCidadaosPorNumReparticao = new HashMap<>();
        mapaReparticaoPorNumReparticao = new HashMap<>();
        mapaReparticaoPorNifCidadao = new HashMap<>();

    }

    /*==========================================================================
    ==============================Métodos de inicializar========================*/
    /**
     * Método que inicializa todos os mapas de repartições após a leitura do
     * ficheiro
     */
    private void inicializarMapaReparticao(Reparticao r) {

        if (!mapaCidadaosPorNumReparticao.containsKey(r.getNumReparticao())) {
            mapaCidadaosPorNumReparticao.put(r.getNumReparticao(), new HashSet<>());
        }
        if (!mapaReparticaoPorNumReparticao.containsKey(r.getNumReparticao())) {
            mapaReparticaoPorNumReparticao.put(r.getNumReparticao(), r);
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
     * @return True or False
     */
    public boolean adicionarReparticao(Reparticao r) {
        boolean added = registoReparticao.adicionarReparticao(r);
        if (added == true) {
            inicializarMapaReparticao(r);
            passarCidadaosDeUmaReparticaoParaOutra(r);
            return true;
        }
        return false;
    }

    /*==========================================================================
    ==============================Alinea C========================*/
    /**
     * Método que remove uma repartição apenas se existir mais do que uma, E
     * passa todos os cidadãos associados a essa repartição à repartição com o
     * código postal (4 dígitos) mais próxima;
     *
     * @param r Reparticao a Remover
     * @return true or false
     */
    public boolean removerReparticao(Reparticao r) {
        boolean removed = false;
        final int LIMITE = 0;
        if (registoReparticao.size() > LIMITE) {
            Reparticao repMaisProxima = null;
            Set<Cidadao> listaCidadaos = null;
            if (registoReparticao.size() > 0) {
                repMaisProxima = obterReparticaoMaisProximaPorCodigoPostal(r);
                listaCidadaos = mapaCidadaosPorNumReparticao.get(r.getNumReparticao());
            }
            for (Cidadao c : listaCidadaos) {
                //Remover dos mapas da reparticao antiga
                mapaCidadaosPorNumReparticao.get(r.getNumReparticao()).remove(c);
                mapaReparticaoPorNifCidadao.remove(c.getNif());
                mapaReparticaoPorNumReparticao.remove(r.getNumReparticao());

                if (repMaisProxima != null && listaCidadaos != null) {
                    //Alterar num reparticao de um cidadao
                    c.setNumReparticao(repMaisProxima.getNumReparticao());
                    //Adicionar aos mapas da reparticao nova
                    mapaCidadaosPorNumReparticao.get(repMaisProxima.getNumReparticao()).add(c);
                    mapaReparticaoPorNifCidadao.put(c.getNif(), repMaisProxima);

                }
            }
            if (removed == false) {
                removed = true;
            }
            registoReparticao.removerReparticao(r);
        }
        return removed;
    }

    /*==========================================================================
    ==============================Alínea D========================*/
    /**
     * Retorna uma lista de CidadaoAfectos, que é que cidade, num repartição e
     * nif de cidadãos afetados por cada repartição
     *
     * @return lista de CidadaoAfecto
     */
    public List<CidadaosAfetosPorReparticao> quaisCidadaosAfectos() {
        List<CidadaosAfetosPorReparticao> listaCidadaoAfecto = new ArrayList<>();
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            String cidade = r.getCidade();
            int numRep = r.getNumReparticao();
            Set<Cidadao> setCidadao = mapaCidadaosPorNumReparticao.get(r.getNumReparticao());
            CidadaosAfetosPorReparticao cf = new CidadaosAfetosPorReparticao(cidade, numRep, setCidadao);
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
        boolean added;
        added = registoCidadao.adicionarCidadao(c);
        if (added == true) {
            mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c);
            mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));
        }
        return added;
    }

    /*==========================================================================
    ==============================Alínea F========================*/
    public void conhecerUtilizacaoReparticao(Reparticao r, int hora, int min) {
        final int MIN_HORA = 9;
        final int MAX_HORA = 15;

        final int HORA_ABERTURA = 9;
        final int HORA_FECHO = 15;
        final int MIN_ABERTURA = 0;
        final int MIN_FECHO = 30;

        final int MIN_MINUTOS = 0;
        final int MAX_MINUTOS = 60;
        if (hora >= HORA_ABERTURA && hora <= HORA_FECHO) {
        }
    }

    /*==========================================================================
    ==============================Alínea G========================*/
    public Map<Character, Integer> determinarServicosComMaiorProcura() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        Map<Character, Integer> mapaNumeroSenhasPorServico = new HashMap<>();
        while (it.hasNext()) {
            Reparticao r = it.next();
            Map<Character, Integer> outroMapa = r.determinarProcura();
            if (!outroMapa.isEmpty()) {
                for (Character ch : outroMapa.keySet()) {
                    if (mapaNumeroSenhasPorServico.containsKey(ch)) {
                        int cont = mapaNumeroSenhasPorServico.get(ch);
                        cont += outroMapa.get(ch);
                        mapaNumeroSenhasPorServico.put(ch, cont);
                    } else {
                        int cont = outroMapa.get(ch);
                        mapaNumeroSenhasPorServico.put(ch, cont);
                    }
                }
            }
        }
        return mapaNumeroSenhasPorServico;
    }

    /*==========================================================================
    ============================Métodos de Obter========================*/
    /**
     * Método que obtém uma repartição associada a um cidadão
     *
     * @param nif Nif do Cidadão associado à repartição
     * @return Repartição ou Null
     */
    public Reparticao obterReparticaoAssociadaNif(Long nif) {
        if (mapaReparticaoPorNifCidadao.containsKey(nif)) {
            return mapaReparticaoPorNifCidadao.get(nif);
        }
        return null;
    }

    /**
     * Métdo que obtém a Repartição mais próxima em termos de código postal
     *
     * @param r Repartição com o código postal a comparar
     * @return Repartição ou null
     */
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
    public void lerFicheirosDados() {
        Ficheiro f = new Ficheiro();
        f.lerReparticoes(this, FX_REPARTICAO);
        f.lerCidadaos(this, FX_CIDADAOS);
        validarCodigoPostalTodosCidadaos();
        f.lerSenhas(this, FX_SENHAS);

    }

    /*==========================================================================
    ============================Métodos Utilitários e Testes========================*/
    public List<Reparticao> obterListaReparticoes() {
        List<Reparticao> lista = new ArrayList<>();
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        return lista;
    }

    /**
     * Retorna uma Lista de Cidadãos associados a uma repartição Para questões
     * de teste
     *
     * @param r Repartição
     * @return Lista de Cidadãos
     */
    public List<Cidadao> obterCidadaosAssociadosAReparticao(Reparticao r) {
        List<Cidadao> lista = new ArrayList<>();
        if (!mapaCidadaosPorNumReparticao.get(r.getNumReparticao()).isEmpty()) {
            for (Cidadao c : mapaCidadaosPorNumReparticao.get(r.getNumReparticao())) {
                lista.add(c);
            }
        }
        return lista;
    }

    /**
     * Retorna uma Reparticao através do código postal associado Para questão de
     * teste
     *
     * @param codPostal CodPostal
     * @return Lista de Cidadãos
     */
    public Reparticao obterReparticaoPorCodigoPostal(String codPostal) {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            if (r.getCodigoPostal().equals(codPostal)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Retorna uma lista de cidadadãos através do código postal associado Para
     * questão de testes
     *
     * @param r
     * @return
     */
    public List<Cidadao> obterCidadaosPorCodigoPostal(Reparticao r) {
        List<Cidadao> lista = registoCidadao.obterCidadaosPorCodigoPostal(r);
        return lista;
    }

    /*==========================================================================
    ==============================Métodos de Validar========================*/
    /**
     * Método que vai buscar uma lista de todos os cidadãos que deveriam ser
     * afetados por a repartição passada por parâmetro e troca as referênciais
     * anteriores dos cidadãos para a nova repartição
     *
     *
     * @param r Repartição a trocar a referência
     */
    private void passarCidadaosDeUmaReparticaoParaOutra(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = registoCidadao.obterCidadaosPorCodigoPostal(r);
        if (!listaCidadaosPorCodPostal.isEmpty()) {
            for (Cidadao c : listaCidadaosPorCodPostal) {
                //Remover as referênciais à repartição anterior
                mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).remove(c);
                //Mudar as referências para a repartição nova
                c.setNumReparticao(r.getNumReparticao());
                mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c);
                mapaReparticaoPorNumReparticao.put(c.getNumReparticao(), r);

            }
        }
    }

    /**
     * Método chamado após a leitura de Cidadãos que valida se cidadãos estão na
     * repartição correta
     */
    private void validarCodigoPostalTodosCidadaos() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();
        while (it.hasNext()) {
            Reparticao r = it.next();
            passarCidadaosDeUmaReparticaoParaOutra(r);
        }
    }

}
