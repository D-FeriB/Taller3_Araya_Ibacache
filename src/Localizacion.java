/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public class Localizacion {
    private String nombre;
    private Entregas entregas;
    private int enviadas;
    private int recibidas;

    public Localizacion(String nombre) {
        this.nombre = nombre;
        enviadas = 0;
        recibidas = 0;
        entregas = new Entregas();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEnviadas() {return enviadas;}
    public int getRecibidas() {return recibidas;}
    
    public void aumentarEnviadas(){enviadas++;}
    public void aumentarRecibidas(){recibidas++;};
    
    public Entregas getEntregas(){
        return entregas;
    }
}
