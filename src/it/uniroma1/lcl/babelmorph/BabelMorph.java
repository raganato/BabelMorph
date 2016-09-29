/**
 * 
 */
package it.uniroma1.lcl.babelmorph;


import it.uniroma1.lcl.babelmorph.iterator.BabelMorphWordIterator;
import it.uniroma1.lcl.jlt.util.Language;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TotalHitCountCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.BytesRef;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import edu.mit.jwi.item.POS;

/**
 * @author raganato
 *
 */
public class BabelMorph 
{
	private final static Log log = LogFactory.getLog(BabelMorph.class);
	/**
	 * The only instance of BabelMorph 
	 */
	static private BabelMorph instance;
	
	private final IndexSearcher morphologicalDictionary;
	
	/**
	 * The private constructor used to initialize the BabelMorph index
	 * 
	 * @throws IOException
	 */
	private BabelMorph() throws IOException
	{
		
		BabelMorphConfiguration config = BabelMorphConfiguration.getInstance();
		
		String indexFile = config.getBabelMorphIndexDir();
				
		Directory morphDir = new SimpleFSDirectory(new File(indexFile).toPath());
		
		log.info("Opening index: "+indexFile);
		IndexReader idx =  DirectoryReader.open(morphDir);
		
		this.morphologicalDictionary = new IndexSearcher(idx);
		
	}
	
	
	/**
	 * Used to access {@link BabelMorph}
	 * 
	 * @return an instance of {@link BabelMorph}
	 */
	public static synchronized BabelMorph getInstance()
	{
		try
		{
			if (instance == null) instance = new BabelMorph();
			return instance;
		}
		catch (IOException e)
		{
			throw new RuntimeException("Could not init BabelMorph: " + e.getMessage());
		}
	}
	
	/**
	 * Given a word and its pos, returns the morphology of the word.
	 * 
	 * 
	 * @param language
	 *            the language of the input word.
	 * @param word
	 *            the word whose morphology has to be retrieved.
	 * @param pos
	 *            the PoS of the word.
	 * @return the morphology of the word.
	 * 	
	 */
	public List<BabelMorphWord> getMorphologyFromWord(Language language, String word, POS pos) throws IOException
	{
		List<BabelMorphWord> morph_list = getBabelMorphWord(language, null, word, pos);
		morph_list.addAll(getMorphologyFromLemma(language, word, pos));
		return morph_list; 
	}
	
	
	/**
	 * Given a word, returns the morphology of the word.
	 * 
	 * @param language
	 *            the language of the input word.
	 * @param word
	 *            the word whose morphology has to be retrieved.
	 *
	 * @return the morphology of the word.
	 * 
	 */
	public List<BabelMorphWord> getMorphologyFromWord(Language language, String word) throws IOException
	{
		List<BabelMorphWord> morph_list = getBabelMorphWord(language, null, word, null);
		morph_list.addAll(getMorphologyFromLemma(language, word, null));
		return morph_list;
	}
	
	/**
	 * Given a lemma and its pos, returns the morphology of the lemma.
	 * 
	 * @param language
	 *            the language of the input word.
	 * @param lemma
	 *            the word whose morphology has to be retrieved.
	 * @param pos
	 *            the PoS of the word.
	 *            
	 * @return the morphology of the word.
	 * 
	 */
	public List<BabelMorphWord> getMorphologyFromLemma(Language language, String lemma, POS pos) throws IOException
	{
		return getBabelMorphWord(language, lemma, null, pos);
	}
	
	/**
	 * Given a lemma, returns the morphology of the lemma.
	 * 
	 * @param language
	 *            the language of the input word.
	 * @param lemma
	 *           the word whose morphology has to be retrieved.
	 *
	 * @return the morphology of the word.
	 * 
	 */
	public List<BabelMorphWord> getMorphologyFromLemma(Language language, String lemma) throws IOException
	{
		return getBabelMorphWord(language, lemma, null, null);
	}
	
