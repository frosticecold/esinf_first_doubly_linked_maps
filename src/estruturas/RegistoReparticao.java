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
        if (dll.isEmpty()) {                                        //O(1)
            return dll.addLast(r);                                  //O(1)

        } else {
            boolean existe = verificarSeReparticaoJaExiste(r);      //O(n)
            boolean repetido = verificarCodigoPostalRepetido(r);    //O(n)
            if (existe == false && repetido == false) {             //O(1)
                return dll.addLast(r);                              //O(1)
            }
        }
        return false;                                               //O(1)
    }                                                               //Total O(n)
    
    /**
     * Remove uma repartição da lista de repartições
     *
     * @param r Repartição a remover
     * @return true or false
     */
    public boolean removerReparticao(Reparticao r) {        
        if (dll.isEmpty()) {                                        //O(1)
            return false;                                           //O(1)
        } else {
            ListIterator<Reparticao> it = dll.listIterator();       //O(1)
            while (it.hasNext()) {                                  //O(n)
                Reparticao oRep = it.next();                        //O(1)
                if (r.equals(oRep)) {                               //O(1)
                    it.remove();                                    //O(1)
                    return true;                                    //O(1)
                }
            }
        }
        return false;                                               //O(1)
    }                                                               //Total O(n)

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
        boolean existe = false;                                  //O(1)       
        ListIterator<Reparticao> it = dll.listIterator();        //O(1)
        while (it.hasNext()) {                                   //O(n)
            Reparticao rep = it.next();                          //O(1)
            if (r.equals(rep)) {                                 //O(1)
                existe = true;                                   //O(1)
                break;                                           //O(1)
            }
        }
        return existe;                                           //O(1)
    }                                                            //Total O(n)

    private boolean verificarCodigoPostalRepetido(Reparticao r) {
        boolean repetido = false;                               //O(1)
        String cod_rep1 = r.getCodigoPostal();                  //O(1)
        ListIterator<Reparticao> it = dll.listIterator();       //O(1)
        while (it.hasNext()) {                                  //O(n)
            Reparticao rep2 = it.next();                        //O(1)
            String cod_rep2 = rep2.getCodigoPostal();           //O(1)
            if (cod_rep1.equals(cod_rep2)) {                    //O(1)
                repetido = true;                                //O(1)
                break;                                          //O(1)
            }
        }
        return repetido;                                        //O(1)
    }                                                           //Total O(n)

}
