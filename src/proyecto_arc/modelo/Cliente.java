package proyecto_arc.modelo;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
class Cliente extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id, x ,y, zona;
    private boolean estado;
    public Persona(int id) {
        this.id = id;
        x = (int) (Math.random() * 1000);
        y = (int) (Math.random() * 1000);
        if (x <= 500 && y <= 500){
            zona = 1;
        }
        else if (x > 500 && y < 500){
            zona = 2;
        }
        else if (x < 500 && y > 500){
            zona = 3;
        }
        else if (x > 500 && y > 500){
            zona = 4;
        }
        if (Math.random() >= 0.3){
            estado = true;
        }
        else{
            estado = false;
        }
    }   
    @Override
    public void run() {
        try {
            sk = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            
            dos.writeInt(id);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(zona);
            dos.writeBoolean(estado);
            
            String respuesta="";
            respuesta = dis.readUTF();
            System.out.println(" Servidor devuelve : " + respuesta);
            dis.close();
            dos.close();
            sk.close();
            
            while(true){
                x = moverseX(x);
                y = moverseY(y);
                zona = comprobarZona(x, y);
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int nv = 0, xv = -1, yv = -1;
                boolean estadov = false;
                
                sk = new Socket("127.0.0.1", 10578);
                dos = new DataOutputStream(sk.getOutputStream());
                dis = new DataInputStream(sk.getInputStream());

                dos.writeInt(id);
                dos.writeInt(x);
                dos.writeInt(y);
                dos.writeInt(zona);
               
                nv = dis.readInt();
                System.out.println("Soy id " + id + "  ZONA " + zona);
                for(int i = 0; i < nv; i++){
                    xv = dis.readInt();
                    yv = dis.readInt();
                    estadov = dis.readBoolean();
                    System.out.println("La posicion de mi vecino " + i + "  es x " + xv + " es y " + yv);
                }
                
                dis.close();
                dos.close();
                sk.close();
            }
     
            
            
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int moverseX(int x){
        int z;
        z = (int) (Math.random() * 10);
        if(z <= 3 && (x + 5 < 500))
            x += 5;
        else if (z <= 6 && (x - 5 > 0))
            x -= 5;
        return x;
    }
    public int moverseY(int y){
        int w;
        w = (int) (Math.random() * 10);
        if(w <= 3 && (y + 5 < 500))
            y += 5;
        else if (w <= 6 &&(y - 5 > 0))
            y -= 5;
        return y;
    }
    public int comprobarZona(int x, int y){
        int zona;
        if (x <= 500 && y <= 500){
            zona = 1;
        }
        else if (x > 500 && y < 500){
            zona = 2;
        }
        else if (x < 500 && y > 500){
            zona = 3;
        }
        else {
            zona = 4;
        }
        return zona;
    }
}
public class Cliente {
    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new Persona(i));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
} 
