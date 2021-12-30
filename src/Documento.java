/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;

public class Documento extends Entrega{
    
    private double grossor;//en mm

    public Documento(double grossor, int codigo, double peso) {
        super(codigo, peso);
        this.grossor = grossor;
    }

    public double getGrossor() {
        return grossor;
    }

    public void setGrossor(double grossor) {
        this.grossor = grossor;
    }

    @Override
    public double calcularValor() {
        return ((getPeso()/1000)*grossor*100);
    }
    
}
