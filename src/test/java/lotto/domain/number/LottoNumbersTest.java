package lotto.domain.number;

import lotto.domain.generator.FixedNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("LottoNumbers 클래스 테스트")
public class LottoNumbersTest {
    @DisplayName("NumberGenerator 타입을 주입 받아 LottoNumbers 객체를 생성할 수 있다.")
    @Test
    void createLottoNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.newLottoNumbersWithNumberGenerator(new LottoNumberGenerator());

        assertThat(lottoNumbers.getNumbers()).hasSize(LottoNumbers.LOTTO_SIZE);
    }

    @DisplayName("WinningNumbers와 비교하여 존재하는 갯수를 리턴할 수 있다.")
    @Test
    void matchCount() {
        String winningNumberString = "1,2,3,4,5,6";
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberString);

        NumberGenerator<LottoNumber> fixedNumberGenerator = new FixedNumberGenerator();
        LottoNumbers lottoNumbers = LottoNumbers.newLottoNumbersWithNumberGenerator(fixedNumberGenerator);

        int matchCount = lottoNumbers.matchCount(winningNumbers);

        assertThat(matchCount).isEqualTo(fixedNumberGenerator.getNumbers().size());
    }

    @DisplayName("로또 번호를 포함하는지 확인할 수 있다.")
    @Test
    void contains() {
        LottoNumber lottoNumber = new LottoNumber(1);
        LottoNumbers lottoNumbers = LottoNumbers.newLottoNumbersWithNumberGenerator(new FixedNumberGenerator());

        boolean contains = lottoNumbers.contains(lottoNumber);

        assertThat(contains).isTrue();
    }

    @DisplayName("6자리인지 검증할 수 있다.")
    @Test
    void validateLottoNumbersLength() {
        assertThatExceptionOfType(LottoNumbersLengthException.class)
                .isThrownBy(() -> LottoNumbers.newLottoNumbersWithList(Arrays.asList(1, 2, 3, 4, 5)))
                .withMessage(LottoNumbersLengthException.MESSAGE);
    }

    @DisplayName("중복된 값이 있는지 검증할 수 있다.")
    @Test
    void validateLottoNumberDuplicate() {
        assertThatExceptionOfType(LottoNumbersDuplicateException.class)
                .isThrownBy(() -> LottoNumbers.newLottoNumbersWithList(Arrays.asList(1, 2, 2, 3, 4, 5)))
                .withMessage(LottoNumbersDuplicateException.MESSAGE);
    }
}