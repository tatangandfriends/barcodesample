/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barcodesample.entity;

import java.io.Serializable;
import javafx.beans.property.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Hadouken
 */
@Entity
@Table(name = "boom_kunana")
@NamedQueries({
@NamedQuery(name = "BoomKunana.findAll", query = "SELECT b FROM BoomKunana b"),   
    @NamedQuery(name = "BoomKunana.findByName", query = "SELECT b FROM BoomKunana b WHERE b.employeeName = :employeeName")})
public class BoomKunana implements Serializable {
    
   private StringProperty id;
   private StringProperty employeeName;
   
   public BoomKunana(){
       id = new SimpleStringProperty();
       employeeName = new SimpleStringProperty();
   }
   
   
   public BoomKunana(String name){
       this.employeeName.set(name);
   }
      
   @Id
   @Column(name = "id")
   public String getId(){
       return this.id.get();
   }
   
   public void setId(String id){
       this.id.set(id);
   }
   
   @Column(name = "employee_name")
   public String getEmployeeName(){
       return this.employeeName.get();
   }
   
   public void setEmployeeName(String name){
       this.employeeName.set(name);
   }
   
}
