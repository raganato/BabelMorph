/**
 * 
 */
package it.uniroma1.lcl.babelmorph.iterator;


import it.uniroma1.lcl.babelmorph.BabelMorph;
import it.uniroma1.lcl.babelmorph.BabelMorphIndexField;
import it.uniroma1.lcl.babelmorph.BabelMorphWord;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.util.Bits;

/**
 * @author raganato
 *
 */
public class BabelMorphWordIterator implements Iterator<BabelMorphWord>
{
	private final IndexReader reader;
	private int currentIndex;
	private Bits liveDocs;
	
	public BabelMorphWordIterator(IndexSearcher dictionary) 
	{
		 this.reader = dictionary.getIndexReader();
		 this.currentIndex = reader.numDocs()-1;
		 this.liveDocs = MultiFields.getLiveDocs(reader);
		 
	}
	
	/**
	 * @return true if the iteration has more elements. 
	 * (In other words, returns true if next() would return an element rather than throwing an exception.)
	 *
	 */
	 @Override
	 public boolean hasNext() 
	 {
		  return currentIndex >= 0;
	 }

	/**
	 * 
	 * @return the next element (i.e. a BabelMorphWord) in the iteration.
	 * 
	 */
	@Override
	public BabelMorphWord next()
	{
		if (liveDocs != null && !liveDocs.get(currentIndex))
			throw new ConcurrentModificationException();
		
		try
		{
			Document doc = reader.document(currentIndex--);
			
			String version = doc.get(BabelMorphIndexField.VERSION.toString());
		    if (version != null)
		    	return next();
		    
		    return BabelMorph.getBabelMorphFromDocument(doc);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Cannot return next: " + currentIndex);
		}
	}

	/**
	 * Unsupported operation
	 *  
	 * @throws RuntimeException
	 */
	 @Override
	 public void remove() 
	 {
		  throw new RuntimeException("Unsupported operation 'remove'");
	 }
	 
}
