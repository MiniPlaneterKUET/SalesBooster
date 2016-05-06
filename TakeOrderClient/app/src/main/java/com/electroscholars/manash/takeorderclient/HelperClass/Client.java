package com.electroscholars.manash.takeorderclient.HelperClass;

/**
 * Created by Manash on 5/6/2016.
 */
public class Client {


    //Client category helper class
    public class ClientCategory{
        public final static int TRADE_ID = 0;
        public final static int DISTRIBUTOR_ID = 1;
        public final static int CORPORATE_ID = 2;

        private final static String TRADE = "Trade";
        private final static String CORPORATE = "Corporate";
        private final static String DISTRIBUTOR = "Distributor";

        private String category;
        private int id;

        ClientCategory (int id){
            this.id = id;
            switch (id){
                case TRADE_ID:
                    category = TRADE;
                case DISTRIBUTOR_ID:
                    category = DISTRIBUTOR;
                case CORPORATE_ID:
                    category = CORPORATE;
            }
        }

        public void setID(int id) {this.id = id;}

        public String getCategory() {return category; }



    }

    private String clientID;
    private String clientName;
    private String clientEmail;
    private String clientAddress;
    private String clientCity;
    private String clientContactNumber;
    private String clientCountry;
    private ClientCategory clientCategory;



    //Ctor
    public Client() {}

    //Ctor with name only
    public Client(String clientName){
        this.clientName = clientName;
    }

    public Client(String clientName, String clientID){
        this.clientName = clientName;
        this.clientID = clientID;
    }

    public Client(String clientName, String clientID, String clientContactNumber, String clientEmail,
                  String clientAddress, String clientCity, String clientCountry, ClientCategory
                          clientCategory)
    {
        this.clientName = clientName;
        this.clientID = clientID;
        this.clientContactNumber = clientContactNumber;
        this.clientEmail = clientEmail;
        this.clientCategory = clientCategory;
        this.clientCountry = clientCountry;
        this.clientCity = clientCity;
        this.clientAddress = clientAddress;
    }

    //Set methods
    public void setClientID(String clientID) {this.clientID = clientID;}

    public void setClientName(String clientName) {this.clientName = clientName;}

    public void setClientCity(String clientCity) { this.clientCity = clientCity;}

    public void setClientCategory(ClientCategory clientCategory) { this.clientCategory = clientCategory; }

    public void setClientContactNumber (String clientContactNumber) {this.clientContactNumber =
            clientContactNumber;}

    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public void setClientCountry ( String clientCountry) {this.clientCountry = clientCountry;}

    public void setClientAddress (String clientAddress) { this.clientAddress = clientAddress; }

    //Get methods

    public String getClientName() { return this.clientName; }

    public String getClientID() { return this.clientID; }

    public String getClientCity() { return this.clientCity; }

    public ClientCategory getClientCategory() { return this.clientCategory; }

    public String getClientEmail() { return this.clientEmail; }

    public String getClientAddress() { return this.clientAddress; }

    public String getClientContactNumber() {return this.clientContactNumber;}

    public String getClientCountry() { return this.clientCountry; }


    public String toString(){
        String info = "";

        info += "Name: " + getClientName() + "\n";
        info += "ID: " + getClientID() + "\n";
        info += "Contact: " + getClientContactNumber() + "\n";
        info += "e-mail: " + getClientEmail() + "\n";
        info += "Address: " + getClientAddress() + "\n";
        info += "City: " + getClientCity() + "\n";
        info += "Country: " + getClientCountry() + "\n";
        info += "Category: " + getClientCategory().getCategory() + "\n";

        return info;
    }
}
