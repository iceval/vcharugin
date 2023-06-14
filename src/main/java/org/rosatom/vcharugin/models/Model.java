package org.rosatom.vcharugin.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "models")
@Getter
@Setter
public class Model {
    @Id
    String id;
    String name;
    String comment;
    Date createAt;
    Date updateAt;
    String creator;
    String updater;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
