package proyecto_arc.modelo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.*;
public class Servidor {
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        DataOutputStream dos;
        DataInputStream dis;
        ArrayList<Person> personas = new ArrayList<Person>(); 
        try {
            ss = new ServerSocket(10578); 
            System.out.println("\t[OK]");
            int id,x,y,zona, per = 0;
            boolean estado;
            while (true) {
                Socket socket;
                socket = ss.accept();
                boolean esta = false;
                ArrayList<Person> vecinos = new ArrayList<Person>(); 
                int nv = 0;
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                
                id = dis.readInt();
                for(int i = 0; i < personas.size(); i++){
                    if(personas.get(i).getID() == id){
                        esta = true;
                        per = i;
                    }                        
                }
                if (esta == false){
                    x = dis.readInt();
                    y = dis.readInt();
                    zona = dis.readInt();
                    estado = dis.readBoolean();

                    dos.writeUTF("Me ha llegado");
                    Person persona = new Person(id,x,y,zona,estado);
                    System.out.println("Me ha llegado la persona con id: " + id);
                    personas.add(persona);
                    socket.close();
                }
                else{
                    x = dis.readInt();
                    y = dis.readInt();
                    zona = dis.readInt();
                    personas.get(per).setX(x);
                    personas.get(per).setY(y);
                    personas.get(per).setZona(zona);
                    for(int i = 0; i < personas.size(); i++){
                        if((personas.get(per).getZona() == personas.get(i).getZona()) && (per != i)){
                            nv++;
                            vecinos.add(personas.get(i));
                        }
                    }
                    dos.writeInt(nv);
                    for(int i = 0; i < vecinos.size(); i++){
                            dos.writeInt(vecinos.get(i).getX());
                            dos.writeInt(vecinos.get(i).getY());
                            dos.writeBoolean(vecinos.get(i).getEstado());
                    }
                    socket.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 
