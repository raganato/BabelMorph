package it.uniroma1.lcl.babelmorph.example;

import it.uniroma1.lcl.babelmorph.BabelMorph;
import it.uniroma1.lcl.babelmorph.BabelMorphConfiguration;
import it.uniroma1.lcl.babelmorph.BabelMorphWord;
import it.uniroma1.lcl.babelmorph.iterator.BabelMorphWordIterator;
import it.uniroma1.lcl.jlt.util.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.HashMultimap;

import edu.mit.jwi.item.POS;


/**
 * @author raganato
 *
 */
public class Example 
{
	
	public static void main(String[] args) throws Exception 
	{
		
		BabelMorph bm = BabelMorph.getInstance();
	
		String lemma = "président";
		System.out.println("morphology from the lemma: \""+lemma+"\"");
		List<BabelMorphWord> bmwFromLemma = bm.getMorphologyFromLemma(Language.FR, lemma);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwFromLemma)
			System.out.println(bmw);
		System.out.println();
		
		String word = "président";
		System.out.println("morphology from the word: \""+word+"\"");
		List<BabelMorphWord> bmwFromWord = bm.getMorphologyFromWord(Language.FR, word);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwFromWord)
			System.out.println(bmw);
		System.out.println();
		
		List<BabelMorphWord> bmwNounFromLemma = bm.getMorphologyFromLemma(Language.FR, lemma, POS.NOUN);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwNounFromLemma)
			System.out.println(bmw);
		System.out.println();
		
		List<BabelMorphWord> bmwVerbFromWord = bm.getMorphologyFromWord(Language.FR, word, POS.VERB);
		System.out.println("Lemma\tLang\tPoS\tinflected forms");
		for(BabelMorphWord bmw : bmwVerbFromWord)
			System.out.println(bmw);
		System.out.println();
		
	}

}

