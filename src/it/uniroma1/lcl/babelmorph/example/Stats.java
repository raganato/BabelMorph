/**
 * 
 */
package it.uniroma1.lcl.babelmorph.example;

import it.uniroma1.lcl.babelmorph.BabelMorph;
import it.uniroma1.lcl.babelmorph.BabelMorphWord;
import it.uniroma1.lcl.babelmorph.iterator.BabelMorphWordIterator;
import it.uniroma1.lcl.jlt.util.Language;

import java.util.Set;
import java.util.TreeMap;

import edu.mit.jwi.item.POS;

/**
 * @author raganato
 *
 */
public class Stats 
{

	public static void main(String[] args) throws Exception 
	{
		
		BabelMorph bm = BabelMorph.getInstance();
		
		System.out.println(bm.getBabelMorphVersion());
		//retrieve all the available languages
		Set<Language> languages = bm.getBabelMorphLanguages();
		System.out.println("available languages:\n"+languages.toString());
		//retrieve all the available part-of-speech
		Set<POS> pos = bm.getBabelMorphPoS();
	    System.out.println("available parts-of-speech:\n"+pos.toString());
		System.out.println("word stats:");
		countAllWordsByPOS(bm);
		System.out.println("lemma stats:");
		countAllLemmasByPOS(bm);
	  
	}
	
	private static void countAllWordsByPOS(BabelMorph bm)
	{
		BabelMorphWordIterator it =	bm.getBabelMorphWordIterator();
		TreeMap<String, CountStats> countWords = new TreeMap<>();
		
		while(it.hasNext())
		{
			BabelMorphWord bmw = it.next();
			POS pos = bmw.getPos();
			int words = bmw.getSizeMorphology_map();
			CountStats counter = countWords.get(bmw.getLanguage().getName());
			if(counter == null)
				counter = new CountStats();
			if(pos == POS.NOUN){
				int n = counter.getNumNoun();
				//add the lemma itself
				n+=words+1;
				counter.setNumNoun(n);	
			}if(pos == POS.VERB){
				int n = counter.getNumVerb();
				//add the lemma itself
				n+=words+1;
				counter.setNumVerb(n);
			}if(pos == POS.ADJECTIVE){
				int n = counter.getNumAdj();
				//add the lemma itself
				n+=words+1;
				counter.setNumAdj(n);
			}
			
			countWords.put(bmw.getLanguage().getName(), counter);
		}
		
		System.out.println("language\twords\tnouns\tverbs\tadjectives");
		for(String lang : countWords.keySet()){
			System.out.println(lang+"\t"+countWords.get(lang).getTot()+"\t"
								+countWords.get(lang).getNumNoun()+"\t"+countWords.get(lang).getNumVerb()+"\t"
								+countWords.get(lang).getNumAdj());
		}
		
	}
	
	private static void countAllLemmasByPOS(BabelMorph bm)
	{
		BabelMorphWordIterator it =	bm.getBabelMorphWordIterator();
		TreeMap<String,CountStats> countLemmas = new TreeMap<>();
		while(it.hasNext())
		{
			BabelMorphWord bmw = it.next();
			POS pos = bmw.getPos();
			CountStats counter = countLemmas.get(bmw.getLanguage().getName());
			if(counter == null)
				counter = new CountStats();
			if(pos == POS.NOUN){
				int n = counter.getNumNoun()+1;
				counter.setNumNoun(n);	
			}if(pos == POS.VERB){
				int n = counter.getNumVerb()+1;
				counter.setNumVerb(n);
			}if(pos == POS.ADJECTIVE){
				int n = counter.getNumAdj()+1;
				counter.setNumAdj(n);
			}
			
			countLemmas.put(bmw.getLanguage().getName(), counter);
		}
		
		System.out.println("language\twords\tnouns\tverbs\tadjectives");
		for(String lang : countLemmas.keySet()){
			System.out.println(lang+"\t"+countLemmas.get(lang).getTot()+"\t"
								+countLemmas.get(lang).getNumNoun()+"\t"+countLemmas.get(lang).getNumVerb()+"\t"
								+countLemmas.get(lang).getNumAdj());
		}
		
	}

}

class CountStats
{
	private int numNoun = 0;
	private int numVerb = 0;
	private int numAdj = 0;
	 
	public int getTot(){
		return numNoun+numVerb+numAdj;
	}

	/**
	 * @return the numNoun
	 */
	public int getNumNoun() {
		return numNoun;
	}

	/**
	 * @param numNoun the numNoun to set
	 */
	public void setNumNoun(int numNoun) {
		this.numNoun = numNoun;
	}

	/**
	 * @return the numVerb
	 */
	public int getNumVerb() {
		return numVerb;
	}

	/**
	 * @param numVerb the numVerb to set
	 */
	public void setNumVerb(int numVerb) {
		this.numVerb = numVerb;
	}

	/**
	 * @return the numAdj
	 */
	public int getNumAdj() {
		return numAdj;
	}

	/**
	 * @param numAdj the numAdj to set
	 */
	public void setNumAdj(int numAdj) {
		this.numAdj = numAdj;
	}
	
	
}
	