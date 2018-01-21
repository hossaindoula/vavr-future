package info.doula.controller;

import info.doula.AsyncConfiguration;
import info.doula.manager.ImportManager;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import static info.doula.AsyncConfiguration.TASK_EXECUTOR_CONTROLLER;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Mohammed Hossain Doula
 *
 * @hossaindoula | @itconquest
 * <p>
 * http://hossaindoula.com
 * </p>
 * https://github.com/hossaindoula
 */
@RestController
@RequestMapping("/api/import")
public class ImportResource {
    @Autowired
    private ImportManager importManager;

    @Async(TASK_EXECUTOR_CONTROLLER)
    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getUser(@RequestParam("file") MultipartFile csvFile) throws Exception {
        try {
            return importManager.importVaultHolder(csvFile)
                    .thenApply(mapMaybeToResponse)
                    .exceptionally(handleGetUserFailure);
        } catch (InterruptedException | ExecutionException ex) {
            throw new Exception("Service is down");
        }
    }

    protected Function<Option<?>, ResponseEntity> mapMaybeToResponse = maybeUser -> maybeUser
            .<ResponseEntity>map(ResponseEntity::ok)
            .getOrElse(ResponseEntity.notFound().build());

    public Function<String, Function<Throwable, ResponseEntity>> handleGetUserFailure = userId ->
            throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

}
