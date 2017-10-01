/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import model.Senha;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class PriorityBufferSenha<E extends Senha> {

    private final ArrayList<Senha> buffer;

    /**
     * Cria uma nova PriorityBufferSenha
     *
     *
     */
    public PriorityBufferSenha() {
        buffer = new ArrayList<>(); // Diamond notation: the type can be inferred by the compiler
    }

    /**
     * Adds a Senha to the buffer.Senha are added using a numeric priority
     * system, being FIFO when priorities are equal.
     *
     * @return true if Senha has been added, false otherwise
     */
    public Boolean addSenha(Senha senha) {
        int i = 0;
        while (i < buffer.size()) {
            if (buffer.get(i).compareTo(senha) < 0) {
                buffer.add(i, senha);
                return true;
            }
            i++;
        }

        return buffer.add(senha);
    }

    /**
     * Gets the next Senha in the queue.
     *
     * @return The next Senha in the queue
     */
    public Senha getSenha() {
        if (buffer.isEmpty()) {
            return null;
        }
        Senha s = buffer.get(0);
        buffer.remove(0);

        return s;
    }

    /**
     * Deletes a document from the buffer (if it exists), given a name and an
     * author
     *
     * @param num_order Number priority of a senha
     * @param code_Assunto code of senha
     * @return true if the senha existed, false otherwise
     */
    public Boolean delSenha(int num_order, char code_Assunto) {
        int i = 0;
        Iterator<Senha> it = buffer.iterator();
        while (it.hasNext()) {
            Senha s = it.next();
            if (s.getNumSenha() == num_order && s.getCodigoSenha() == code_Assunto) {
                it.remove();
                return true;
            }
        }
        return false;

    }

    /**
     * Deletes all the Senhas which are superior to a Priority
     *
     * @param priority the size above which documents will be deleted
     * @return true if there is at least one Document size
     */
    public Boolean delSenhaNumAbove(Integer priority) {
        Boolean rem = false;
        Iterator<Senha> it = buffer.iterator();
        while (it.hasNext()) {
            Senha s = it.next();
            if (s.getNumSenha() > priority) {
                it.remove();
                rem = true;
            }
        }
        return rem;
    }

    @Override
    public String toString() {
        return buffer.toString();

    }

    public Iterator<Senha> iterator() {
        return buffer.iterator();

    }
}
