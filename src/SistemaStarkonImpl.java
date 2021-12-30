/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class SistemaStarkonImpl implements SistemaStarkon{

    public ArrayList<Localizacion> localizaciones;
    public LinkedList<Cliente> clientes;
    public Entregas entregas;
    
    public SistemaStarkonImpl(){
        localizaciones = new ArrayList<Localizacion>();
        clientes = new LinkedList<Cliente>();
        entregas = new Entregas();
    }
    
    @Override
    public void agregarCliente(String rut, String nombre, String apellido, int saldo, String loc) {
        Cliente c = new Cliente(rut, nombre, apellido, saldo);
        c.setLoc(buscarLoca(loc));
        clientes.add(c);
    }

    @Override
    public void agregarLocalizacion(String nombre) {
        localizaciones.add(new Localizacion(nombre));
    }

    @Override
    public void agregarDocumento(String remitente, String destinatario,double grossor, int codigo, double peso) {
        Entrega e = new Documento(grossor,codigo,peso);
        entregas.ingresar(e);
        asociarEntregaClientes(remitente, destinatario, e);
    }

    @Override
    public void agregarEncomienda(String remitente, String destinatario,double largo, double ancho, double profundidad, int codigo, double peso) {
        Entrega e = new Encomienda(largo, ancho, profundidad, codigo, peso);
        entregas.ingresar(e);
        asociarEntregaClientes(remitente, destinatario, e);
    }

    @Override
    public void agregarValija(String remitente, String destinatario,String material, int codigo, double peso) {
        Entrega e = new Valija(material, codigo, peso);
        entregas.ingresar(e);
        asociarEntregaClientes(remitente, destinatario, e);
    }

    @Override
    public void asociarEntregaClientes(String remitente, String destinatario, Entrega e) {
        Cliente r = buscarCliente(remitente);
        Cliente d = buscarCliente(destinatario);
        if (r!=null && d!=null){
            e.setRemitente(r);
            e.setDestinatario(d);
            r.getRealizadas().ingresar(e);
            d.getEnviadas().ingresar(e);
            d.getLoc().aumentarRecibidas();
            r.getLoc().aumentarEnviadas();
            r.getLoc().getEntregas().ingresar(e);
        }
        else{
            throw new NullPointerException("No existe el cliente");
        }
    }

    @Override
    public boolean checkearCliente(String rut) {
        if (buscarCliente(rut)==null){
            return false;
        }
        return true;
    }

    @Override
    public int realizarEntrega(String remitente, String destinatario, double peso, double grossor, double[] medidas, String material) {
        Cliente r = buscarCliente(remitente);
        Cliente d = buscarCliente(destinatario);
        if (r!=null && d!=null){
            int codigo = 0;
            for (int i = 0; i < 6; i++) {
                if (i==0){
                    codigo +=  Math.random()*10;
                    System.out.println(codigo);
                }
                else{
                    codigo += (Math.random()*10)*Math.pow(10, i);
                }
            }
            if (grossor==0 && material==null){
                //Encomienda
                if (peso<=50000){
                    double largo = medidas[0];
                    double ancho = medidas[1];
                    double profu = medidas[2];
                    if (largo<120 && ancho <80 && profu<80){
                        Entrega encomienda = new Encomienda(largo,ancho,profu,codigo,peso);
                        double valor = encomienda.calcularValor();
                        if (r.getSaldo()>=valor){
                            r.setSaldo(r.getSaldo()-(int)valor);
                            asociarEntregaClientes(remitente, destinatario, encomienda);
                            entregas.ingresar(encomienda);
                            return (int)valor;
                        }
                        else{
                            throw new NullPointerException("El usuario "+r.getNombre()+" "+r.getApellido()+" no tiene saldo suficiente.");
                        }
                    }
                    else{
                        throw new NullPointerException("Entrega no cumple los requisitos para ser enviada. (Encomienda)");
                    }
                }
                else{
                    throw new NullPointerException("Entrega no cumple los requisitos para ser enviada. (Encomienda)");
                }
            }
            else if(material==null){
                //Documento
                if (peso<=1500 && grossor<=50){
                    Entrega encomienda = new Documento(grossor, codigo, peso);
                    double valor = encomienda.calcularValor();
                    if (r.getSaldo()>=valor){
                        r.setSaldo(r.getSaldo()-(int)valor);
                        asociarEntregaClientes(remitente, destinatario, encomienda);
                        entregas.ingresar(encomienda);
                        return (int)valor;
                    }
                    else{
                        throw new NullPointerException("El usuario "+r.getNombre()+" "+r.getApellido()+" no tiene saldo suficiente.");
                    }
                }
                else{
                    throw new NullPointerException("Entrega no cumple los requisitos para ser enviada. (Documento)");
                }
            }
            else{
                //Valija
                if (peso<=2000){
                    if (material.equals("Cuero") || material.equals("Plastico") || material.equals("Tela")){
                        Entrega encomienda = new Valija(material, codigo, peso);
                        double valor = encomienda.calcularValor();
                        if (r.getSaldo()>=valor){
                            r.setSaldo(r.getSaldo()-(int)valor);
                            asociarEntregaClientes(remitente, destinatario, encomienda);
                            entregas.ingresar(encomienda);
                            return (int)valor;
                        }
                        else{
                            throw new NullPointerException("El usuario "+r.getNombre()+" "+r.getApellido()+" no tiene saldo suficiente.");
                        }
                    }
                    else{
                        throw new NullPointerException("Entrega no cumple los requisitos para ser enviada. (Valija)");
                    }
                }
                else{
                    throw new NullPointerException("Entrega no cumple los requisitos para ser enviada. (Valija)");
                }
            }
        }
        else{
            throw new NullPointerException("El destinario no existe");
        }
    }

    @Override
    public Cliente buscarCliente(String rut) {
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()){
            Cliente c = (Cliente) it.next();
            if (c.getRut().equals(rut)){
                return c;
            }
        }
        return null;
    }
    
    public Localizacion buscarLoca(String nombre){
        Iterator<Localizacion> it = localizaciones.iterator();
        while (it.hasNext()){
            Localizacion c = (Localizacion) it.next();
            if (c.getNombre().equals(nombre)){
                return c;
            }
        }
        return null;
    }

    @Override
    public void recargarSaldo(String rut, int saldo) {
        Cliente c = buscarCliente(rut);
        if (c==null){
            throw new NullPointerException("El cliente no existe");
        }
        else{
            c.setSaldo(c.getSaldo()+saldo);
        }
    }

    @Override
    public String getEntregasTipo() {
        String text = "\t\tDocumentos\n";
        text+= entregas.getPorTipo("Documento");
        text += "\t\tEncomiendas\n";
        text+= entregas.getPorTipo("Encomienda");
        text += "\t\tValijas\n";
        text+= entregas.getPorTipo("Valija");
        return text;
    }

    @Override
    public String getEntregasLocalizacion() {
        String text = "";
        for (Localizacion l : localizaciones) {
            text+=l.getNombre()+" realizó "+l.getEnviadas()+" y recibió "+l.getRecibidas()+" envíos.\n";
        }
        return text;
    }

    @Override
    public String getEntregasClientes() {
        String text = "";
        for (Cliente cliente : clientes) {
            text += "\t\t\t"+ cliente.getRut()+"\n";
            text += "Enviadas\n";
            text += cliente.getEnviadas().toString();
            text += "Recibidas\n";
            text += cliente.getRealizadas().toString();
        }
        return text;
    }

    @Override
    public String getGanancias() {
        String text = "";
        int sum = 0;
        Iterator<Localizacion> it = localizaciones.iterator();
        while (it.hasNext()){
            Localizacion l = (Localizacion) it.next();
            int v = (int)l.getEntregas().getValores(); 
            sum += v;
            text+= l.getNombre()+" "+v+" CLP \n";
        }
        text+= "Total: "+sum;
        return text;
    }

    @Override
    public String obtenerEntregas() {
        return entregas.escritura();
    }
    
}
