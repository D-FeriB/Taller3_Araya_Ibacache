/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;


public class Entregas {
    //Hola, no dice cuales referencias (first y last o last o first) por lo tanto solo lo hice con first.
    
    private NodoEntrega first;
	
	public Entregas() {
		first = null;
	}
	
	public void ingresar(Entrega j) {
            NodoEntrega n = new NodoEntrega(j);
            if (first == null) {
                    first = n;
                    first.setNext(n);
            } 
            else {
                    NodoEntrega aux = first;
                    while (aux.getNext() != first) {
                            aux = aux.getNext();
                    }
                    aux.setNext(n);
                    n.setPrev(aux);
                    n.setNext(first);
                    first.setPrev(n);
            }	
	}
	
	public Entrega buscar(int rut){
        NodoEntrega aux = first;
        do {
            aux=aux.getNext();
            } 
        while ((aux != first) && ((aux.getEntrega().getCodigo()== rut)));       
        return aux.getEntrega();
      }
	
	public String escritura(){
            String text = "";
            NodoEntrega current = first;
            // do while: ejecuta y despues pregunta
            do {
                int code = current.getEntrega().getCodigo();
                String r1 = current.getEntrega().getRemitente().getRut();
                String r2 = current.getEntrega().getDestinatario().getRut();
                if (current.getEntrega() instanceof Documento){
                    Documento d = (Documento) current.getEntrega();
                    int peso = (int) d.getPeso();
                    int gorss =(int) d.getGrossor();
                    text+=code+",D,"+r1+","+r2+","+peso+","+gorss+"\n";
                }
                else if (current.getEntrega() instanceof Encomienda){
                    Encomienda d = (Encomienda) current.getEntrega();
                    int peso = (int) d.getPeso();
                    int largo =(int) d.getLargo();
                    int ancho =(int) d.getAncho();
                    int prof =(int) d.getProfundidad();
                    text+=code+",E,"+r1+","+r2+","+peso+","+largo+","+ancho+","+prof+"\n";
                }
                else{
                    Valija d = (Valija) current.getEntrega();
                    int peso = (int) d.getPeso();
                    String m = d.getMaterial();
                    text+=code+",V,"+r1+","+r2+","+m+","+peso+"\n";
                }
                current = current.getNext();
            } while (current != first);
            return text;
        }
        
        
	@Override
	public String toString() {
            String text = "";
            NodoEntrega current = first;
            if (first==null){
                return "No hay entregas";
            }
            // do while: ejecuta y despues pregunta
            do {
                text += "Código: "+current.getEntrega().getCodigo()+"\n";
                text += "Remitente: "+current.getEntrega().getRemitente().getRut()+"\n";
                text += "Nombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                text += "Destinatario: "+current.getEntrega().getDestinatario().getRut()+"\n";
                text += "Nombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                text += "Valor de entrega: "+current.getEntrega().calcularValor()+" CLP\n\n";
                current = current.getNext();
            } while (current != first);

            return text;
	}
        
        public double getValores(){
            double suma = 0;
            NodoEntrega current = first;
            // do while: ejecuta y despues pregunta
            do {
                suma += current.getEntrega().calcularValor();
                
                current = current.getNext();
            } while (current != first);

            return suma;
        }
        
        public String getPorTipo(String tipo){
            String text = "";
            NodoEntrega current = first;
            // do while: ejecuta y despues pregunta
            do {
                if ((current.getEntrega() instanceof Documento) && tipo.equals("Documento")){
                    text += "\tCódigo: "+current.getEntrega().getCodigo();
                    text += "Remitente: "+current.getEntrega().getRemitente().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Destinatario: "+current.getEntrega().getDestinatario().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Valor de entrega: "+current.getEntrega().calcularValor()+" CLP\n\n";
                    current = current.getNext();
                }
                if ((current.getEntrega() instanceof Encomienda) && tipo.equals("Encomienda")){
                    text += "\tCódigo: "+current.getEntrega().getCodigo();
                    text += "Remitente: "+current.getEntrega().getRemitente().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Destinatario: "+current.getEntrega().getDestinatario().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Valor de entrega: "+current.getEntrega().calcularValor()+" CLP\n\n";
                    current = current.getNext();
                }
                else{
                    text += "\tCódigo: "+current.getEntrega().getCodigo();
                    text += "Remitente: "+current.getEntrega().getRemitente().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Destinatario: "+current.getEntrega().getDestinatario().getRut()+"\n";
                    text += "\tNombre: "+current.getEntrega().getRemitente().getNombre()+""+current.getEntrega().getRemitente().getApellido()+"\n";
                    text += "Valor de entrega: "+current.getEntrega().calcularValor()+" CLP\n\n";
                    current = current.getNext();
                }
                current = current.getNext();
                
            } while (current != first);

            return text;
        }
}
