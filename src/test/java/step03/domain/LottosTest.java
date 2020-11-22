package step03.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step03.Rank;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    List<Lotto> lottos;
    LottoBall bonusBall;
    Lotto winningLotto;

    @BeforeEach
    void setup() {
        lottos = Arrays.asList(
                Lotto.intOf(Arrays.asList(8, 21, 23, 41, 42, 43)),
                Lotto.intOf(Arrays.asList(3, 5, 11, 16, 32, 38)),
                Lotto.intOf(Arrays.asList(7, 11, 16, 35, 36, 44)),
                Lotto.intOf(Arrays.asList(1, 8, 11, 31, 41, 42)),
                Lotto.intOf(Arrays.asList(13, 14, 16, 38, 42, 45)),
                Lotto.intOf(Arrays.asList(7, 11, 30, 40, 42, 43)),
                Lotto.intOf(Arrays.asList(2, 13, 22, 32, 38, 45)),
                Lotto.intOf(Arrays.asList(23, 25, 33, 36, 39, 41)),
                Lotto.intOf(Arrays.asList(1, 3, 5, 14, 22, 45)),
                Lotto.intOf(Arrays.asList(5, 9, 38, 41, 43, 44)),
                Lotto.intOf(Arrays.asList(2, 8, 9, 18, 19, 21)),
                Lotto.intOf(Arrays.asList(13, 14, 18, 21, 23, 35)),
                Lotto.intOf(Arrays.asList(17, 21, 29, 37, 42, 45)),
                Lotto.intOf(Arrays.asList(3, 8, 27, 30, 35, 44))
        );
        winningLotto = Lotto.intOf(Arrays.asList(1, 2, 3, 4, 5, 6));
        bonusBall = LottoBall.valueOf(7);
    }

    @DisplayName("생성자")
    @Test
    void test_constructor() {
        assertThat(Lottos.of(lottos)).isEqualTo(Lottos.of(lottos));
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
}
