/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;

public class Encomienda extends Entrega{
    
    private double largo;
    private double ancho;
    private double profundidad;
    //todo en cm

    public Encomienda(double largo, double ancho, double profundidad, int codigo, double peso) {
        super(codigo, peso);
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    
    
    
    @Override
    public double calcularValor() {
        return ((getPeso()/1000)*largo*ancho*profundidad*50);
    }
    
}
