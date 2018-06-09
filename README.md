# BabelMorph

BabelMorph: a Multilingual Morphological Library

### Index and Configuration

BabelMorph provides an index from the data of the [UniMorph project](http://unimorph.org/).  
As first step, download the index (UniMorph.zip) from [here](https://drive.google.com/file/d/1H40HSDvBEIo_bbf-FoiC13BrWCI7-RTc/view?usp=sharing) ~124MB.  
Next, configure the file located inside the config folder (babelmorph.properties) with the path to the Morphological index.  
The index contains word inflections for **50** languages, covering nouns, verbs and adjectives.  
For more stats, please visit [https://unimorph.github.io/](https://unimorph.github.io/)  

1. Introduction
=========================

This package provides an open-source API for retrieving word inflections for nouns, verbs and adjectives based on Wiktionary.

2. Requirements
=========================

- Java 7 (JRE 1.7) or higher

3. Quick start
=========================

The following is a usage example (you can find and run it in the example package).

	//the entry point to access  the  content  available
	BabelMorph bm = BabelMorph.getInstance();
	
	String lemma = "eat";
	System.out.println("morphology from the lemma \""+lemma+"\" in "+Language.ENG);
	List<BabelMorphWord> bmwFromLemma = bm.getMorphologyFromLemma(Language.ENG, lemma);
	System.out.println("Lemma\tLang\tPoS\tinflected forms");
	for(BabelMorphWord bmw : bmwFromLemma)
		System.out.println(bmw);
	
	String word = "président";
	System.out.println("morphology from the word \""+word+"\" in "+Language.FRA);
	List<BabelMorphWord> bmwFromWord = bm.getMorphologyFromWord(Language.FRA, word);
	System.out.println("Lemma\tLang\tPoS\tinflected forms");
	for(BabelMorphWord bmw : bmwFromWord)
		System.out.println(bmw);

Where:

`BabelMorphWord` contains a lemma associated with a language, a part-of-speech and a multi-map representing the various morphological inflections of the given lemma.

4. License
=========================

BabelMorph and its API are licensed under a Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. 

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;

If you use this API, please link to [this repository](https://github.com/raganato/BabelMorph). 

If you use the UniMorph index, please cite also this paper:

> Christo Kirov; John Sylak-Glassman; Roger Que; and David Yarowsky. In press. Very-large scale parsing and normalization of Wiktionary morphological paradigms. 
> Proceedings of the 10th International Conference on Language Resources and Evaluation (LREC 2016). Portorož, Slovenia: European Language Resources Association (ELRA). 
