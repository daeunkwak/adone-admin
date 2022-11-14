package app.adoneadmin.domain.file.notice;

import app.adoneadmin.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {
}
