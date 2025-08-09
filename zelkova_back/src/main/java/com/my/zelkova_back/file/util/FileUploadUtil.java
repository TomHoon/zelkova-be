package com.my.zelkova_back.file.util;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.file.entity.File;
import com.my.zelkova_back.file.repository.FileRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUploadUtil {

    private final FileRepository fileRepository;

    // 업로드 루트 경로
    private final Path rootDir = Paths.get("C:/uploadtest");

    @PostConstruct
    void init() {
        try {
            Files.createDirectories(rootDir);
        } catch (IOException e) {
            throw new CustomException(ResponseCode.FILE_DIRECTORY_CREATE_FAILED);
        }
    }

    /**
     * 파일 저장
     * @param mf 업로드 파일
     * @param subDir 하위 폴더명
     * @param postId 연결된 게시글 ID
     * @param memberId 업로더 ID
     * @return 저장된 File 엔티티
     */
    public File store(MultipartFile mf, String subDir, Long postId, Long memberId) {
        if (mf == null || mf.isEmpty()) {
            throw new CustomException(ResponseCode.FILE_EMPTY);
        }

        String original = sanitizeOriginalName(mf.getOriginalFilename());
        String ext = getExt(original).toLowerCase(Locale.ROOT);

        Set<String> allowed = Set.of("png", "jpg", "jpeg", "webp");
        if (!ext.isBlank() && !allowed.contains(ext)) {
            throw new CustomException(ResponseCode.FILE_EXTENSION_NOT_ALLOWED,
                    "허용되지 않은 확장자: " + ext);
        }

        try {
            String dateDir = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
            Path targetDir = rootDir.resolve(Paths.get(
                    Optional.ofNullable(subDir).orElse("misc"), dateDir)).normalize();
            Files.createDirectories(targetDir);

            String storedName = UUID.randomUUID() + (ext.isBlank() ? "" : "." + ext);
            Path target = targetDir.resolve(storedName);

            Path tmp = Files.createTempFile("upload-", ".tmp");
            try (InputStream in = mf.getInputStream()) {
                Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
            }
            Files.move(tmp, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);

            String storedPath = rootDir.relativize(target).toString().replace(java.io.File.separatorChar, '/');

            File entity = File.builder()
                    .fileName(original)
                    .postId(postId)
                    .filePath(storedPath)
                    .memberId(memberId)
                    .isDeleted(false)
                    .build();

            return fileRepository.save(entity);

        } catch (IOException e) {
            throw new CustomException(ResponseCode.FILE_UPLOAD_FAILED);
        }
    }


    //파일 읽기
    public Resource loadAsResource(String storedPath) {
        Path p = rootDir.resolve(storedPath).normalize();
        if (!p.startsWith(rootDir)) {
            throw new CustomException(ResponseCode.BAD_REQUEST);
        }
        if (!Files.exists(p) || !Files.isReadable(p)) {
            throw new CustomException(ResponseCode.FILE_NOT_FOUND_ERROR);
        }
        return new FileSystemResource(p);
    }
    
    //파일 삭제
    public void delete(String storedPath) {
        try {
            Path p = rootDir.resolve(storedPath).normalize();
            Files.deleteIfExists(p);
        } catch (IOException e) {
            throw new CustomException(ResponseCode.FILE_DELETE_FAILED);
        }
    }

    private String sanitizeOriginalName(String name) {
        if (name == null) return "unknown";
        String base = Paths.get(name).getFileName().toString();
        return base.replaceAll("[\\r\\n\\t]", "_");
    }

    private String getExt(String name) {
        int i = name.lastIndexOf('.');
        return (i >= 0 && i < name.length() - 1) ? name.substring(i + 1) : "";
    }

    @Getter
    public static class Stored {
        private final String storedPath;
        private final String originalName;
        private final long size;
        private final String contentType;

        public Stored(String storedPath, String originalName, long size, String contentType) {
            this.storedPath = storedPath;
            this.originalName = originalName;
            this.size = size;
            this.contentType = contentType;
        }
    }
}
