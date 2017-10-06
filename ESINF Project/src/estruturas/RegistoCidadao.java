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
    
    Set<Cidadao> setCidadao;
    
    public RegistoCidadao() {
        setCidadao = new HashSet<>();
    }
    
    public boolean adicionarCidadao(Cidadao c) {
        if (!setCidadao.contains(c)) {
            return setCidadao.add(c);
        }
        return false;
    }
    
    @Override
    public Iterator<Cidadao> iterator() {
        return setCidadao.iterator();
    }
    
    public List<Cidadao> obterCidadaosPorCodigoPostal(Reparticao r) {
        List<Cidadao> listaCidadaosPorCodPostal = new ArrayList<>();
        String DEL = "-";
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
