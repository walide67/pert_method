/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pert.methode;
import java.util.ArrayList;

public class Separation {
	public static ArrayList<String> nomTache = new ArrayList<String>();
	public static ArrayList<String> duree = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> anteriorite = new ArrayList<ArrayList<String>>();
	public int obj = 0;
	
	public Separation(String nom, String dure, String ant){
		nomTache.add(nom);
		duree.add(dure);
		anteriorite.add(s_anteroite(ant));
	}
             public void clear_object(){
                 nomTache.clear();
		duree.clear();
		anteriorite.clear();
                          this.obj=0;
             }
	
	public ArrayList<String> s_anteroite(String a){
                             a = a.replace(" ", ",");
                             a = a.replace(".", ",");
		String []s = a.split(",");
		ArrayList<String> ant = new ArrayList<String>();
		for (int j = 0; j < s.length; j++) {
			ant.add(s[j]);
		}
		return ant;		
	}
	// get les nom taches
	public String getNomTache(int n){
		return nomTache.get(n);
	}
	//get les duree
	public int getDuree(int n){
		return Integer.parseInt(duree.get(n));
	}
	// get les Anteriorite
	public String getAnterior(int n){
		String s = "";
		for(int i = 0; i<anteriorite.get(n).size(); i++){
			if(anteriorite.get(n).size() - i != 1){
				s = s + anteriorite.get(n).get(i).toString() + ",";
			}else{
				s = s + anteriorite.get(n).get(i).toString();
			}
		}
		return s;		
	}
	// Information sur la tache
	public String info_tache(String nom){
		String s = "";
		for(int i=0; i<obj; i++){
			if(nomTache.get(i).equals(nom)){
				s = "Tache : "+getNomTache(i)+" et la durأ©e : "+getDuree(i)+" et l'anterioritأ© : "+getAnterior(i);
			}
		}
		return s;
	}
	// afiche tous les taches fichier Test.txt
	public void affichageTaches(){
		for (int j = 0; j < obj; j++) {
			System.out.println("Tache : " + getNomTache(j) + " et le durأ©e : " + getDuree(j) + " et l'anteriorite est : " + getAnterior(j));
		}		
	}
}

