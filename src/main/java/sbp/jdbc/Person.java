package sbp.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class Person implements Comparable<Person>{
    private long id;
    private String name;
    private String city;
    private int age;

    @Override
    public int compareTo(Person o) {
        int result = city.compareTo(o.city);
        if (result  == 0){
            return name.compareTo(o.name);
        }
        return result;
    }


    public String toString(){
        return name + "[" + city + "]("+ age + ")";
    }

}
