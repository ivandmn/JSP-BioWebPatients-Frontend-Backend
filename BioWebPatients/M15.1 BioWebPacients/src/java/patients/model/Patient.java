package patients.model;

/**
 * Class that contains all the patients info: blood type, rh, height, weight, name... 
 * @author alumne
 */
public class Patient {
    private String name;
    private String surnames;
    /** 
     * Gender can be: gen-wom, gen-man, or gen-oth.
     */
    private String gender;
    /** 
     * Blood Type can be: A+,A-,O+,0-,AB+,AB-,B+, B-.
     */
    private String bloodType;
    /** 
     * Weight in Kgs
     */
    private int weight;
    /** 
     * Height in cms
     */
    private int height;

    
    public Patient(String name, String surnames, String gender, String bloodType, int weight, int height) {
        this.name = name;
        this.surnames = surnames;
        this.gender = gender;
        this.bloodType = bloodType;
        this.weight = weight;
        this.height = height;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", surnames=" + surnames + ", gender=" + gender + ", bloodType=" + bloodType + ", weight=" + weight + ", height=" + height + '}';
    }
    
    
}
