# Introduction #

This page explain the datamodel of ginvoicing project.

# Datamodel #
| **Category**|extends EntityBase| | |
|:|:-----------------|:|:|
|name|String|Category name| |
|description|String|Category description| |

| **Contact**|extends EntityBase| | |
|:|:-----------------|:|:|
|name|String|Contact name| |
|address|String|Contact address| |
|city|String|Contact city| |
|country|String|Contact country| |
|phone|String|Contact phone| |
|email|String|Contact email| |

| **Customer**|extends Contact| | |
|:|:--------------|:|:|
|contact|String|Customer contact| |
|fax|String|Customer fax| |
|notes|String|Customer general notes| |
|enabled|Boolean|Customer enabled| |

| **EntityBase**|| | |
|:|:|:|:|
|id|String|PK|Entity identification|

| **EntityDisplay**|| | |
|:|:|:|:|
|data|String|Data| |

| **Invoice**|extends EntityBase| | |
|:|:-----------------|:|:|
|date|Date|Invoice created date| |
|customer|Customer|Invoice customer id| |
|lines|List|List of lines objects| |
|notes|String|Notes about this invoice| |
|amount|Float|Total amount of the invoice| |

| **Line**|extends EntityBase| | |
|:|:-----------------|:|:|
|quantity|Float|Quantity of products| |
|product|String|Product id| |
|productName|String|Product name (Not persistent)| |
|price|Float|Unit price of the product| |

| **Products**|extends EntityBase| | |
|:|:-----------------|:|:|
|name|String|Product name| |
|description|String|Product description| |
|price|Float|Product unit price| |
|category|String|Product category id| |



NOT IMPLEMENTED
| **Supplier**|extends EntityBase| | |
|:|:-----------------|:|:|
|contact|String|Supplier contact| |
|fax|String|Supplier fax| |
|payment|String|Supplier payment terms| |
|enabled|Boolean|Supplier enabled| |