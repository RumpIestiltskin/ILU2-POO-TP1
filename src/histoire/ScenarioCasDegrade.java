package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois Asterix = new Gaulois("Asterix", 1000);
		try {
			etal.acheterProduit(1, Asterix);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fin du test");
		}
	}
}
