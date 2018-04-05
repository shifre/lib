package com.cantonsoft.core.cloud.site;

public interface SiteFieldConstent {
	public static final String TYPE_TEXT = "text"; // 文本
	public static final String TYPE_TEXTAREA = "textarea"; // 多行文本
	public static final String TYPE_HTML = "html"; // HTML
	public static final String TYPE_IMAGES = "images"; // 图片集
	public static final String TYPE_FILES = "files"; // 文件集
	public static final String TYPE_REFS = "refs"; // 引用文章
	public static final String TYPE_BOOL = "bool"; // 单选
	public static final String TYPE_MULTIPLE = "multiple"; // 多选
	public static final String TYPE_DATETIME = "datetime"; // 日期
	
	public static final String TYPE_ASSET_IMAGE = "IMAGE";
	public static final String TYPE_ASSET_FILE = "FILE";
	public static final String TYPE_ASSET_STRING = "STRING";
	public static final String TYPE_ASSET_HTML = "HTML";
	
	public static final String FIELD_ATTRIBUTES = "attributes";
	public static final String FIELD_UNIQUENAME = "name";
	public static final String FIELD_PARENTID = "parentId";
	public static final String FIELD_ENABLEED="enabled";
	public static final String FIELD_QUANTITY="quantity";
	public static final String FIELD_SITEID = "siteId";
	public final static String FIELD_POSITION= "position";
	public static final String FIELD_YEAR = "year";
	public static final String FIELD_MONTH = "month";
	public static final String FIELD_DATE = "date";
	public static final String FIELD_HOUR = "hour";
	public static final String FIELD_VISITS = "visits";
	public static final String FIELD_REMOTEADDRESS = "remoteAddress";
	public static final String FIELD_USERAGENT = "userAgent";
 
	public static final String UNDEFINED = "undefined";
	
//	public static final String SITNAV_UNIQUEUENAME_ALLSHOP = "allShop";
	
	public static final String OPTION_GT="$gt";
	public static final String OPTION_GTE="$gte";
	public static final String OPTION_LT="$lt";
	public static final String OPTION_LTE="$lte";
	public static final String OPTION_INC="$inc";
	public static final String OPTION_MATCH="$match";
	public static final String OPTION_SUM = "$sum";
	public static final String OPTION_GROUP = "$group";
	public static final String OPTION_SORT = "$sort";
	public static final String OPTION_LIMIT = "$limit";
	
	public static final String OPTION_BASIC = "$";
	
	public static final String PRO_SITEVISIT_RANG="site.visit.date.rang";
	public static final String PRO_SITEVISIT_RANG_DEFAULT= "14";
	
	public static final String PRO_SITEVISIT_DATE_FORMAT="site.visit.date.format";
	public static final String PRO_SITEVISIT_DATE_FORMAT_DEFAULT= "MM月dd日";
	
	public static final String PRO_ARTICLE_POPULAR_LIMIT = "article.popular.limit";
	public static final String PRO_ARTICLE_POPULAR_LIMIT_DEFAULT = "5";
	
	public static final String PRO_ARTICLE_POPULAR_RANG="article.popular.rang";
	public static final String PRO_ARTICLE_POPULAR_RANG_DEFAULT= "14";
	
	public static final String PRO_PRODUCT_POPULAR_LIMIT = "product.popular.limit";
	public static final String PRO_PRODUCT_POPULAR_LIMIT_DEFAULT = "5";
	
	public static final String PRO_PRODUCT_POPULAR_RANG="product.popular.rang";
	public static final String PRO_PRODUCT_POPULAR_RANG_DEFAULT= "14";
}
