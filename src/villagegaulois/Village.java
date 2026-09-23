package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMaximum);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() +  " cherche un endroit pour vendre " + nbProduit + produit + ".\n");
		int indiceEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);  //ATTENTION QUAND INDICE = -1 !!
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + indiceEtal + ".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length == 0) {
			chaine.append("Personne ne vend de " + produit + "s au marché...\n");
		} else if (etalsProduit.length == 1) {
			chaine.append("Seul le vendeur " + etalsProduit[0].getVendeur() + " vend des " + produit + "s au marché.");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + "s sont :\n");
			for (int i = 0; i<etalsProduit.length ; i++) {
				chaine.append(" - " + etalsProduit[i].getVendeur().getNom() +"\n");
			}
		}
		return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		
	}
	
	
	
	private static class Marche {
		private int nbEtals;
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for (int i = 0; i < nbEtals; i++) {
				if (! etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalsProduit = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalsProduit ++;
				}
			}
			Etal[] etalsProduit = new Etal[nbEtalsProduit];
			int indiceCourant = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[indiceCourant] = etals[i];
					indiceCourant++;
				}
			}
			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsVides = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nbEtalsVides ++;
				}
			}
			if (nbEtalsVides == 0) {
				chaine.append("Il reste " + nbEtalsVides + " étals vides dans le marché.\n");
			}
			
			return chaine.toString();
			
		}
		
		
	}
	
	
}