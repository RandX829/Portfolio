package tokyo.randx.portfolio.handson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Todo(int id, String todo, boolean completed) { }
