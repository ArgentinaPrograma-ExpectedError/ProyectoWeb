package model;

import java.util.Comparator;

public class Ordenador implements Comparator<Suggestion> {

	public int compare(Suggestion primeraSugerencia, Suggestion segundaSugerencia) {
		if (primeraSugerencia instanceof Attraction && segundaSugerencia instanceof Attraction) {
			return compare2(primeraSugerencia, segundaSugerencia);
		}
		if (primeraSugerencia instanceof Promotion && segundaSugerencia instanceof Promotion) {
			return compare2(primeraSugerencia, segundaSugerencia);
		}
		if (primeraSugerencia instanceof Promotion) {
			return -1;
		} else {
			return 1;
		}
	}

	public int compare2(Suggestion primeraSugerencia, Suggestion segundaSugerencia) {
		if (primeraSugerencia.getCost().compareTo(segundaSugerencia.getCost()) == 0) {
			return primeraSugerencia.getDuration().compareTo(segundaSugerencia.getDuration()) * -1;
		}
		return primeraSugerencia.getCost().compareTo(segundaSugerencia.getCost()) * -1;
	}
}
