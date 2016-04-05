# BabelMorph

BabelMorph: a Multilingual Morphological Library

1. INTRODUCTION
=========================

This package provides the Java API of BabelMorph, an open-source API for retrieving word inflections for nouns, verbs and adjectives based on Wiktionary.

2. REQUIREMENTS
=========================

- Java 7 (JRE 1.7) or higher

3. QUICK START
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

4. LICENSE
=========================

Copyright (c) 2016 Sapienza University of Rome.
All Rights Reserved.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;

For more information please contact:

> raganato atsign di (dot) uniroma1 (dot) it
