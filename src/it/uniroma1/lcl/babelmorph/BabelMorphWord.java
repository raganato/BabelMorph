/**
 * 
 */
package it.uniroma1.lcl.babelmorph;


import it.uniroma1.lcl.jlt.util.Language;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import edu.mit.jwi.item.POS;

/**
 * @author raganato
 *
 */
public class BabelMorphWord
{

	private final String lemma;
	
	private final Language language;
	
	private final POS pos;
	
	private final SetMultimap<String, String> inflectedFormsInfo_map;
	
	/**
	 * Creates a new instance of a {@link BabelMorphWord}.
	 * 
	 * @param lemma
	 * @param language
	 * @param pos
	 * @param inflectedFormsInfo_map
	 * 
	 */
	public BabelMorphWord(String lemma, Language language, POS pos, SetMultimap<String, String> inflectedFormsInfo_map)
	{
		this.lemma = lemma;
		this.language = language;
		this.pos = pos;
		this.inflectedFormsInfo_map = inflectedFormsInfo_map;
	}

	/**
	 * @return the lemma
	 */
	public String getLemma() 
	{
		return lemma;
	}

	/**
	 * @return the language of the lemma
	 */
	public Language getLanguage() 
	{
		return language;
	}

	/**
	 * @return the PoS of the lemma
	 */
	public POS getPos() 
	{
		return pos;
	}

	/**
	 * @return the map with the inflectedForms and informations of the lemma
	 */  
	public SetMultimap<String, String> getInflectedFormsInfo_map() 
	{
		return inflectedFormsInfo_map;
	}

	/**
	 * @return the size of the map
	 */  
	public int getSizeMorphology_map() 
	{
		return inflectedFormsInfo_map.size();
	}	
	
	/**
	 * returns a string consisting of the four fields of the object.
	 * In other words, this method returns a string equal to the value of:
	 * lemma \t language \t pos \t inflectedForms1 info:Info1 \t inflectedForms2 info:Info2...
	 * 
	 * @return a string representation of the object
	 */
	@Override
	public String toString() 
	{
		StringBuilder infFormsInfo = new StringBuilder();
		for(String iF : inflectedFormsInfo_map.keySet()){
			infFormsInfo.append("\t"+iF+" info:"+inflectedFormsInfo_map.get(iF));
		}
		
		return  lemma + "\t" + language
				+ "\t" + pos + infFormsInfo.toString();
	}
	
}
