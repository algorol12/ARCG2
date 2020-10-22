package proyecto_arc.modelo;


import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author megag
 */
public class Person{
        private int id, x ,y, zona;
        private boolean vecino = false, estado;
        public Person(int id, int x, int y, int zona, boolean estado) {
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
        public boolean getVecino(){
            return vecino;
        }
        public void setZona(int zo){
            this.zona = zo;
        }
        public void setX(int x){
            this.x = x;
        }
        public void setY(int y){
            this.y = y;
        }
    }
