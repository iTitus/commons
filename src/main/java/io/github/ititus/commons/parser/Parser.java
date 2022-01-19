package io.github.ititus.commons.parser;

import java.util.Optional;

public interface Parser<IN, OUT> {

    Optional<ParserResult<IN, OUT>> parse(IN in);

}
