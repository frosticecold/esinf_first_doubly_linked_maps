/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estruturas.RegistoCidadao;
import estruturas.RegistoReparticao;
import java.util.ArrayList;
import java.util.Collections;
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
     * Mapa de Reparticao por Codigo Postal
     */
    private final Map<String, Reparticao> mapaReparticaoPorCodigoPostal;

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
        mapaReparticaoPorCodigoPostal = new HashMap<>();

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
        Ficheiro f = new Ficheiro();            //O(1)
        f.lerReparticoes(this, FX_REPARTICAO);  //O(n^3)
        f.lerCidadaos(this, FX_CIDADAOS);       //O(n^2)
        f.lerSenhas(this, FX_SENHAS);           //O(n^2)

    }                                           //Total : O(n^3)

    /*==========================================================================
    ==============================Métodos de inicializar========================*/
    /**
     * Método que inicializa todos os mapas de repartições após a leitura do
     * ficheiro
     */
    private void inicializarMapaReparticao(Reparticao r) {

        if (!mapaCidadaosPorNumReparticao.containsKey(r.getNumReparticao())) {      //O(1)
            mapaCidadaosPorNumReparticao.put(r.getNumReparticao(), new HashSet<>());    //O(1)
        }
        if (!mapaReparticaoPorNumReparticao.containsKey(r.getNumReparticao())) {        //O(1)
            mapaReparticaoPorNumReparticao.put(r.getNumReparticao(), r);                //O(1)
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
        boolean added = registoReparticao.adicionarReparticao(r);   //O(n)
        if (added == true) {                                        //O(1)
            inicializarMapaReparticao(r);                           //O(1)
            passarCidadaosDeUmaReparticaoParaOutra(r);              //O(n)
            mapaReparticaoPorCodigoPostal.put(r.getCodigoPostal(), r); //O(1)
            return true;                                            //O(1)
        }
        return false;                                               //O(1)
    }                                                               //Total O(n)

    /**
     * Método que vai buscar uma lista de todos os cidadãos que deveriam ser
     * afetados por a repartição passada por parâmetro e troca as referênciais
     * anteriores dos cidadãos para a nova repartição
     *
     *
     * @param r Repartição a trocar a referência
     */
    private void passarCidadaosDeUmaReparticaoParaOutra(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = registoCidadao.obterCidadaosPorCodigoPostal(r.getCodigoPostal()); //O(n)
        if (!listaCidadaosPorCodPostal.isEmpty()) {                             //O(1)
            for (Cidadao c : listaCidadaosPorCodPostal) {                       //O(n)
                //Remover as referências à repartição anterior
                mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).remove(c);//O(1)
                //Mudar as referências para a repartição nova
                c.setNumReparticao(r.getNumReparticao());                       //O(1)
                mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c);  //O(1)
                mapaReparticaoPorNumReparticao.put(c.getNumReparticao(), r);    //O(1)

            }
        }
    }                                                                           //Total O(n)

    /*==========================================================================
    ==============================Alinea C========================*/
    /**
     * Métdo que obtém a Repartição mais próxima em termos de código postal
     *
     * @param r Repartição com o código postal a comparar
     * @return Repartição ou null
     */
    private Reparticao obterReparticaoMaisProximaPorCodigoPostal(String codpostal) {
        int codPostalRep1 = Integer.parseInt(codpostal);      //O(1)
        Reparticao outraRep = null;                                     //O(1)
        Reparticao repRetornar = null;                                  //O(1)
        int dif = -1;                                                   //O(1)
        final int SEM_DIFERENÇA = -1;                                   //O(1)
        ListIterator<Reparticao> it = registoReparticao.listIterator(); //O(1)
        while (it.hasNext()) {                                          //O(n)
            outraRep = it.next();                                       //O(1)
            int outroCodPostal = Integer.parseInt(outraRep.getCodigoPostal());//O(1)
            if (dif == SEM_DIFERENÇA) {                                 //O(1)
                dif = Math.abs(codPostalRep1 - outroCodPostal);         //O(1)
                repRetornar = outraRep;                                 //O(1)
            } else {
                int d = Math.abs(codPostalRep1 - outroCodPostal);       //O(1)
                if (d < dif) {                                          //O(1)
                    repRetornar = outraRep;                             //O(1)
                }
            }

        }
        return repRetornar;                                             //O(1)
    }                                                                   //Total O(n)

    /**
     * Método que remove uma repartição apenas se existir mais do que uma, E
     * passa todos os cidadãos associados a essa repartição à repartição com o
     * código postal (4 dígitos) mais próxima;
     *
     * @param r Reparticao a Remover
     * @return true or false
     */
    public boolean removerReparticao(Reparticao r) {
        boolean removed = false;                                                //O(1)
        final int LIMITE = 0;                                                   //O(1)
        if (registoReparticao.size() > LIMITE) {                                //O(1)
            Reparticao repMaisProxima = null;                                   //O(1)
            Set<Cidadao> listaCidadaos = null;                                  //O(1)
            if (registoReparticao.size() > 0) {                                 //O(1)
                repMaisProxima = obterReparticaoMaisProximaPorCodigoPostal(r.getCodigoPostal());  //O(n)
                listaCidadaos = mapaCidadaosPorNumReparticao.get(r.getNumReparticao());//O(1)
            }
            for (Cidadao c : listaCidadaos) {                                   //O(n)
                //Remover dos mapas da reparticao antiga
                mapaCidadaosPorNumReparticao.get(r.getNumReparticao()).remove(c);//O(1)
                mapaReparticaoPorNifCidadao.remove(c.getNif());                 //O(1)
                mapaReparticaoPorNumReparticao.remove(r.getNumReparticao());    //O(1)

                if (repMaisProxima != null && listaCidadaos != null) {          //O(1)
                    //Alterar num reparticao de um cidadao
                    c.setNumReparticao(repMaisProxima.getNumReparticao());      //O(1)
                    //Adicionar aos mapas da reparticao nova
                    mapaCidadaosPorNumReparticao.get(repMaisProxima.getNumReparticao()).add(c); //O(1)
                    mapaReparticaoPorNifCidadao.put(c.getNif(), repMaisProxima);                //O(1)

                }
            }
            removed = registoReparticao.removerReparticao(r); //O(n)
            if (removed == true) {                                //O(1)
                mapaReparticaoPorCodigoPostal.remove(r.getCodigoPostal()); //O(1)
            }
        }
        return removed;         //O(1)
    }                           //Total O(n)

    /*==========================================================================
    ==============================Alínea D========================*/
    /**
     * Retorna uma lista de CidadaoAfectos, que é que cidade, num repartição e
     * nif de cidadãos afetados por cada repartição
     *
     * @return lista de CidadaoAfecto
     */
    public List<CidadaosAfetadosPorReparticao> quaisCidadaosAfectos() {
        List<CidadaosAfetadosPorReparticao> listaCidadaoAfecto = new ArrayList<>(); //O(1)
        ListIterator<Reparticao> it = registoReparticao.listIterator();             //O(1)
        while (it.hasNext()) {                                                      //O(n)
            Reparticao r = it.next();                                               //O(1)
            String cidade = r.getCidade();                                          //O(1)
            int numRep = r.getNumReparticao();                                      //O(1)
            Set<Cidadao> setCidadao = mapaCidadaosPorNumReparticao.get(r.getNumReparticao()); //O(1)
            CidadaosAfetadosPorReparticao cf = new CidadaosAfetadosPorReparticao(cidade, numRep, setCidadao); //O(1)
            listaCidadaoAfecto.add(cf);                                             //O(1)
        }
        return listaCidadaoAfecto;                                                  //O(1)
    }                                                                               //Total O(n)

    /*==========================================================================
    ==============================Alínea E========================*/
    private void validarCodigoPostalCidadao(Cidadao c) {

    }

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
        added = registoCidadao.adicionarCidadao(c); //O(1)
        Reparticao rep_por_num_rep = null;          //O(1)
        Reparticao r_cod_postal_assoc = null;       //O(1)
        if (added == true) {                        //O(1)
            rep_por_num_rep = mapaReparticaoPorNumReparticao.get(c.getNumReparticao()); //O(1)
            r_cod_postal_assoc = mapaReparticaoPorCodigoPostal.get(c.getCodigoPostal());//O(1)
            //Caso não tenha nenhuma reparticao associada
            if (rep_por_num_rep == null && r_cod_postal_assoc == null) {                //O(1)
                Reparticao r_mais_proxima = obterReparticaoMaisProximaPorCodigoPostal(c.getCodigoPostal());//O(n)
                if (r_mais_proxima != null) {                                                              //O(1)
                    c.setNumReparticao(r_mais_proxima.getNumReparticao());                                 //O(1)
                    mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c); //O(1)
                    mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));//O(1)
                }
            } else {
                if (rep_por_num_rep == r_cod_postal_assoc) {                                        //O(1)
                    mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c); //O(1)
                    mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));//O(1)
                } else {
                    if (rep_por_num_rep != null && r_cod_postal_assoc != null && rep_por_num_rep != r_cod_postal_assoc) {
                        c.setNumReparticao(r_cod_postal_assoc.getNumReparticao());     //O(1)
                        mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c); //O(1)
                        mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));//O(1)
                    } else {
                        if (rep_por_num_rep == null && r_cod_postal_assoc != null) {
                            c.setNumReparticao(r_cod_postal_assoc.getNumReparticao());      //O(1)
                            mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c); //O(1)
                            mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));//O(1)
                        } else {

                            if (rep_por_num_rep != null && r_cod_postal_assoc == null) {
                                mapaCidadaosPorNumReparticao.get(c.getNumReparticao()).add(c); //O(1)
                                mapaReparticaoPorNifCidadao.put(c.getNif(), mapaReparticaoPorNumReparticao.get(c.getNumReparticao()));//O(1)

                            }

                 }}}}}
        return added;
    }       //Total O(n)

    /*==========================================================================
    ==============================Alínea F========================*/
    /**
     * Método que recebe uma repartição, uma dada hora e min Se a hora/min forem
     * válidos então vai buscar todos as senhas atendidas por cada 10mins
     *
     * @param r Repartição a verificar a utilização
     * @param hora Hora a verificar
     * @param min Min a verificar
     * @return Mapa com um indice 0,1,2,3 para cada 10mins e com uma lista de
     * senhas atendidas
     */
    public Map<Integer, List<Senha>> conhecerUtilizacaoReparticao(Reparticao r, int hora, int min) {
        final int MIN_HORA = 9;                                         //O(1)
        final int MAX_HORA = 15;                                        //O(1)

        final int HORA_ABERTURA = 9;                                    //O(1)
        final int HORA_FECHO = 15;                                      //O(1)

        final int MIN_ABERTURA = 0;                                     //O(1)
        final int MIN_FECHO = 30;                                       //O(1)

        final int MIN_MINUTOS = 0;                                      //O(1)
        final int MAX_MINUTOS = 60;                                     //O(1)

        final int TEMPO_MEDIA_SENHAS = 10;                              //O(1)

        Map<Integer, List<Senha>> mapaUtilizacao = null;                //O(1)
        boolean valid = true;                                           //O(1)

        if (hora < HORA_ABERTURA || hora > HORA_FECHO) {                //O(1)
            valid = false;                                              //O(1)
        }
        if (valid == true && min < MIN_MINUTOS && min >= MAX_MINUTOS) { //O(1)
            valid = false;                                              //O(1)
        }
        if (valid == true && hora == MAX_HORA && min > MIN_FECHO) {     //O(1)
            valid = false;                                              //O(1)
        }

        if (valid == true) {                                            //O(1)
            int horasEmMinutos = Math.abs(hora - HORA_ABERTURA) * 60;   //O(1)
            int nrFilas = (horasEmMinutos + min) / TEMPO_MEDIA_SENHAS;  //O(1)
            mapaUtilizacao = r.conhecerUtilizacaoReparticao(nrFilas);   //O(n^3)
        }

        return mapaUtilizacao;                                          //O(1)

    }                                                                   //Total O(n^3)

    /*==========================================================================
    ==============================Alínea G========================*/
    /**
     * Método que determina a procura dos serviços geral das repartições
     *
     * @return Mapa com o serviço e a quantidade de senhas geral associada a
     * esse serviço
     */
    public List<ProcuraPorServico> determinarServicosComMaiorProcura() {
        ListIterator<Reparticao> it = registoReparticao.listIterator();         //O(1)
        Map<Character, Integer> mapaNumeroSenhasPorServico = new HashMap<>();   //O(1)
        while (it.hasNext()) {                                                  //O(n) * O(n) * O(n)
            Reparticao r = it.next();                                           //O(1)
            Map<Character, Integer> outroMapa = r.determinarProcura();          //O(n)
            if (!outroMapa.isEmpty()) {                                         //O(1)
                for (Character ch : outroMapa.keySet()) {                       //O(n)
                    if (mapaNumeroSenhasPorServico.containsKey(ch)) {           //O(1)
                        int cont = mapaNumeroSenhasPorServico.get(ch);          //O(1)
                        cont += outroMapa.get(ch);                              //O(1)
                        mapaNumeroSenhasPorServico.put(ch, cont);               //O(1)
                    } else {
                        int cont = outroMapa.get(ch);                           //O(1)
                        mapaNumeroSenhasPorServico.put(ch, cont);               //O(1)
                    }
                }
            }
        }                                                                       //O(n^3)    
        List<ProcuraPorServico> listaProcuraServico = new ArrayList<>();           //O(1)
        for (Character ch : mapaNumeroSenhasPorServico.keySet()) {              //O(n)
            ProcuraPorServico ps = new ProcuraPorServico(ch, mapaNumeroSenhasPorServico.get(ch));//O(1)
            listaProcuraServico.add(ps);                                        //O(1)
        }
        Collections.sort(listaProcuraServico);                                  //O(nlogn)
        return listaProcuraServico;                                             //O(1)
    }                                                                           //Total : O(n^3)

    /*==========================================================================
    ============================Métodos de Obter========================*/
    /**
     * Método que obtém uma repartição associada a um cidadão
     *
     * @param nif Nif do Cidadão associado à repartição
     * @return Repartição ou Null
     */
    public Reparticao obterReparticaoAssociadaNif(Long nif) {
        if (mapaReparticaoPorNifCidadao.containsKey(nif)) { //O(1)
            return mapaReparticaoPorNifCidadao.get(nif);    //O(1)
        }
        return null;                                        //O(1)
    }


    /*==========================================================================
    ============================Métodos Utilitários e Testes========================*/
    public List<Reparticao> obterListaReparticoes() {
        List<Reparticao> lista = new ArrayList<>();                     //O(1)
        ListIterator<Reparticao> it = registoReparticao.listIterator(); //O(1)
        while (it.hasNext()) {                                          //O(n)
            lista.add(it.next());                                       //O(1)
        }
        return lista;                                                   //O(1)
    }                                                               //Total O(n)

    /**
     * Retorna uma Lista de Cidadãos associados a uma repartição Para questões
     * de teste
     *
     * @param r Repartição
     * @return Lista de Cidadãos
     */
    public List<Cidadao> obterCidadaosAssociadosAReparticao(Reparticao r) {
        List<Cidadao> lista = new ArrayList<>();                                //O(1)
        if (!mapaCidadaosPorNumReparticao.get(r.getNumReparticao()).isEmpty()) {//O(1)
            for (Cidadao c : mapaCidadaosPorNumReparticao.get(r.getNumReparticao())) {//O(n)
                lista.add(c);                                                   //O(1)
            }
        }
        return lista;                                                           //O(1)
    }                                                                           //Total O(n)

    /**
     * Retorna uma Reparticao através do código postal associado Para questão de
     * teste
     *
     * @param codPostal CodPostal
     * @return Lista de Cidadãos
     */
    public Reparticao obterReparticaoPorCodigoPostal(String codPostal) {
        return mapaReparticaoPorCodigoPostal.get(codPostal);                                                       //O(1)
    }                                                                           //Total O(1)

    /**
     * Retorna uma lista de cidadadãos através do código postal associado Para
     * questão de testes
     *
     * @param r
     * @return
     */
    public List<Cidadao> obterCidadaosPorCodigoPostal(String codPostal) {
        List<Cidadao> lista = registoCidadao.obterCidadaosPorCodigoPostal(codPostal);
        return lista;
    }

}
