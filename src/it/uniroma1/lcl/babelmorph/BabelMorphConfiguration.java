
package it.uniroma1.lcl.babelmorph;


import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author raganato
 *
 */
public class BabelMorphConfiguration 
{
	
	private org.apache.commons.configuration.PropertiesConfiguration config = null;
	
	private final static Log log = LogFactory.getLog(BabelMorphConfiguration.class);
	
	static private BabelMorphConfiguration instance = null;
	static private String CONFIG_DIR = "config/";
	static public String CONFIG_FILE = "babelmorph.properties";

	
	/**
	 * Private constructor. By default loads config/babelmorph.properties
	 * 
	 * @throws ConfigurationException
	 */
	private BabelMorphConfiguration()
	{
		
		File configFile = new File(CONFIG_DIR, CONFIG_FILE);
		boolean bDone = false;
		if (configFile.exists())
		{
			log.info("Loading " + CONFIG_FILE + " FROM " + configFile.getAbsolutePath());
			try
			{
				config = new PropertiesConfiguration(configFile);
				bDone = true;
			}
			catch (ConfigurationException ce)
			{
				ce.printStackTrace();
			}
		}
		
		if (!bDone)
		{
			log.info("BabelMorph starts with empty configuration!!!");
			config = new PropertiesConfiguration();
		}
	}
	
	/**
	 * Used to obtain object instance
	 * 
	 * @return an instance of {@link BabelMorphConfiguration}
	 */
	public static synchronized BabelMorphConfiguration getInstance()
	{
		if (instance == null)
		{
			instance = new BabelMorphConfiguration();
		}
		return instance;
	}
	
	
	/**
	 * Set the file from which to load the new properties
	 * 
	 * @param configurationFile
	 *            the file under config/ to load the properties from
	 */
	public void setConfigurationFile(File configurationFile)
	{
		log.info("Changing configuration properties to " + configurationFile);
		try
		{
			config = new PropertiesConfiguration(configurationFile);
			config.setBasePath(
				configurationFile.getParentFile().getAbsolutePath());
		}
		catch (Exception ce)
		{
			ce.printStackTrace();
			log.info("Setting BabelMorph to an empty configuration");
			config = new PropertiesConfiguration();
		}
	}
	
	/**
	 * 
	 * @return the path of the index
	 */
	public String getBabelMorphIndexDir()
	{
		return config.getString("babelmorph.dir");
	}
}
