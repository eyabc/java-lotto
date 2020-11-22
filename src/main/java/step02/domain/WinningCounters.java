package step02.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningCounters {
    private final int FIRST = 0;
    private final List<WinningCounter> winningCounters;

    private WinningCounters(List<WinningCounter> winningCounters) {
        this.winningCounters = winningCounters;
    }

    public static WinningCounters of(List<WinningCounter> winningCounters) {
        return new WinningCounters(winningCounters);
    }

    public int getCount(int winningNumber) {
        List<WinningCounter> filteredWinningCounters = winningCounters.stream()
                .filter(winningCounter -> winningCounter.isSame(winningNumber))
                .collect(Collectors.toList());

        if (filteredWinningCounters.size() == 0) return 0;

        return filteredWinningCounters.get(FIRST).getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningCounters that = (WinningCounters) o;
        return Objects.equals(winningCounters, that.winningCounters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningCounters);
    }
}
