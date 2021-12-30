/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3.pkg202120;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Taller3202120 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SistemaStarkon sys = new SistemaStarkonImpl();
        Lectura(sys);
        Menu(sys);
    }

    private static void Lectura(SistemaStarkon sys) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("localizacion.txt"));
        while(scan.hasNext()){
            sys.agregarLocalizacion(scan.nextLine());
        }
        scan = new Scanner(new File("Cliente.txt"));
        while(scan.hasNext()){
            String [] partes = scan.nextLine().split(",");
            String rut = partes[0];
            String nombre = partes[1];
            String apellido = partes[2];
            int saldo = Integer.parseInt(partes[3]);
            String loc = partes[4];
            sys.agregarCliente(rut, nombre, apellido, saldo, loc);
        }
        scan = new Scanner(new File("Entregas.txt"));
        while(scan.hasNext()){
            String [] partes = scan.nextLine().split(",");
            if (partes.length==0){
                return;
            }
            int code = Integer.parseInt(partes[0]);
            String tipo = partes[1];
            String remitente = partes[2];
            String destinatario = partes[3];
            if (tipo.equals("D")){
                int peso = Integer.parseInt(partes[4]);
                int gros = Integer.parseInt(partes[5]);
                try {
                    sys.agregarDocumento(remitente, destinatario, gros, code, peso);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tipo.equals("E")){
                int peso = Integer.parseInt(partes[4]);
                int largo = Integer.parseInt(partes[5]);
                int ancho = Integer.parseInt(partes[6]);
                int prof = Integer.parseInt(partes[7]);
                try {
                    sys.agregarEncomienda(remitente, destinatario, largo, ancho, prof, code, peso);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tipo.equals("V")){
                int peso = Integer.parseInt(partes[5]);
                String material = partes[4];
                try {
                    sys.agregarValija(remitente, destinatario, material, code, peso);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void Menu(SistemaStarkon sys) throws IOException {
        System.out.println("Bienvenido\nIngrese su rut:");
        Scanner scan = new Scanner(System.in);
        String rut = scan.nextLine();
        if (rut.equals("Admin")){
            System.out.println("Ingresa contraseña:");
            String pass = scan.nextLine();
            if (pass.equals("choripan123")){
                menuAdmin(sys);
            }
        }
        else{
            boolean login = sys.checkearCliente(rut);
            if (login){
                menuCliente(sys,rut);
            }
        }
    }

    private static void menuAdmin(SistemaStarkon sys) throws IOException {
        System.out.println("1)Entregas por tipo\n2)Entregas por localizacion\n3)Entregas por cliente\n4)Registro de ganancia\n5)Salir\nIngresa opcion");
        Scanner scan = new Scanner(System.in);
        int op = Integer.parseInt(scan.nextLine());
        while (op!=0){
            if (op==1){
                System.out.println(sys.getEntregasTipo());
            }
            if (op==2){
                System.out.println(sys.getEntregasLocalizacion());
            }
            if (op==3){
                System.out.println(sys.getEntregasClientes());
            }
            if (op==4){
                System.out.println(sys.getGanancias());
            }
            
            if (op==5){
                System.out.println("Bye!");
                salir(sys);
                System.exit(0);
            }
            System.out.println("1)Entregas por tipo\n2)Entregas por localizacion\n3)Entregas por cliente\n4)Registro de ganancia\n5)Salir\nIngresa opcion");
            op = Integer.parseInt(scan.nextLine());
        }
    }

    private static void menuCliente(SistemaStarkon sys, String rut) throws IOException {
        System.out.println("1)Realizar Entrega \n2)Recargar saldo \n3)Salir");
        Scanner scan = new Scanner(System.in);
        int op = Integer.parseInt(scan.nextLine());
        while (op!=0){
            if (op==1){
                System.out.println("Ingresa rut de destino");
                String destino = scan.nextLine();
                System.out.println("Ingresa tipo de entrega a enviar (D,E,V): ");
                String tipo = scan.nextLine();
                if (tipo.equals("D")){
                    System.out.println("Ingresa peso:");
                    int peso = Integer.parseInt(scan.nextLine());
                    System.out.println("Ingresa grosor:");
                    int gros = Integer.parseInt(scan.nextLine());
                    try {
                        System.out.println("Valor gastado: "+sys.realizarEntrega(rut, destino, peso, gros, null, null));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (tipo.equals("E")){
                    System.out.println("Ingresa peso:");
                    int peso = Integer.parseInt(scan.nextLine());
                    double[] lit = new double[3];
                    System.out.println("Ingresa largo:");
                    lit[0] = Integer.parseInt(scan.nextLine());
                    System.out.println("Ingresa ancho:");
                    lit[1] = Integer.parseInt(scan.nextLine());
                    System.out.println("Ingresa profundidad:");
                    lit[2] = Integer.parseInt(scan.nextLine());
                    try {
                        System.out.println("Valor gastado: "+sys.realizarEntrega(rut, destino, peso, 0, lit, null));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (tipo.equals("V")){
                    System.out.println("Ingresa peso:");
                    int peso = Integer.parseInt(scan.nextLine());
                    System.out.println("Ingresa material:");
                    String material = scan.nextLine();
                    try {
                        System.out.println("Valor gastado: "+sys.realizarEntrega(rut, destino, peso, 0, null, material));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (op==2){
                System.out.println("Ingrese saldo:");
                int saldo = Integer.parseInt(scan.nextLine());
                sys.recargarSaldo(rut, saldo);
                System.out.println("Saldo actualizado");
            }
            if (op==3){
                System.out.println("Bye!");
                salir(sys);
                System.exit(0);
            }
            System.out.println("1)Realizar Entrega \n2)Recargar saldo \n3)Salir");
            op = Integer.parseInt(scan.nextLine());
        }
    }
    public static void salir(SistemaStarkon sys) throws IOException {
        FileWriter fileEntregas = new FileWriter("entregas.txt");
        PrintWriter escrituraEntregas = new PrintWriter(fileEntregas);
        escrituraEntregas.print(sys.obtenerEntregas());
        escrituraEntregas.close();
    }
}
