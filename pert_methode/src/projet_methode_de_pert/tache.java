/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_methode_de_pert;

import java.util.ArrayList;

/**
 *
 * @author walide
 */
public class tache {
    String nom_tache;
    int duree;
    ArrayList<tache> ant_list=new ArrayList();
    int dto,dta,fta,fto,mirage;
    ArrayList<tache> all_tacche=new ArrayList(),pres=new ArrayList();
    
    public tache(String nom,int duree,ArrayList<tache> ant,ArrayList<tache> tous){
        this.nom_tache=nom;
        this.duree=duree;
        this.ant_list=ant;
        this.dta=0;
        this.dto=0;
        this.fta=0;
        this.fto=0;
        this.mirage=0;
        
        for(int i=0;i<this.ant_list.size();i++){
           this.ant_list.get(i).update_pres(this);            
            
        }
        
        
    }
    public void update_pres(tache t){
        if(!this.pres.contains(t))
        this.pres.add(t);
    }
    public void update_all_taches(){
        this.all_tacche.add(this);
    }
    public boolean is_end(ArrayList<tache> list_tache){
        boolean exist=true;
        for(int i=0;i<list_tache.size();i++){
            if(!list_tache.get(i).ant_list.isEmpty()){
            if(list_tache.get(i).ant_list.contains(this)){
               exist=false;
            }    
            }  
        }
        return exist;
    }
    public boolean is_start(){
        if(this.ant_list.isEmpty()){
            return true;
        }
        return false;
    }
    public int max_tache(ArrayList<tache> tache_list){
        int max = tache_list.get(0).fto;
        if(tache_list.size()>1){
        for(int i=1;i<tache_list.size();i++){
          if(tache_list.get(i).fto>max){
              max=tache_list.get(i).fto;
          }  
        }    
        }
        
        return max;
    }
    public int min_tache(ArrayList<tache> tache_list){
        int min=tache_list.get(0).dta;
        if(tache_list.size()>1){
        for(int i=1;i<tache_list.size();i++){
            if(min>tache_list.get(i).dta){
                min=tache_list.get(i).dta;
            }
        }    
        }
        
        return min;
    }
    public void cal_dto(){
        if(this.is_start()){
            this.dto=0;
        }else{
            this.dto= this.max_tache(this.ant_list);
        }
    }
    public void cal_fto(){
        this.fto=this.dto+this.duree;
    }
    public void cal_dta(){
        this.dta=this.fta-this.duree;
    }
    public void cal_fta(){
        if(this.is_end(all_tacche)){
            this.fta=this.fto;
        }else{
            System.out.println(this.nom_tache+" pas tache final");
            this.fta=min_tache(this.pres);
        }
    }
    public String get_ant_string(){
        String s="";
        if(this.ant_list.isEmpty()){
          s="-";  
        }else{
        for(int i=0;i<this.ant_list.size();i++){
            s=s+ant_list.get(i).nom_tache+" ";
        }    
        }
        return s;
        
    }
    public String get_pres_string(){
        String s="";
        if(this.pres.isEmpty()){
          s="-";  
        }else{
        for(int i=0;i<this.pres.size();i++){
            s=s+this.pres.get(i).nom_tache+" ";
        }    
        }
        return s;
        
    }
    public void cal_mirage(){
      this.mirage=this.dta-this.dto;  
    }
    
    
    }
    
    
    

