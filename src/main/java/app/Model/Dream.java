package app.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Dream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String filename;

    private boolean done;

    private int startYear;

    public Dream(String description, String filename, boolean done, int startYear) {
        this.description = description;
        this.filename = filename;
        this.done = done;
        this.startYear = startYear;
    }
}
