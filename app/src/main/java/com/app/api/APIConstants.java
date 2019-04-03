package com.app.api;


public class APIConstants {

    // Domain/Server
    private static final String DEVELOPMENT_DOMAIN = "";
    private static final String TESTING_DOMAIN = "";
    private static final String PRODUCTION_DOMAIN = "";

    /**
     * This defines the environment or the server to which the app is pointing to.
     */
    private static Environment environment = Environment.PRODUCTION;


    /**
     * Check the current environment of the app.
     *
     * @return current environment value of the app.
     */
    public static Environment getEnvironment() {
        return environment;
    }

    /**
     * Get domain based on the environment of the app.
     *
     * @return domain based on the app environment. (e.g. development/production)
     */
    public static String getDomain() {
        switch (environment) {
            case DEVELOPMENT:
                return DEVELOPMENT_DOMAIN;
            case PRODUCTION:
                return PRODUCTION_DOMAIN;
            case TESTING:
                return TESTING_DOMAIN;
            default:
                return "";
        }
    }

    /**
     * Get API prefix.
     *
     * @return string with combination of domain and method.
     */
    private static String getAPIPrefix() {
        return getDomain() + "api/" + "?method=";
    }

    /**
     * App environment enum. (can be more like: UAT/STAGING/DEBUG...)
     */
    public enum Environment {
        DEVELOPMENT, TESTING, PRODUCTION
    }

}
