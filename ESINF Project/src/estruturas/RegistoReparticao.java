/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import java.util.ListIterator;
import model.GestaoAtendimento;
import model.Reparticao;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class RegistoReparticao {

    private DoublyLinkedList<Reparticao> dll;

    public RegistoReparticao() {
        dll = new DoublyLinkedList<Reparticao>();
    }

    public void adicionarReparticao(Reparticao r, GestaoAtendimento ga) {
        if (dll.isEmpty()) {
            dll.addLast(r);

        } else {
            boolean existe = verificarSeReparticaoJaExiste(r);
            boolean repetido = verificarCodigoPostalRepetido(r);
        }
    }

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
            Reparticao rep = it.next();
            String cod_rep2 = r.getCodigoPostal().split(DEL)[C_COD_POSTAL];
            if (cod_rep1.equals(cod_rep2)) {
                repetido = true;
                break;
            }
        }
        return repetido;
    }
}
