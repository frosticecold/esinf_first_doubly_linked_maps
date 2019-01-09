/*
 */
package estruturas;

import model.Reparticao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class RegistoReparticaoTest {
    
    public RegistoReparticaoTest() {
    }

    /**
     * Test of listIterator method, of class RegistoReparticao.
     */
    @Test
    public void testListIterator() {
        System.out.println("listIterator");
        RegistoReparticao instance = new RegistoReparticao();
        boolean expResult = false;
        boolean result = instance.listIterator().hasNext();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of listIterator method, of class RegistoReparticao.
     */
    @Test
    public void testListIteratorFilled() {
        System.out.println("listIteratorFilled");
        RegistoReparticao instance = new RegistoReparticao();
        Reparticao r = new Reparticao("Porto", 0, "4000");
        Reparticao r1 = new Reparticao("Lisboa", 1, "1000");
        instance.adicionarReparticao(r);
        instance.adicionarReparticao(r1);
        boolean expResult = true;
        boolean result = instance.listIterator().hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarReparticao method, of class RegistoReparticao.
     */
    @Test
    public void testAdicionarReparticao() {
        System.out.println("adicionarReparticao");
        Reparticao r = null;
        RegistoReparticao instance = new RegistoReparticao();
        boolean expResult = true;
        boolean result = instance.adicionarReparticao(r);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of adicionarReparticao method, of class RegistoReparticao.
     */
    @Test
    public void testAdicionarReparticaoRepetida() {
        System.out.println("adicionarReparticaoRepetida");
        Reparticao r = new Reparticao("Porto", 0, "4000");
        Reparticao r1 = new Reparticao("Porto", 0, "4000");
        RegistoReparticao instance = new RegistoReparticao();
        instance.adicionarReparticao(r);
        boolean expResult = false;
        boolean result = instance.adicionarReparticao(r1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of adicionarReparticao method, of class RegistoReparticao.
     */
    @Test
    public void testAdicionarReparticaoCodPostalRepetido() {
        System.out.println("adicionarReparticaoCodPostalRepetido");
        Reparticao r = new Reparticao("Porto", 0, "4000");
        Reparticao r1 = new Reparticao("Gaia", 4, "4000");
        RegistoReparticao instance = new RegistoReparticao();
        instance.adicionarReparticao(r);
        boolean expResult = false;
        boolean result = instance.adicionarReparticao(r1);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerReparticao method, of class RegistoReparticao.
     */
    @Test
    public void testRemoverReparticao() {
        System.out.println("removerReparticao");
        Reparticao r = new Reparticao("Porto", 0, "4000");
        RegistoReparticao instance = new RegistoReparticao();
        instance.adicionarReparticao(r);
        boolean expResult = true;
        boolean result = instance.removerReparticao(r);
        assertEquals(expResult, result);
    }
    /**
     * Test of removerReparticao method, of class RegistoReparticao.
     */
    @Test
    public void testRemoverReparticaoFail() {
        System.out.println("removerReparticaoFalha");
        Reparticao r = null;
        RegistoReparticao instance = new RegistoReparticao();
        boolean expResult = false;
        boolean result = instance.removerReparticao(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class RegistoReparticao.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        RegistoReparticao instance = new RegistoReparticao();
        int expResult = 0;
        int result = instance.size();
        
        assertEquals(expResult, result);
        
        expResult = 1;
        Reparticao r = new Reparticao("Porto", 0, "4000");
        instance.adicionarReparticao(r);
        result = instance.size();
        
        assertEquals(expResult, result);
    }
    
}
