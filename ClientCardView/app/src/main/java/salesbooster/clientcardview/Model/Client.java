package salesbooster.clientcardview.Model;

/**
 * Created by Manash on 5/17/2016.
 */
public class Client {

    private String name;
    private String contact;
    private String e_mail;
    private String address;

    public Client(String name, String address, String e_mail, String contact){
        this.name = name;
        this.address = address;
        this.e_mail = e_mail;
        this.contact = contact;
    }

    public String getName(){
        return this.name;
    }

    public String getE_mail() { return this.e_mail;}

    public String getContact() { return this.contact; }

    public String getAddress() { return this.address; }

}
