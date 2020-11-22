package step01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntTest {

    private static Stream<Arguments> provideIsANumberResult() {
        return Stream.of(
          Arguments.of("1", true),
          Arguments.of("-1", true),
          Arguments.of("0", true),
          Arguments.of("1/", false),
          Arguments.of("111a", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsANumberResult")
    public void test_isANumber(String input, boolean expect) {
        assertThat(Int.isParsed(input)).isEqualTo(expect);
    }

    private static Stream<Integer> provideNegativeResult() {
        return Stream.of(
                -1,
                -10
        );
    }

    @ParameterizedTest
    @MethodSource("provideNegativeResult")
    public void test_validateNotNegative_throwException(int input) {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Int.validateNotNegative(input));
    }
}
