package pert.methode;
import java.util.ArrayList;

public class Etat{
	public static ArrayList<String> nom_tache = new ArrayList<String>();
	public static ArrayList<String> ant = new ArrayList<String>();
	public static ArrayList<Integer> duree = new ArrayList<Integer>();
	public static ArrayList<Integer> dto = new ArrayList<Integer>();
	public static ArrayList<Integer> dta = new ArrayList<Integer>();
	public static ArrayList<Integer> fto = new ArrayList<Integer>();
	public static ArrayList<Integer> fta = new ArrayList<Integer>();
	public static ArrayList<Integer> mirage = new ArrayList<Integer>();
	public int obj = 0;
             String[][] array_info;
	
	public Etat(String nom, int dure, String a){
		//nom tache
		nom_tache.add(nom);
		//anteriorite
		ant.add(a);
		//duree
		duree.add(dure);
		//dfo
		dto.add(0);
		//dfa
		dta.add(0);
		//fto
		fto.add(0);
		//fta
		fta.add(0);
		//mirage
		mirage.add(0);
	}
             public void clear_object(){
                          nom_tache.clear();
		//anteriorite
		ant.clear();
		//duree
		duree.clear();
		//dfo
		dto.clear();
		//dfa
		dta.clear();
		//fto
		fto.clear();
		//fta
		fta.clear();
		//mirage
		mirage.clear();
                          this.obj=0;
                          this.array_info=null;
             }
	public String getNomTache(int i){
		return nom_tache.get(i);
	}
	public String getAnt(int i){
		return ant.get(i);
	}
	public int getDuree(int i){
		return duree.get(i);
	}
	public int getMirage(int i){
		return mirage.get(i);
	}
	public int getDTO(int i){
		return dto.get(i);
	}
	public int getDTA(int i){
		return dta.get(i);
	}
	public int getFTO(int i){
		return fto.get(i);
	}
	public int getFTA(int i){
		return fta.get(i);
	}		

