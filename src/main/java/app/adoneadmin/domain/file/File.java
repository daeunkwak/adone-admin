package app.adoneadmin.domain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class File {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long fileId;

    @Column(nullable = false, length = 300)
    private String fileUrl;

    public File(String fileUrl){
        this.fileUrl = fileUrl;
    }

}
