package it.uniroma1.lcl.babelmorph;

public enum Language 
{
	
	ARA("Arabic", "ara"),
	BEN("Bengali", "ben"),
	BUL("Bulgarian", "bul"),
	CAT("Catalan", "cat"),
	CES("Czech", "ces"),
	CKB("Central Kurdish", "ckb"),
	CYM("Welsh", "cym"),
	DAN("Danish", "dan"),
	DEU("German", "deu"),
	DSB("Lower Sorbian", "dsb"),
	ENG("English", "eng"),
	EST("Estonian", "est"),
	EUS("Basque", "eus"),
	FAO("Faroese", "fao"),
	FAS("Persian", "fas"),
	FIN("Finnish", "fin"),
	FRA("French", "fra"),
	GLA("Scottish Gaelic", "gla"),
	GLE("Irish", "gle"),
	HAI("Haida", "hai"),
	HEB("Hebrew", "heb"),
	HIN("Hindi", "hin"),
	HUN("Hungarian", "hun"),
	HYE("Armenian", "hye"),
	ISL("Icelandic", "isl"),
	ITA("Italian", "ita"),
	KAT("Georgian", "kat"),
	KLR("Khaling", "klr"),
	KMR("Northern Kurdish", "kmr"),
	LAT("Latin", "lat"),
	LAV("Latvian", "lav"),
	LIT("Lithuanian", "lit"),
	MKD("Macedonian", "mkd"),
	NAV("Navajo", "nav"),
	NLD("Dutch", "nld"),
	NNO("Norwegian Nynorsk", "nno"),
	NOB("Norwegian Bokmål", "nob"),
	POL("Polish", "pol"),
	POR("Portuguese", "por"),
	QUE("Quechua", "que"),
	RON("Romanian", "ron"),
	RUS("Russian", "rus"),
	SLK("Slovak", "slk"),
	SLV("Slovenian", "slv"),
	SME("Northern Sami", "sme"),
	SPA("Spanish", "spa"),
	SQI("Albanian", "sqi"),
	SWE("Swedish", "swe"),
	TUR("Turkish", "tur"),
	UKR("Ukrainian", "ukr"),
	URD("Urdu", "urd");


	private String lang;
	private String langCode;
	
	Language(String lang, String langCode){ 
		this.lang = lang; 
		this.langCode = langCode;
	}
	
	public String getLang(){ return this.lang; }
	public String getLangCode(){ return this.langCode; }

	public String toString() { return this.lang; }
	
}
