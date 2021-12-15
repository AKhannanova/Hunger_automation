public enum ElementConst {

    //Welcome screen
    WELCOME_GREAT_BUTTON("de.ordersmart.portal:id/greatButton", RecentAddresses.NO_VALUE);



    //Location access permission window
    //LOC_PER_DENY_BUTTON("com.android.packageinstaller:id/permission_deny_button",RecentAddresses.NO_VALUE);

    /*public static final String loc_per_deny_button = "com.android.packageinstaller:id/permission_deny_button";
    public String loc_per_allow_button = "com.android.packageinstaller:id/permission_allow_button";
    //"Delivery address" screen
    public String del_add_back_button = "de.ordersmart.portal:id/backButton";
    public String del_add_header = "de.ordersmart.portal:id/header";
    public String del_add_empty_result = "de.ordersmart.portal:id/emptyResults";
    public String del_add_edit_text = "de.ordersmart.portal:id/editText";
    public String del_add_search_icon = "de.ordersmart.portal:id/searchIcon";
    public String del_add_clear_text_button = "de.ordersmart.portal:id/clearTextButton";
    public String del_add_location_icon = "de.ordersmart.portal:id/locationIcon";
    public String del_add_search_result_list = "de.ordersmart.portal:id/searchResultList";
    public String del_add_recent_list = "de.ordersmart.portal:id/recentList";
    public String del_add_confirm_button = "de.ordersmart.portal:id/confirmAddressButton";
    //Branches list screen
    public String branches_postal_code = "de.ordersmart.portal:id/postalCodeTextView";
    */

    private String android;
    private String ios;
    ElementConst(String android, String ios) {
        this.android = android;
        this.ios = ios;
    }

    public String getIos() {
        return ios;
    }

    public String getAndroid() {
        return android;
    }

    public String getConstBy(boolean isAndroid){
        return isAndroid ? getAndroid(): getIos();
    }
}
