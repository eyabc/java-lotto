package step04.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    List<Lotto> lottos;
    LottoBall bonusBall;
    Lotto winningLotto;

    @BeforeEach
    void setup() {
        lottos = Arrays.asList(
                Lotto.of(8, 21, 23, 41, 42, 43),
                Lotto.of(3, 5, 11, 16, 32, 38),
                Lotto.of(7, 11, 16, 35, 36, 44),
                Lotto.of(1, 8, 11, 31, 41, 42),
                Lotto.of(13, 14, 16, 38, 42, 45),
                Lotto.of(7, 11, 30, 40, 42, 43),
                Lotto.of(2, 13, 22, 32, 38, 45),
                Lotto.of(23, 25, 33, 36, 39, 41),
                Lotto.of(1, 3, 5, 14, 22, 45),
                Lotto.of(5, 9, 38, 41, 43, 44),
                Lotto.of(2, 8, 9, 18, 19, 21),
                Lotto.of(13, 14, 18, 21, 23, 35),
                Lotto.of(17, 21, 29, 37, 42, 45),
                Lotto.of(3, 8, 27, 30, 35, 44)
        );
        winningLotto = Lotto.of(1, 2, 3, 4, 5, 6);
        bonusBall = LottoBall.valueOf(7);
    }

    @DisplayName("생성자")
    @Test
    void test_constructor() {
        assertThat(Lottos.of(lottos)).isEqualTo(Lottos.of(lottos));
    }

    @DisplayName("생성자 매개 변수가 lottos 의 count")
    @Test
    void test_constructor_coutOfLottos() {
        assertThat(Lottos.of(3).size()).isEqualTo(Lottos.of(3).size());
    }

    @DisplayName("사이즈 리턴")
    @Test
    void test_size() {
        assertThat(Lottos.of(lottos).size()).isEqualTo(14);
    }

    @DisplayName("matching Count 계산")
    @Test
    void test_calculateCountOfMatch() {
        RankCounter expect = RankCounter.of();

        expect.put(Rank.valueOf(3, false), 1);
        expect.put(Rank.valueOf(0, false), 13);

        assertThat(Lottos.of(lottos).calculateCountOfMatch(winningLotto, bonusBall))
                .isEqualTo(expect);
    }

    private static Stream<Arguments> provideManualLottosResult() {
        List<Lotto> lottos = Arrays.asList(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6)
        );

        List<Lotto> expectLottos = Arrays.asList(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 6)
        );

        return Stream.of(
                Arguments.of(Lottos.of(lottos), Lottos.of(lottos), Lottos.of(expectLottos))
        );
    }

    @DisplayName("Lottos 에 로또들 더하기")
    @ParameterizedTest
    @MethodSource("provideManualLottosResult")
    void test_addLottos(Lottos originLottos, Lottos addedLottos, Lottos expect) {
        assertThat(originLottos.concat(addedLottos))
                .isEqualTo(expect);
    }
}
