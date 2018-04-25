package ueb05;

import java.util.*;

class CorpusAnalyzer {
	private List<String> theses;

	/**
	 * @param thesesIterator
	 */
	CorpusAnalyzer(Iterator<String> thesesIterator) {
		// TODO Alle Titel in die this.theses Liste übernehmen
        while(thesesIterator.hasNext()){
            theses.add(thesesIterator.next());
        }
	}

	/**
	 * Gibt die Anzahl der angefertigten Theses zurück
	 */
	int countTheses() {
		return theses.size();
	}

	/**
	 * Gibt die durchschnittliche Länge von Titeln in Worten zurück
	 */
	int averageThesisTitleLength() {
		int n = 0;
		for(String s : theses){
		    n += s.split(" ").length;
        }

        return n / theses.size();
	}

	/**
	 * Gibt eine alphabetisch absteigend sortierte und duplikatfreie
	 * Liste der ersten Wörter der Titel zurück.
	 */
	List<String> uniqueFirstWords() {
		Set<String> hset = new HashSet<>();

		for(String s : theses){
			hset.add(s.split(" ")[0]);
		}

		List<String> list = new LinkedList<>();
		list.addAll(hset);
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) { //absteigend sortieren, o1.compareTo(02) ist aufsteigend sortieren
				return (o2.compareTo(o1));
			}
		});
		return list;
	}

	/**
	 * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
	 * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
	 */
	Iterator<String> censoredIterator(Set<String> blackList) {
		return new Iterator<String>() {
			Iterator<String> it = theses.iterator();

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public String next() {
				String s = it.next();
				for(String a : blackList){
					StringBuilder sb = new StringBuilder();
					int n = a.length();
					while (n-- > 0)
						sb.append(a);

					s = s.replaceAll(a, "*");
				}
				return s;
			}
		};
	}

	/**
	 * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
	 * wie sie in der Map abgebildet werden.
	 */
	List<String> normalizedTheses(Map<String, String> replace) {
		throw new UnsupportedOperationException();
	}
}
