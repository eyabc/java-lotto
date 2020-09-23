package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final String INVALID_LOTTO_SIZE = "로또 개수는 6개 입니다.";
    private static final String LOTTO_NUMBER_DELIMITER = ",";

    private final Set<LottoNumber> lottoNumbers;

    private Lotto(Set<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE);
        }
    }

    public static Lotto from(String manualLotto) {
        return new Lotto(Arrays.stream(manualLotto.split(LOTTO_NUMBER_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .collect(Collectors.toSet()));
    }

    public static Lotto of(LottoNumberGenerator lottoNumberGenerator) {
        return new Lotto(lottoNumberGenerator.generator(LOTTO_SIZE));
    }

    public static Lotto of(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toSet()));
    }

    public int getMatchCountByWinningLotto(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::hasNumber)
                .count();
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return "["
                + lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(" ,"))
                + "]";
    }
}
