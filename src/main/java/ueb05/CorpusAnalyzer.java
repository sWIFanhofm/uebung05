package ueb05;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.repeat;

class CorpusAnalyzer {
	private List<String> theses = new LinkedList<>();

	/**
	 * @param thesesIterator
	 */
	CorpusAnalyzer(Iterator<String> thesesIterator) {
		while(thesesIterator.hasNext())
			theses.add(thesesIterator.next());
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
		for(String w : theses){
			n += w.split(" ").length;
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
		list.sort(Collections.reverseOrder());
		return list;
	}

	/**
	 * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
	 * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
	 */
	Iterator<String> censoredIterator(Set<String> blackList) {
		return new Iterator<String>() {
			Iterator<String> it;

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public String next() {
				String s = it.next();
				for(String c  : blackList)
					s = s.replaceAll(c, repeat("*", c.length()));

				return s;
			}
		};
	}

	/**
	 * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
	 * wie sie in der Map abgebildet werden.
	 */
	List<String> normalizedTheses(Map<String, String> replace) {
		List<String> normalized = new LinkedList<>();
		for(String s : theses){
			for(Map.Entry<String, String> m : replace.entrySet()){
				s = s.replaceAll(m.getKey(), m.getValue());
			}
			normalized.add(s);
		}
		normalized.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
		return normalized;
	}
}
