/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public class Cliente {
    private String rut;
    private String nombre;
    private String apellido;
    private int saldo;
    private Localizacion loc;
    private Entregas realizadas;
    private Entregas enviadas;

    public Cliente(String rut, String nombre, String apellido, int saldo) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.realizadas = new Entregas();
        this.enviadas = new Entregas();
    }

    public Entregas getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(Entregas realizadas) {
        this.realizadas = realizadas;
    }

    public Entregas getEnviadas() {
        return enviadas;
    }

    public void setEnviadas(Entregas enviadas) {
        this.enviadas = enviadas;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Localizacion getLoc() {
        return loc;
    }

    public void setLoc(Localizacion loc) {
        this.loc = loc;
    }
    
    
    
}
