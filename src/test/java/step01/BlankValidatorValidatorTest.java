package step01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BlankValidatorValidatorTest {

    @ParameterizedTest
    @NullSource
    public void test_isNull(String input) {
        assertThat(BlankValidator.isNull(input)).isTrue();
    }

    @ParameterizedTest
    @EmptySource
    public void test_isEmpty(String input) {
        assertThat(BlankValidator.isEmpty(input)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void test_validate(String input) {
        assertThat(BlankValidator.validate(input)).isTrue();
    }

}
