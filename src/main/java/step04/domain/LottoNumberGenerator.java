package step04.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class LottoNumberGenerator {
    private static String TOKEN = ", ";

    public static Lotto execute(String lottoNumbers) {
        return Lotto.of(
                Arrays.stream(lottoNumbers.split(TOKEN))
                        .map(Integer::parseInt)
                        .collect(toList())
        );
    }

    public static Lottos executeByMultiple(List<String> lottos) {
        return lottos.stream()
                .map(LottoNumberGenerator::execute)
                .collect(Collectors.collectingAndThen(toList(), Lottos::of));
    }

}
