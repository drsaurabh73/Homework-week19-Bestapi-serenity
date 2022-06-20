package com.localhost.constant;

/**
 * Created by saurabh
 */
public class EndPoints {

    /**
     * This is Endpoints of Product api
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCTS_BY_ID = "/products/{productID}";
    public static final String CREATE_PRODUCT_BY_ID = "/products";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productID}";

    /**
     * This is Endpoints of Store api
     */

    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_SINGLE_STORES_BY_ID = "/stores/{storeID}";
    public static final String CREATE_STORE_BY_ID = "/stores";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeID}";

    /**
     * This is Endpoints of Categories api
     */

    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String CREATE_CATEGORY_BY_ID = "/categories";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{categoryID}";

    /**
     * This is Endpoints of services api
     */

    public static final String GET_ALL_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICES_BY_ID = "/services/{serviceID}";
    public static final String CREATE_SERVICES_BY_ID = "/services";
    public static final String UPDATE_SERVICES_BY_ID = "/services/{serviceID}";
    public static final String DELETE_SERVICES_BY_ID = "/services/{serviceID}";

    /**
     * This is Endpoints of Utility api
     */

    public static final String GET_ALL_HEALTHCHECK = "/healthcheck";
    public static final String GET_ALL_VERSIONS = "/version";


}
