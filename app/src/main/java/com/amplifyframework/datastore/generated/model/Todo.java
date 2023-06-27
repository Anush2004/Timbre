package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Todo type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Todos", type = Model.Type.USER, version = 1)
public final class Todo implements Model {
  public static final QueryField ID = field("Todo", "id");
  public static final QueryField FIRSTNAME = field("Todo", "firstname");
  public static final QueryField LASTNAME = field("Todo", "lastname");
  public static final QueryField AUDIO_URL = field("Todo", "audioURL");
  public static final QueryField GENDER = field("Todo", "gender");
  public static final QueryField AGE = field("Todo", "age");
  public static final QueryField BMI = field("Todo", "bmi");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String firstname;
  private final @ModelField(targetType="String", isRequired = true) String lastname;
  private final @ModelField(targetType="String", isRequired = true) String audioURL;
  private final @ModelField(targetType="String", isRequired = true) String gender;
  private final @ModelField(targetType="Int", isRequired = true) Integer age;
  private final @ModelField(targetType="Float", isRequired = true) Double bmi;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getFirstname() {
      return firstname;
  }
  
  public String getLastname() {
      return lastname;
  }
  
  public String getAudioUrl() {
      return audioURL;
  }
  
  public String getGender() {
      return gender;
  }
  
  public Integer getAge() {
      return age;
  }
  
  public Double getBmi() {
      return bmi;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Todo(String id, String firstname, String lastname, String audioURL, String gender, Integer age, Double bmi) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.audioURL = audioURL;
    this.gender = gender;
    this.age = age;
    this.bmi = bmi;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Todo todo = (Todo) obj;
      return ObjectsCompat.equals(getId(), todo.getId()) &&
              ObjectsCompat.equals(getFirstname(), todo.getFirstname()) &&
              ObjectsCompat.equals(getLastname(), todo.getLastname()) &&
              ObjectsCompat.equals(getAudioUrl(), todo.getAudioUrl()) &&
              ObjectsCompat.equals(getGender(), todo.getGender()) &&
              ObjectsCompat.equals(getAge(), todo.getAge()) &&
              ObjectsCompat.equals(getBmi(), todo.getBmi()) &&
              ObjectsCompat.equals(getCreatedAt(), todo.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), todo.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFirstname())
      .append(getLastname())
      .append(getAudioUrl())
      .append(getGender())
      .append(getAge())
      .append(getBmi())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Todo {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("firstname=" + String.valueOf(getFirstname()) + ", ")
      .append("lastname=" + String.valueOf(getLastname()) + ", ")
      .append("audioURL=" + String.valueOf(getAudioUrl()) + ", ")
      .append("gender=" + String.valueOf(getGender()) + ", ")
      .append("age=" + String.valueOf(getAge()) + ", ")
      .append("bmi=" + String.valueOf(getBmi()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static FirstnameStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Todo justId(String id) {
    return new Todo(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      firstname,
      lastname,
      audioURL,
      gender,
      age,
      bmi);
  }
  public interface FirstnameStep {
    LastnameStep firstname(String firstname);
  }
  

  public interface LastnameStep {
    AudioUrlStep lastname(String lastname);
  }
  

  public interface AudioUrlStep {
    GenderStep audioUrl(String audioUrl);
  }
  

  public interface GenderStep {
    AgeStep gender(String gender);
  }
  

  public interface AgeStep {
    BmiStep age(Integer age);
  }
  

  public interface BmiStep {
    BuildStep bmi(Double bmi);
  }
  

  public interface BuildStep {
    Todo build();
    BuildStep id(String id);
  }
  

  public static class Builder implements FirstnameStep, LastnameStep, AudioUrlStep, GenderStep, AgeStep, BmiStep, BuildStep {
    private String id;
    private String firstname;
    private String lastname;
    private String audioURL;
    private String gender;
    private Integer age;
    private Double bmi;
    @Override
     public Todo build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Todo(
          id,
          firstname,
          lastname,
          audioURL,
          gender,
          age,
          bmi);
    }
    
    @Override
     public LastnameStep firstname(String firstname) {
        Objects.requireNonNull(firstname);
        this.firstname = firstname;
        return this;
    }
    
    @Override
     public AudioUrlStep lastname(String lastname) {
        Objects.requireNonNull(lastname);
        this.lastname = lastname;
        return this;
    }
    
    @Override
     public GenderStep audioUrl(String audioUrl) {
        Objects.requireNonNull(audioUrl);
        this.audioURL = audioUrl;
        return this;
    }
    
    @Override
     public AgeStep gender(String gender) {
        Objects.requireNonNull(gender);
        this.gender = gender;
        return this;
    }
    
    @Override
     public BmiStep age(Integer age) {
        Objects.requireNonNull(age);
        this.age = age;
        return this;
    }
    
    @Override
     public BuildStep bmi(Double bmi) {
        Objects.requireNonNull(bmi);
        this.bmi = bmi;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String firstname, String lastname, String audioUrl, String gender, Integer age, Double bmi) {
      super.id(id);
      super.firstname(firstname)
        .lastname(lastname)
        .audioUrl(audioUrl)
        .gender(gender)
        .age(age)
        .bmi(bmi);
    }
    
    @Override
     public CopyOfBuilder firstname(String firstname) {
      return (CopyOfBuilder) super.firstname(firstname);
    }
    
    @Override
     public CopyOfBuilder lastname(String lastname) {
      return (CopyOfBuilder) super.lastname(lastname);
    }
    
    @Override
     public CopyOfBuilder audioUrl(String audioUrl) {
      return (CopyOfBuilder) super.audioUrl(audioUrl);
    }
    
    @Override
     public CopyOfBuilder gender(String gender) {
      return (CopyOfBuilder) super.gender(gender);
    }
    
    @Override
     public CopyOfBuilder age(Integer age) {
      return (CopyOfBuilder) super.age(age);
    }
    
    @Override
     public CopyOfBuilder bmi(Double bmi) {
      return (CopyOfBuilder) super.bmi(bmi);
    }
  }
  
}
