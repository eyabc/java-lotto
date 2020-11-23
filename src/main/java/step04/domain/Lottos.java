package step04.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos of(Integer countOfLotto) {
        return of(generateLottos(countOfLotto));
    }

    private static List<Lotto> generateLottos(Integer countOfLotto) {
        return Stream.generate(LottoGenerator::generate)
                .limit(countOfLotto - 1)
                .collect(Collectors.toList());
    }

    public Integer size() {
        return lottos.size();
    }

    public RankCounter calculateCountOfMatch(Lotto targetLotto, LottoBall bonusBall) {
        RankCounter rankCounter = RankCounter.of();
        lottos.forEach(lotto -> {
            int matchOfCount = lotto.matchCount(targetLotto);
            Rank target = Rank.valueOf(matchOfCount, lotto.isContaining(bonusBall));
            rankCounter.increase(target);
        });
        return rankCounter;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }

    public Lottos concat(Lottos addedLottos) {
        return Lottos.of(
                Stream.concat(lottos.stream(), addedLottos.lottos.stream())
                        .collect(Collectors.toList())
        );
    }
}
