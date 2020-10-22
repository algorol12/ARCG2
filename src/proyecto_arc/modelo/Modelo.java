package proyecto_arc.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megag
 */
public class Modelo {
    private int id = -1, x, y, zona, z = 0;
    private boolean estado;
    private Persona per;
    private Cliente cliente;
    private ArrayList<Thread> personas = new ArrayList<Thread>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    public Modelo(){
        
    }
    public ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(int i = 0; i < clientes.size(); i++)
            ids.add(clientes.get(i).getID());
        return ids;
    }
    
    public void CrearDatosPersona() {
        id++;
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
        per = new Persona(id,x,y,zona,estado);
        personas.add(per);
        cliente = new Cliente(id,x,y,zona,estado);
        clientes.add(cliente);
        System.out.println("se crea bien");
    }
    public int getID(int i){
        return clientes.get(i).getID();
    }
    public int getX(int i){
        return clientes.get(i).getX();
    }
    public int getY(int i){
        return clientes.get(i).getY();
    }
    public int getZona(int i){
        return clientes.get(i).getZona();
    }
    public boolean getEstado(int i){
        return clientes.get(i).getEstado();
    }
    public void ConectarConElServidor(){
        {
            personas.get(z).start();
            z++;
            System.out.println(personas.size() + "  soy z " + z);
        }
    }
    
}
class Cliente{
    private int id, x ,y, zona;
    private boolean estado;
    public Cliente(int id, int x, int y, int zona, boolean estado) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.zona = zona;
        this.estado = estado;
    } 
    public int getID (){
        return id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZona(){
        return zona;
    }
    public boolean getEstado(){
        return estado;
    }
}

class Persona extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id, x ,y, zona;
    private boolean estado;
    public Persona(int id, int x, int y, int zona, boolean estado) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.zona = zona;
        this.estado = estado;
        
        
    }   
    @Override
    public void run() {
        System.out.println("no run bien");
        try {
            sk = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            System.out.println(id + " envía saludo");
            String x1 = String.valueOf(this.x);
            dos.writeUTF(x1);
            String respuesta="";
            respuesta = dis.readUTF();
            System.out.println(id + " Servidor devuelve saludo: " + respuesta);
            
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getID (){
        return id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZona(){
        return zona;
    }
    public boolean getEstado(){
        return estado;
    }
}
