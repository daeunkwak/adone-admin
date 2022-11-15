package app.adoneadmin.repository.file;

import app.adoneadmin.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
