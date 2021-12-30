/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public interface SistemaStarkon {
    public void agregarCliente(String rut, String nombre, String apellido, int saldo, String loc);
    public void agregarLocalizacion(String nombre);
    public void agregarDocumento(String remitente, String destinatario,double grossor, int codigo, double peso);
    public void agregarEncomienda(String remitente, String destinatario,double largo, double ancho, double profundidad, int codigo, double peso);
    public void agregarValija(String remitente, String destinatario,String material, int codigo, double peso);
    public void asociarEntregaClientes(String remitente, String destinatario, Entrega e);
    public boolean checkearCliente(String rut);
    /*
    Devuelve el código y el costo
    */
    public int realizarEntrega(String remitente,String destinatario,double peso,double grossor,double [] medidas,String material);
    public Cliente buscarCliente(String rut);
    public void recargarSaldo(String rut, int saldo);
    public String getEntregasTipo();
    public String getEntregasLocalizacion();
    public String getEntregasClientes();
    /*
    Por cada oficina (Localizacion.enviadas) y después de manera general (suma de Localizacion.enviadas).
    */
    public String getGanancias();
    public String obtenerEntregas();
}
