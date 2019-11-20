package RestPractice;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown =true)
public class Spartan {

    private long id;
    private String name;
    private String gender;
    private long phone;

    public Spartan(){

    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name=" + name +
                ", gender=" + gender +
                ", phone=" + phone +
                "}";
    }



    @JsonIgnore      // this will enable us to ignore ID field from being written into json
                     // this will happen when you do serialize
    public long getId(){
        return id;
    }

    @JsonProperty   // this will specifically tell to write this into pojo from json
                    // this will happen when you do deserialize
    public void setId(long id){
        this.id=id;
    }
}
