package base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Person implements Comparable<Person> {
    private String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age);
    }

    @Override
    public String toString() {
        // Returns "MyObject{x=1}"
        return MoreObjects.toStringHelper("")
                .add("name", name)
                .add("age", age)
                .toString();
    }


    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.age, that.age)
                .result();
    }
}