	public void setMirage(int i, int m){
		mirage.set(i, m);
	}
	public void setDTO(int i, int d){
		dto.set(i, d);
	}
	public void setDTA(int i, int d){
		dta.set(i, d);
	}
	public void setFTO(int i, int f){
		fto.set(i, f);
	}
	public void setFTA(int i, int f){
		fta.set(i, f);
	}
	// etats entrer (predecesseur)
	public String etats_entrer(int j){
		String s = "";
		String spl[] = getAnt(j).split(",");
		if(getAnt(j).equals("-")){
			s = "0";
		}else{
			for (int i = 0; i < spl.length; i++) {
				if(spl.length == 1){
					s = s + spl[i];				
				}else{
					// ex "AHB"    B anterierite = "-" ne peut pas somme 
					if(!getAnt(get_i(spl[i])).equals("-")){
						s = s + spl[i];							
					}	
				}
			}
		}
		return s;
	}
	// get id tache numero ligne
	public int get_i(String nom){
		return nom_tache.indexOf(nom);
	}
	// etat_sortie (acsesseur)
	public String etats_sortie(int j){
		String sort = "", split[];
		// if array les taches fin contains id tache
		if(etat_fin().contains(j)){
			sort = "FIN";
		}else{
			for (int i = 0; i < obj; i++) {
				// if nomtache sortie qui vous rechercher dans tous les anterieures motasawiin
				// l'anterier == 1
				if(getAnt(i).equals(getNomTache(j))){
					sort = sort + getNomTache(i);
				}else if(getAnt(i).length() > 1){
					// l'anterier > 1
					split = getAnt(i).split(",");
					for(int k= 0 ; k<split.length ; k++){
						if(getNomTache(j).equals(split[k])){
							// if nomtache sortie qui vous rechercher dans tous les anterieures motasawiin
							// if tache != etats initial ex: R 
							if(!getAnt(get_i(split[k])).equals("-")){
								sort = sort + getNomTache(i);
							}
						}
					}
				}
			}
		}
		return sort;
	}
	// get les taches fin
	public ArrayList<Integer> etat_fin(){
		ArrayList<Integer> fin = new ArrayList<Integer>();
		ArrayList<String> array_separer = new ArrayList<String>();
		String e, ante, split[];
		for (int i = 0; i < obj; i++) {
			ante = getAnt(i);
			split = ante.split(",");
			for(int j=0; j<split.length; j++){
				array_separer.add(split[j]);
			}
		}
		for (int i = 0; i < obj; i++) {
			e = getNomTache(i);
			if(!array_separer.contains(e)){
				fin.add(get_i(e));
			}
		}
		return fin;
	}
	// calculer DTO
	public int cal_DTO(int i){
		int dto = 0, j = 0, r=0, m=0, fto = 0;
		String entrer, spl1 , spl2;
		// entrer = tous les tache entrer dans cet tache si tache anterieur est : - alors dto = 0 
		System.out.println("======= im here ==========");
                          entrer = etats_entrer(i);
		if(entrer.equals("0")){
			setDTO(i, 0);
			dto = 0;
			// calculer FTO
			fto = cal_FTO(i);
			setFTO(i, fto);
		}else{
			if(entrer.length() == 1){
				j = get_i(entrer);
				// calculer FTO
				fto = cal_FTO(j);
				setFTO(j, fto);					
				setDTO(i, getFTO(j));
				dto = getFTO(j);
			}else if(entrer.length() > 1){
				for(int k = 0; k<entrer.length(); k++){
					if(k+1 == entrer.length()){
						break;
					}
					spl1 = String.valueOf(entrer.charAt(m));
					spl2 = String.valueOf(entrer.charAt(k+1));
					j = get_i(spl1);
					r = get_i(spl2);
					// j +r est id tache , si set tache n'est pas calculer dto et fto appeler dautre fonction pour calculer et comparer
					cal_DTO(j);
					cal_DTO(r);
					cal_FTO(j);
					cal_FTO(r);
					if(getFTO(j) >= getFTO(r)){
						m = k;
						setDTO(i, getFTO(j));
						dto = getFTO(j);
						// calculer  FTO
						fto = cal_FTO(i);
						setFTO(i, fto);
					}else if(getFTO(r) > getFTO(j)){
						m = k+1;
						setDTO(i, getFTO(r));
						dto = getFTO(r);
						// calculer FTO
						fto = cal_FTO(i);
						setFTO(i, fto);
					}
				}
			}
		}
		return dto;
	}
	// calculer FTO
	public int cal_FTO(int i){
		int fto = getDTO(i) + getDuree(i);
		setFTO(i, fto);
		return fto;
	}
	// calculer DTA
	public int cal_DTA(int i){
		int dta = getFTA(i) - getDuree(i); 
		setDTA(i, dta);
		return dta;
	}
	// calculer FTA
	public void cal_FTA(int k){
		int fta = 0, fin = 0, fta1, fta2, max =0, min=0;
		// sort table pour tout id la sortie   
		String sort[] = new String[obj];
		String split[];
		for (int j = 0; j < obj; j++) {
			sort[j] = etats_sortie(j);	
			if(etats_sortie(j) == "FIN"){
				fin = fin + 1; 
			}
		}		
		if(sort[k] == "FIN"){
			if(fin == 1){
				fta = getFTO(etat_fin().get(0));
				setFTA(k, fta);				
				setDTA(k, cal_DTA(k));
			}else if(fin > 1){
				for(int j = 0; j<etat_fin().size(); j++){
					if(j+1 == etat_fin().size()){
						break;
					}
					fta1 = getFTO(etat_fin().get(max));
					fta2 = getFTO(etat_fin().get(j+1));
					if(fta1 >= fta2){
						max = j;
						fta = fta1;
						setFTA(k, fta);
						setDTA(k, cal_DTA(k));
					}else if(fta1 < fta2){
						max = j+1;
						fta = fta2;
						setFTA(k, fta);
						setDTA(k, cal_DTA(k));
					}
				}
			}
		}else{
			if(sort[k].length() == 1){
				cal_FTA(get_i(sort[k]));
				fta = getDTA(get_i(sort[k]));
				setFTA(k, fta);
				setDTA(k, cal_DTA(k));		
			}else{
				split = sort[k].split("");
				for (int i = 0; i < split.length; i++) {
					if(i+1 == sort[k].length()){
						break;
					}
					// calculer fta les tache precedent si n'est pas calculer
					cal_FTA(get_i(split[min]));
					cal_FTA(get_i(split[i+1]));
					fta1 = getDTA(get_i(split[min]));
					fta2 = getDTA(get_i(split[i+1]));
					if(fta1 >= fta2){
						min = i;
						fta = fta2;
						setFTA(k, fta);
						setDTA(k, cal_DTA(k));
					}else if(fta1 < fta2){
						min = i+1;
						fta = fta1;
						setFTA(k, fta);
						setDTA(k, cal_DTA(k));
					}
				}
			}
		}	
	}
	// calculer Mirage
	public void mirage_etat(){
		int mirage = 0;
		for (int i = 0; i < obj; i++) {
			mirage = getDTA(i) - getDTO(i);
			setMirage(i, mirage);		
		}
	}
             public String[][] get_matrice(){
                 
                 array_info=new String[obj][7];
                 
                 for(int i=0;i<obj;i++){
                     array_info[i][0]=nom_tache.get(i);
                     array_info[i][1]=String.valueOf(duree.get(i));
                     array_info[i][2]=String.valueOf(dto.get(i));
                     array_info[i][3]=String.valueOf(fto.get(i));
                     array_info[i][4]=String.valueOf(dta.get(i));
                     array_info[i][5]=String.valueOf(fta.get(i));
                     array_info[i][6]=String.valueOf(mirage.get(i));     
                 }
                 return array_info;
             }
	// affichage tous les etats
	public void affichage(){
		for (int i = 0; i < obj; i++) {
			System.out.print("Tache :"+nom_tache.get(i)+" ,la durأ©e = "+duree.get(i));
			System.out.print(" ,DTO = "+dto.get(i)+" ,FTO = "+fto.get(i)+" ,DTA = "+dta.get(i)+" ,FTA = "+fta.get(i));
			System.out.println(" ,mirage = "+mirage.get(i));
		}
	}
	// calculer plus couts chemin
	public String plus_cout_chemin(){
		String c = "";
		for(int i = 0; i<obj; i++){
			if(getMirage(i) == 0){
				// c = les tache mirage egale 0
				c = c + getNomTache(i);				
			}
		}		
		return c;
	}
	// calculer duree plus court chemin
	public int some_duree(){
		int somme = 0, g = 0;
		String c = "";
		for(int i = 0; i<obj; i++){
			if(getMirage(i) == 0){
				// c = les tache mirage egale 0
				c = c + getNomTache(i);				
			}
		}
		// par exemple c = ABFC
		for (int i = 0; i < c.length(); i++) {
			g = get_i(String.valueOf(c.charAt(i)));
			somme = somme + getDuree(g);
		}
		return somme;
	}
}
