/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.Cidadao;
import model.Reparticao;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class RegistoCidadao implements Iterable<Cidadao> {

    /**
     * Lista de cidadãos única
     */
    private Set<Cidadao> setCidadao;

    /**
     * Construtor que cria um RegistoCidadão
     */
    public RegistoCidadao() {
        setCidadao = new HashSet<>();
    }

    /**
     * Adiciona um cidadão ao setCidadao Verifica se já existe, Senão existir
     * adiciona
     *
     * @param c Cidadao a adicionar
     * @return true or false
     */
    public boolean adicionarCidadao(Cidadao c) {
        if (!setCidadao.contains(c)) {
            return setCidadao.add(c);
        }
        return false;
    }

    /**
     * Iterador do setCidadao
     *
     * @return
     */
    @Override
    public Iterator<Cidadao> iterator() {
        return setCidadao.iterator();
    }

    /**
     * Método que percorre o registo de cidadãos e devolve uma lista com os
     * cidadãos com o mesmo código postal da repartição passada por parâmetro
     *
     * @param r Repartição com o código postal a procurar
     * @return Lista de cidadãos que deveriam pertencer a essa repartição
     */
    public List<Cidadao> obterCidadaosPorCodigoPostal(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = new ArrayList<>();
        final String DEL = "-";
        final int C_PREFIXO = 0;
        for (Cidadao c : setCidadao) {
            String codPostalPrefixo = c.getCodigoPostal().split(DEL)[C_PREFIXO];
            if (codPostalPrefixo.equals(r.getCodigoPostal())) {
                listaCidadaosPorCodPostal.add(c);
            }
        }
        Collections.sort(listaCidadaosPorCodPostal);
        return listaCidadaosPorCodPostal;
    }
}
