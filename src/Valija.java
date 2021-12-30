/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public class Valija extends Entrega{
    
    private String material;

    public Valija(String material, int codigo, double peso) {
        super(codigo, peso);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
    @Override
    public double calcularValor() {
        if (material.equals("Cuero")){
            return 200.0*(getPeso()/1000)*150;
        }
        else if (material.equals("Plástico")){
            return 150.0*(getPeso()/1000)*150;
        }
        else if (material.equals("Tela")){
            return 100.0*(getPeso()/1000)*150;
        }
        else{
            return -1;
        }
    }
    
}
