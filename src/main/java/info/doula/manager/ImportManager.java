package info.doula.manager;

import info.doula.AsyncConfiguration;
import info.doula.util.ImmutableMap;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static info.doula.AppConstant.CSV_TYP;
import static info.doula.AppConstant.EMAIL;
import static info.doula.AppConstant.ERROR;
import static info.doula.AppConstant.FIRST_NAME;
import static info.doula.AppConstant.LAST_NAME;
import static info.doula.AppConstant.PACKAGE_ID;
import static info.doula.AppConstant.SEND_EMAIL;
import static info.doula.AppConstant.SPONSOR_CODE;
import static info.doula.AppConstant.SPONSOR_REP;
import static info.doula.AppConstant.TXT_TYP;
import static info.doula.AppConstant.XL_TYP;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import org.apache.tika.Tika;

/**
 * Mohammed Hossain Doula
 *
 * @hossaindoula | @itconquest
 * <p>
 * http://hossaindoula.com
 * <p>
 * https://github.com/hossaindoula
 */
@Service
public class ImportManager {

    ///CompletableFuture<Optional<Map<String, Object>>>
    @Async(AsyncConfiguration.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Option<Map<String, Object>>> importVaultHolder(final MultipartFile csvFile) throws InterruptedException, ExecutionException {
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(
                Future.of(csvFile::getBytes)
                        .filterTry(content -> getTikaParser().detect(new ByteArrayInputStream(content)).equals(TXT_TYP)
                                && csvFile.getContentType().equals(CSV_TYP) || csvFile.getContentType().equals(XL_TYP))
                        .onFailure(throwable -> singletonList(ImmutableMap.of(ERROR, "wrong file given")))
                        .mapTry(ByteArrayInputStream::new)
                        .onFailure(throwable -> singletonList(ImmutableMap.of(ERROR, "problem to process csv file")))
                        .mapTry(fileStream -> new BufferedReader(new InputStreamReader(fileStream)))
                        .flatMap(bufferedReader -> Future.of(bufferedReader::lines))
                        .mapTry(stringStream -> Try.of(() -> sponsorRepValues(stringStream))
                                .onFailure(throwable -> Try.of())
                        ).onFailure(throwable -> ImmutableMap.of(ERROR, "Failed to import"))
        ));
    }

    protected Tika getTikaParser(){
        return new Tika();
    }

    private List<Map<String, String>> sponsorRepValues(Stream<String> stringStream) {
        return stringStream.skip(1).map(s -> s.split(","))
                .filter(l -> l.length == 7).collect(toList()).stream()
                .map(m -> ImmutableMap.of(FIRST_NAME, m[0], LAST_NAME, m[1], EMAIL, m[2],
                        SPONSOR_CODE, m[3], PACKAGE_ID, m[4], SPONSOR_REP, m[5], SEND_EMAIL, m[6])).collect(toList());
    }

    private List<Map<String, String>> withoutSponsorValues(Stream<String> stringStream) {
        return stringStream.skip(1).map(s -> s.split(","))
                .filter(l -> l.length == 6).collect(toList()).stream()
                .map(m -> ImmutableMap.of(FIRST_NAME, m[0], LAST_NAME, m[1], EMAIL, m[2],
                        SPONSOR_CODE, m[3], PACKAGE_ID, m[4], SEND_EMAIL, m[5])).collect(toList());
    }
}
