-- Fresh Setting Data for Breeze Commerce, 2015/08/04, kunner@kunner.com


-- 관리자 관련 데이터 추가

	-- Data for blc_admin_*
	INSERT INTO `BLC_ADMIN_MODULE` (`ADMIN_MODULE_ID`, `DISPLAY_ORDER`, `ICON`, `MODULE_KEY`, `NAME`) VALUES (-10, 120, 'icon-search', 'Search', 'Search');
	INSERT INTO `BLC_ADMIN_SECTION` (`ADMIN_SECTION_ID`, `CEILING_ENTITY`, `DISPLAY_CONTROLLER`, `DISPLAY_ORDER`, `NAME`, `SECTION_KEY`, `URL`, `USE_DEFAULT_HANDLER`, `ADMIN_MODULE_ID`) VALUES (-1700, 'org.broadleafcommerce.core.store.domain.Store', NULL, 7500, 'Store', 'Store', '/store', NULL, -10),(-1600, 'org.broadleafcommerce.core.search.domain.Field', NULL, 7000, 'SearchField', 'SearchField', '/search-field', NULL, -10),(-1500, 'org.broadleafcommerce.core.search.redirect.domain.SearchRedirect', NULL, 6000, 'SearchRedirect', 'SearchRedirect', '/search-redirect', NULL, -10),(-1400, 'org.broadleafcommerce.core.search.domain.SearchFacet', NULL, 6000, 'SearchFacet', 'SearchFacet', '/search-facet', NULL, -10),(-1300, 'org.broadleafcommerce.common.enumeration.domain.DataDrivenEnumeration', NULL, 6000, 'Enumeration', 'Enumeration', '/enum-config', NULL, -2),(-1200, 'org.broadleafcommerce.cms.field.domain.FieldGroup', NULL, 2500, 'PageTypes', 'PageTypes', '/page-fields-group', NULL, -2),(-1000, 'org.broadleafcommerce.cms.page.domain.PageTemplate', NULL, 3000, 'PageTemplate', 'PageTemplate', '/page-template', NULL, -2);
	INSERT INTO `BLC_ADMIN_PERMISSION` (`ADMIN_PERMISSION_ID`, `DESCRIPTION`, `IS_FRIENDLY`, `NAME`, `PERMISSION_TYPE`) VALUES (-250, 'Maintain Store', 1, 'PERMISSION_ALL_STORE_TEMPLATE', 'ALL'),(-202, 'Maintain PageTemplate', 1, 'PERMISSION_ALL_PAGE_TEMPLATE', 'ALL');
	INSERT INTO `BLC_ADMIN_PERMISSION_ENTITY` (`ADMIN_PERMISSION_ENTITY_ID`, `CEILING_ENTITY`, `ADMIN_PERMISSION_ID`) VALUES (-1250, 'org.broadleafcommerce.core.store.domain.Store', -250),(-1230, 'org.broadleafcommerce.cms.page.domain.PageAttribute', -202),(-1220, 'org.broadleafcommerce.cms.field.domain.FieldDefinition', -202),(-1210, 'org.broadleafcommerce.cms.field.domain.FieldGroup', -202),(-1200, 'org.broadleafcommerce.cms.page.domain.PageTemplateFieldGroupXref', -202);
	INSERT INTO `BLC_ADMIN_PERMISSION_XREF` (`CHILD_PERMISSION_ID`, `ADMIN_PERMISSION_ID`) VALUES (-51, -161),(-39, -161);
	INSERT INTO `BLC_ADMIN_ROLE_PERMISSION_XREF` (`ADMIN_ROLE_ID`, `ADMIN_PERMISSION_ID`) VALUES (-1, -250),(-1, -202);
	INSERT INTO `BLC_ADMIN_SEC_PERM_XREF` (`ADMIN_SECTION_ID`, `ADMIN_PERMISSION_ID`) VALUES (-1000, -200),(-1000, -202),(-1000, -1),(-1200, -202),(-1200, -160),(-1300, -202),(-1400, -33),(-1500, -31),(-1400, -32),(-1500, -30),(-1600, -33),(-1700, -250);
	INSERT INTO `BLC_ADMIN_USER_PERMISSION_XREF` (`ADMIN_USER_ID`, `ADMIN_PERMISSION_ID`) VALUES (-1, -103);
	INSERT INTO `BLC_ADMIN_PERMISSION_ENTITY` (`ADMIN_PERMISSION_ENTITY_ID`, `CEILING_ENTITY`, `ADMIN_PERMISSION_ID`) VALUES (-553, 'org.broadleafcommerce.cms.page.domain.PageTemplate', -202);
	INSERT INTO `BLC_ADMIN_ROLE_PERMISSION_XREF` (`ADMIN_ROLE_ID`, `ADMIN_PERMISSION_ID`) VALUES (-1, -39), (-1, -33), (-1, -31);

	-- Data for Locale, Currency, Country
	INSERT INTO `BLC_COUNTRY` (`ABBREVIATION`, `NAME`) VALUES ('KR', 'Korea Rep. of');
	INSERT INTO `BLC_CURRENCY` (`CURRENCY_CODE`, `DEFAULT_FLAG`, `FRIENDLY_NAME`) VALUES ('KRW', 0, 'Korean Won');
	INSERT INTO `BLC_LOCALE` (`LOCALE_CODE`, `DEFAULT_FLAG`, `FRIENDLY_NAME`, `USE_IN_SEARCH_INDEX`, `CURRENCY_CODE`) VALUES ('ko', 0, 'Korean', NULL, 'KRW'),('ko_KR', 0, 'Korean (South)', NULL, 'KRW');
	

