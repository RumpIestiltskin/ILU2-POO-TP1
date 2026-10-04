package histoire;

import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		}
}
