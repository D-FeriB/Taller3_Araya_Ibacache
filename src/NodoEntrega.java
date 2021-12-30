/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public class NodoEntrega {
    private NodoEntrega next;
    private NodoEntrega prev;
    private Entrega entrega;

    public NodoEntrega(Entrega entrega) {
        this.entrega = entrega;
        next = null;
        prev = null;
    }

    public NodoEntrega getNext() {
        return next;
    }

    public void setNext(NodoEntrega next) {
        this.next = next;
    }

    public NodoEntrega getPrev() {
        return prev;
    }

    public void setPrev(NodoEntrega prev) {
        this.prev = prev;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    
}
