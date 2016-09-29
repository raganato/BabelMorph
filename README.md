# BabelMorph

BabelMorph: a Multilingual Morphological Library

Stay tuned for the release of BabelMorph in other languages! 

BabelMorph contains the inflection from the [UniMorph project](http://ckirov.github.io/UniMorph/)!
The new index is UniMorphoIndex-v1.0, you just have to change the path in the babelmorph.properties file (inside config) to use it. The index contains word inflections for 166 languages, covering nouns, verbs and adjectives.  

1. Introduction
=========================

This package provides the Java API of BabelMorph, an open-source API for retrieving word inflections for nouns, verbs and adjectives based on Wiktionary.

2. Requirements
=========================

- Java 7 (JRE 1.7) or higher

3. Quick start
=========================

The following is a usage example (you can find and run it in the example package).

	//the entry point to access  the  content  available
	BabelMorph bm = BabelMorph.getInstance();
	
	String lemma = "président";
	System.out.println("morphology from the lemma: \""+lemma+"\"");
	List<BabelMorphWord> bmwFromLemma = bm.getMorphologyFromLemma(Language.FR, lemma);
	System.out.println("Lemma\tLang\tPoS\tinflected forms");
	for(BabelMorphWord bmw : bmwFromLemma)
		System.out.println(bmw);
		
	String word = "président";
	System.out.println("morphology from the word: \""+word+"\"");
	List<BabelMorphWord> bmwFromWord = bm.getMorphologyFromWord(Language.FR, word);
	System.out.println("Lemma\tLang\tPoS\tinflected forms");
	for(BabelMorphWord bmw : bmwFromWord)
		System.out.println(bmw);

Where:

`BabelMorphWord` contains a lemma associated with a language, a part-of-speech and a multi-map representing the various morphological inflections of the given lemma.

4. Stats 
=========================

Number of lemma and words divided by parts of speech.

| Language | Lemma | Nouns | Verbs | Adjectives | Words | Nouns | Verbs | Adjectives |
| -------  | ----- | ----- | ----- | ---------- | ----- | ----- | ----- | ---------- |
| Afrikaans | 1 216 | 895| 196  |125  | 3 271 |  2 049 | 768 | 454 |
| English | 231 157 | 162 375 | 25 660  |43 122| 565 431 | 330 003 | 104 951 | 130 477 |
| Esperanto | 9 905 |8 129 |  1 770 | 6 |103 113 | 30 746 | 72 343 | 24 |
| French | 50 918 |28 453 |  11 107 |11 358 |449 028 | 62 877 | 323 739 | 62 412 |
| Hebrew | 5 085 |3 297 |  1 100 | 688| 53 187 | 10 912 | 38 856 | 3 419 |
| Ido | 3 809 | 2 500|  1 309 |0|50 001 | 5 000 | 45 001 | 0 |
| Italian | 83 056 |45 764 | 16 874  |20 418 |608 164 | 96 979 | 441 620 | 69 565 |
| Polish | 11 321 |8 104 | 1 396  | 1 821| 228 516 | 100 813 | 68 632 | 59 071 |
| Portuguese | 48 672 | 29 633| 7 826  | 11 213| 583 527 | 63 958 | 477 799 | 41 770 | 
| Spanish | 43 838 |27 680 | 6 701  | 9 457| 529 350 | 63 382 | 431 023 | 34 945 |
| Swedish | 13 159 |8 922 | 2 323  | 1 914|117 272 | 73 771 | 25 852 | 17 649 |
| Welsh | 1 578 |1 134 | 202  | 242|23 460 | 2 375 | 20 076 | 1 009 |

5. License
=========================

BabelMorph and its API are licensed under a Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. 

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;

For more information please contact:

> raganato atsign di (dot) uniroma1 (dot) it
