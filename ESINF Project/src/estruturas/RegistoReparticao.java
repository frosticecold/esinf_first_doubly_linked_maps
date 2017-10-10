/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import java.util.ListIterator;
import model.Reparticao;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class RegistoReparticao {

    private final DoublyLinkedList<Reparticao> dll;

    /**
     * Construtor para um objeto do tipo RegistoReparticao
     */
    public RegistoReparticao() {
        dll = new DoublyLinkedList<>();
    }

    /**
     * Retorna o iterator da doublylinklist
     *
     * @return Iterator<Reparticao>
     */
    public ListIterator<Reparticao> listIterator() {
        return dll.listIterator();
    }

    /*==========================================================================
    ==============================Métodos Public========================*/
    /**
     * Método que adiciona uma repartição à lista de repartições Caso esta não
     * seja repetida na lista e Caso não exista outra repartição com o mesmo
     * código postal
     *
     * @param r
     * @return
     */
    public boolean adicionarReparticao(Reparticao r) {
        if (dll.isEmpty()) {
            return dll.addLast(r);

        } else {
            boolean existe = verificarSeReparticaoJaExiste(r);
            boolean repetido = verificarCodigoPostalRepetido(r);
            if (existe == false && repetido == false) {
                return dll.addLast(r);
            }
        }
        return false;
    }

    /**
     * Remove uma repartição da lista de repartições
     *
     * @param r Repartição a remover
     * @return true or false
     */
    public boolean removerReparticao(Reparticao r) {
        if (dll.isEmpty()) {
            return false;
        } else {
            ListIterator<Reparticao> it = dll.listIterator();
            while (it.hasNext()) {
                Reparticao oRep = it.next();
                if (r.equals(oRep)) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna quantas repartições estão guardadas
     * @return 
     */
    public int size() {
        return dll.size();
    }

    /*==========================================================================
    ==============================Métodos Private========================*/
    private boolean verificarSeReparticaoJaExiste(Reparticao r) {
        boolean existe = false;
        ListIterator<Reparticao> it = dll.listIterator();
        while (it.hasNext()) {
            Reparticao rep = it.next();
            if (r.equals(rep)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    private boolean verificarCodigoPostalRepetido(Reparticao r) {
        boolean repetido = false;
        final int C_COD_POSTAL = 0;
        final String DEL = "-";
        String cod_rep1 = r.getCodigoPostal().split(DEL)[C_COD_POSTAL];
        ListIterator<Reparticao> it = dll.listIterator();
        while (it.hasNext()) {
            Reparticao rep2 = it.next();
            String cod_rep2 = rep2.getCodigoPostal().split(DEL)[C_COD_POSTAL];
            if (cod_rep1.equals(cod_rep2)) {
                repetido = true;
                break;
            }
        }
        return repetido;
    }

}
