package produit;

import jade.util.leap.Serializable;

public class Produit implements Serializable{
	
	public float prix;
	public String designation;
	public double livraison;
	public String etat;
	Produit(float prix, String designation, double livraison, String etat){
		prix=this.prix; 
		designation=this.designation;
		livraison=this.livraison;
		etat=this.etat;
	}

	public Produit() {
		// TODO Auto-generated constructor stub
	}
}
