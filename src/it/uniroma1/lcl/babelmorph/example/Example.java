package it.uniroma1.lcl.babelmorph.example;

import java.util.List;

import edu.mit.jwi.item.POS;
import it.uniroma1.lcl.babelmorph.BabelMorph;
import it.uniroma1.lcl.babelmorph.BabelMorphWord;
import it.uniroma1.lcl.babelmorph.Language;


/**
 * @author raganato
 *
 */
public class Example 
{
	
	public static void main(String[] args) throws Exception 
	{
		
		BabelMorph bm = BabelMorph.getInstance();

		String lemma = "eat";
		System.out.println("morphology from the lemma \""+lemma+"\" in "+Language.ENG);
		List<BabelMorphWord> bmwFromLemma = bm.getMorphologyFromLemma(Language.ENG, lemma);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwFromLemma)
			System.out.println(bmw);
		System.out.println();
		
		String word = "président";
		System.out.println("morphology from the word \""+word+"\" in "+Language.FRA);
		List<BabelMorphWord> bmwFromWord = bm.getMorphologyFromWord(Language.FRA, word);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwFromWord)
			System.out.println(bmw);
		System.out.println();
		
		lemma = "stockwerk";
		System.out.println("morphology from the lemma \""+lemma+"\" in "+Language.DEU);
		List<BabelMorphWord> bmwNounFromLemma = bm.getMorphologyFromLemma(Language.DEU, lemma, POS.NOUN);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwNounFromLemma)
			System.out.println(bmw);
		System.out.println();
		
		word = "vedemmo";
		System.out.println("morphology from the word \""+word+"\" in "+Language.ITA);
		List<BabelMorphWord> bmwVerbFromWord = bm.getMorphologyFromWord(Language.ITA, word, POS.VERB);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwVerbFromWord)
			System.out.println(bmw);
		System.out.println();
		
		
	}

}

