public class HomeOwnerAccount{
  
  String fistName;
  String lastName;
  String password;
  String[] services;
  
  public HomeOwnerAccount(String lastName, String firstName, String password){
  
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.services = { "l�installation ou la r�paration d�appareil", "nettoyage de tapis", "d�m�nagement", "plomberie", "l�assemblage de meubles", "services de serrurerie", "peinture", "nettoyage de fen�tre",
    "�lectrique", "nettoyage de moisissure", "services antiparasitaire", "enl�vement des ordures", "autres"}
  }
  
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public String getFirstName(){
    return firstName;
  }
  
  public String getLastName(){
    return lastName;
  }
  
  public String getPassword(){
    return password;
  }
  
  public int SetService(int index){
    return index;
  }
}