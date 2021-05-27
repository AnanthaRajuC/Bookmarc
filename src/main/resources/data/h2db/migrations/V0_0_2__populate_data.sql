/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

/*
 * Data
 */
INSERT INTO `application_settings` (`settings_Key`,`settings_Value`) VALUES ('applicationName','bookmarc');
INSERT INTO `application_settings` (`settings_Key`,`settings_Value`) VALUES ('applicationVersion','1.0');
INSERT INTO `application_settings` (`settings_Key`,`settings_Value`) VALUES ('Chrome Live Connection','OFF');

-- QNA
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('askubuntu','QNA');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('stackoverflow','QNA');

-- SOCIAL_MEDIA_PLATFORM
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('facebook','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('reddit','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('twitter','SOCIAL_MEDIA_PLATFORM');

-- SOCIAL_MEDIA_PLATFORM, IMAGES
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('instagram','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('flickr','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('tumblr','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('pinterest','SOCIAL_MEDIA_PLATFORM');

-- SOCIAL_MEDIA_PLATFORM, DIGITAL_DESIGN
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('behance','SOCIAL_MEDIA_PLATFORM');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('dribble','SOCIAL_MEDIA_PLATFORM');

-- STOCK_PHOTOGRAPHY
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('unsplash','STOCK_PHOTOGRAPHY');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('shutterstock','STOCK_PHOTOGRAPHY');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('gettyimages','STOCK_PHOTOGRAPHY');

-- VIDEO
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('youtube','VIDEO');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('vimeo','VIDEO');

-- VIDEO, OTT
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('primevideo','VIDEO');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('hotstar','VIDEO');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('hulu','VIDEO');

-- ONLINE_SHOPPING, E_COMMERCE
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('amazon','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('ebay','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('walmart','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('target','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('bestbuy','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('costco','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('homedepot','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('lowes','ONLINE_SHOPPING');

-- ONLINE_SHOPPING, E_COMMERCE
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('flipkart','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('myntra','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('snapdeal','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('shopclues','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('grofers','ONLINE_SHOPPING');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('bigbasket','ONLINE_SHOPPING');

-- FOOD_DELIVERY
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('zomato','FOOD_DELIVERY');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('swiggy','FOOD_DELIVERY');
INSERT INTO `HOST_CLASSIFICATION` (`host`,`website_category`) VALUES('ubereats','FOOD_DELIVERY');