	private  List<BabelMorphWord> getBabelMorphWord(Language language, String lemma, String word, POS pos)throws IOException
	{
		BooleanQuery.Builder mainQuery = new BooleanQuery.Builder();
		
		Term termLanguage = new Term(BabelMorphIndexField.LANGUAGE.toString(), language.name());
		TermQuery termQueryLanguage = new TermQuery(termLanguage);
		mainQuery.add(termQueryLanguage, BooleanClause.Occur.MUST);
		
		if( word != null)
		{
			Term termForm =new Term(BabelMorphIndexField.FORM.toString(), word);
			TermQuery termQueryForm = new TermQuery(termForm);
			mainQuery.add(termQueryForm, BooleanClause.Occur.MUST);
		}
		
		if( lemma != null)
		{
			Term termLemma =new Term(BabelMorphIndexField.LEMMA.toString(), lemma);
			TermQuery termQueryLemma = new TermQuery(termLemma);
			mainQuery.add(termQueryLemma, BooleanClause.Occur.MUST);
		}
		
		if( pos != null )
		{
			Term termPos =new Term(BabelMorphIndexField.POS.toString(), pos.name());
			TermQuery temQueryPos = new TermQuery(termPos);
			mainQuery.add(temQueryPos, BooleanClause.Occur.MUST);
		}
		
		List<BabelMorphWord> listBabelMorphWord = new ArrayList<BabelMorphWord>();
		BooleanQuery bq = mainQuery.build();
		TotalHitCountCollector collector = new TotalHitCountCollector();
		morphologicalDictionary.search(bq, collector);
		TopDocs topDocs = morphologicalDictionary.search(bq, Math.max(1, collector.getTotalHits()));
		ScoreDoc[] topScoreDocs = topDocs.scoreDocs;
		for(ScoreDoc sc : topScoreDocs)
		{
			Document doc = morphologicalDictionary.doc(sc.doc);
			
			String queryLemma = doc.get(BabelMorphIndexField.LEMMA.toString());
			String queryPos = doc.get(BabelMorphIndexField.POS.toString());
			POS p = POS.valueOf(queryPos);
			String[] queryForms = doc.getValues(BabelMorphIndexField.FORM.toString());
			String[] queryInfos = doc.getValues(BabelMorphIndexField.INFO.toString());
			SetMultimap<String, String> multimap = HashMultimap.create();
			for(int i=0;i<queryForms.length;i++)
				multimap.put(queryForms[i], queryInfos[i]);	
			
			BabelMorphWord bmw = new BabelMorphWord(queryLemma, language, p, multimap);
			listBabelMorphWord.add(bmw);
		}
		
		return listBabelMorphWord;
	}
	
	/**
	 * 
	 * @return the BabelMorphAPI Version 
	 */
	public  String getBabelMorphVersion()throws IOException
	{
		Terms terms = SlowCompositeReaderWrapper.wrap(morphologicalDictionary.getIndexReader()).terms(BabelMorphIndexField.VERSION.toString()); 
		TermsEnum termsEnum = terms.iterator();
		String version = termsEnum.next().utf8ToString();
		return "BabelMorph API v"+version;
	}

	
	/**
	 * 
	 * @return the available languages 
	 */
	public Set<Language> getBabelMorphLanguages()throws IOException
	{
		Set<Language> languages = new HashSet<>();
		
		Terms terms = SlowCompositeReaderWrapper.wrap(morphologicalDictionary.getIndexReader()).terms(BabelMorphIndexField.LANGUAGE.toString()); 
		TermsEnum termsEnum = terms.iterator();
		BytesRef text;
		while((text = termsEnum.next()) != null) {
		     languages.add(Language.valueOf(text.utf8ToString()));
		}
		return languages;
	}
	
	/**
	 * 
	 * @return the available part-of-speech 
	 */
	public Set<POS> getBabelMorphPoS()throws IOException
	{
		Set<POS> pos = new HashSet<>();
		
		Terms terms = SlowCompositeReaderWrapper.wrap(morphologicalDictionary.getIndexReader()).terms(BabelMorphIndexField.POS.toString()); 
		TermsEnum termsEnum = terms.iterator();
		BytesRef text;
		while((text = termsEnum.next()) != null) {
			pos.add(POS.valueOf(text.utf8ToString()));
		}
		return pos;
	}
	
	/**
	 * Gets a {@link BabelMorphWord} from a {@link Document}
	 * 
	 * @param doc
	 *            a Lucene {@link Document} record for a certain WktMorphWord
	 * @return an instance of a {@link BabelMorphWord} from a {@link Document}
	 */
	public static BabelMorphWord getBabelMorphFromDocument(Document doc)
	{
		try
		{
			String queryLanguage = doc.get(BabelMorphIndexField.LANGUAGE.toString());
			Language lang = Language.valueOf(queryLanguage);
			String queryLemma = doc.get(BabelMorphIndexField.LEMMA.toString());
			String queryPos = doc.get(BabelMorphIndexField.POS.toString());
			POS p = POS.valueOf(queryPos);
			String[] queryForms = doc.getValues(BabelMorphIndexField.FORM.toString());
			String[] queryInfos = doc.getValues(BabelMorphIndexField.INFO.toString());
			
			if( lang == null || queryLemma == null || 
					queryPos == null || p == null || queryForms == null ) return null;
				
			HashMultimap<String, String> multimap =  HashMultimap.create();
			for(int i=0;i<queryForms.length;i++)
				multimap.put(queryForms[i], queryInfos[i]);
			
			BabelMorphWord bmw = new BabelMorphWord(queryLemma, lang, p, multimap);
			return bmw;
		
		}
	    catch (Exception e)
	    {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	
	/**
	 * Creates a new instance of {@link BabelMorphWordIterator}.
	 * Returns an iterator over the elements.
	 * 
	 * @return an instance of a {@link BabelMorphWordIterator}.
	 * 
	 */
	public BabelMorphWordIterator getBabelMorphWordIterator() {

		if(morphologicalDictionary != null)
			return new BabelMorphWordIterator(morphologicalDictionary);
		else
			return null;
	}
	
}
